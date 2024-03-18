package br.com.appfastfood.carrinho.aplicacao.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.MensagemSNS;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoListadoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.ProdutoResposta;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.infraestrutura.menssagem.entrada.CarrinhoFecharIN;
import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.execption.InternalServerErrorException;
import br.com.appfastfood.configuracoes.logs.Log;
import com.amazonaws.services.sns.AmazonSNS;
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
@RequestMapping("/carrinho-eventos")
@Tag(name = "Carrinho Eventos", description = "Tudo sobre Carrinho")
public class CarrinhoEventosController {

    private CarrinhoServico carrinhoServico;
    private Log logger;
    private final AmazonSNS amazonSNS;

    public CarrinhoEventosController(CarrinhoServico carrinhoServico, Log logger, AmazonSNS amazonSNS) {
        this.carrinhoServico = carrinhoServico;
        this.logger = logger;
        this.amazonSNS = amazonSNS;
    }

    @PostMapping("/fecharCarrinho")
    @Operation(summary = "Fechar Carrinho", description = "Evento de fechar o carrinho, usando id de carrinho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho fechado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResposta.class))}),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RequisicaoExcecao.class)))})
    public ResponseEntity fecharCarrinho(@RequestBody String notification) {

        ObjectMapper mapper = new ObjectMapper();
        MensagemSNS mensagemSNS;

        try {
            mensagemSNS = mapper.readValue(notification, MensagemSNS.class);

            switch (mensagemSNS.getType()) {
                case "SubscriptionConfirmation":
                    // Lógica para confirmar a inscrição
                    String subscribeURL = mensagemSNS.getSubscribeURL();
                    System.out.println("Received subscription confirmation request. URL: " + subscribeURL);
                    HttpClient client = HttpClient.newHttpClient();
                    URI uri = URI.create(subscribeURL);
                    System.out.println("PATH URL: " + uri.getPath());
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(subscribeURL))
                            .GET() // Método GET para confirmar a inscrição
                            .build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("Subscription confirmed successfully.");
                        } else {
                            System.out.println("Failed to confirm subscription. Response code: " + response.statusCode());
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error confirming subscription: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no serviço");
                    }

                    break;
                case "Notification":
                    // Lógica para tratar mensagens recebidas
                    System.out.println("Received message: " + mensagemSNS.getMessage());
                    carrinhoServico.fecharCarrinho(mensagemSNS.getMessage());
                    break;
                case "UnsubscribeConfirmation":
                    // Lógica para tratar confirmações de cancelamento de inscrição
                    System.out.println("Unsubscribed from topic");
                    break;
                default:
                    System.out.println("Unknown message type: " + mensagemSNS.getType());
                    break;
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception j) {
            throw new RuntimeException(j);
        }
    }
}
