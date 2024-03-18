package br.com.appfastfood.carrinho.aplicacao.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.MensagemSNS;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoListadoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.ProdutoResposta;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;

import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.logs.Log;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/carrinho")
@Tag(name = "Carrinho", description = "Tudo sobre Carrinho")
public class CarrinhoController {

    private CarrinhoServico carrinhoServico;
    private Log logger;
    private final AmazonSNS amazonSNS;

    public CarrinhoController(CarrinhoServico carrinhoServico, Log logger, AmazonSNS amazonSNS) {
        this.carrinhoServico = carrinhoServico;
        this.logger = logger;
        this.amazonSNS = amazonSNS;
    }

    @PostMapping
    @Operation(summary = "Criar Carrinho", description = "Funcionalidade de criar um carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarrinhoResposta.class))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})

    public ResponseEntity cadastrar(@RequestBody CarrinhoRequisicao carrinhoRequisicao) {
        this.carrinhoServico.cadastrar(carrinhoRequisicao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover Carrinho", description = "Funcionalidade de remover um carrinho passando o parametro 'id' do carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho removido com suceso",
                    content = {@Content()}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity remover(@PathVariable("id") Long id) {
        this.carrinhoServico.remover(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    @Operation(summary = "Listar Carrinhos", description = "Funcionalidade de listar carrinhos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content()})})
    public ResponseEntity<List<CarrinhoListadoResposta>> listar() {

        List<Carrinho> carrinhos = this.carrinhoServico.listar();

        List<CarrinhoListadoResposta> carrinhosResposta = carrinhos.stream().map(carrinho -> {
            List<ProdutosReq> produtosReqs = carrinho.getProdutoVOS()
                    .stream()
                    .map(produtoVO -> ProdutosReq
                            .builder()
                            .idProduto(produtoVO.getIdProduto())
                            .quantidadeProduto(produtoVO.getQuantidadeProduto())
                            .build()
                    ).toList();
            return new CarrinhoListadoResposta(carrinho.getId(), produtosReqs, carrinho.getCliente().getCliente(), carrinho.getValorTotal(), carrinho.getStatus().getNome());
        }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(carrinhosResposta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Carrinho", description = "Funcionalidade de atualização de um carrinho passando o parametro 'id' e o corpo da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoResposta.class))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CarrinhoRequisicao carrinhoRequisicao){

        Carrinho carrinho = this.carrinhoServico.atualizar(id, carrinhoRequisicao);

        List<ProdutosReq> produtosReq = carrinho.getProdutoVOS().stream().map(produtoVO -> ProdutosReq
                .builder()
                .idProduto(produtoVO.getIdProduto())
                .quantidadeProduto(produtoVO.getQuantidadeProduto())
                .build()
        ).toList();

        CarrinhoResposta carrinhoResposta = new CarrinhoResposta(produtosReq, carrinho.getCliente().getCliente(), carrinho.getValorTotal());

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoResposta);
    }

    @PutMapping("/fecharCarrinho/{id}")
    @Operation(summary = "Fechar Carrinho", description = "Funcionalidade de fechar o carrinho, usando id de carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho fechado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResposta.class))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity fecharCarrinho(@PathVariable("id") Long id){
        this.carrinhoServico.fecharCarrinho(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
