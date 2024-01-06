package br.com.appfastfood.carrinho.aplicacao.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoListadoResposta;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.dominio.modelos.Cliente;
import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.logs.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarrinhoControllerTest {

    @Mock
    private CarrinhoServico carrinhoServico;

    @Mock
    private Log logger;

    @InjectMocks
    private CarrinhoController carrinhoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carrinhoController = new CarrinhoController(carrinhoServico, logger);
    }

    @Test
    public void testCadastrar() {
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(ProdutosReq.builder().idProduto("1").quantidadeProduto("1").build()), "123");

        ResponseEntity resposta = carrinhoController.cadastrar(carrinhoRequisicao);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        verify(carrinhoServico, times(1)).cadastrar(carrinhoRequisicao);
    }

    @Test
    public void testRemover() {
        ResponseEntity resposta = carrinhoController.remover(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(carrinhoServico, times(1)).remover(1L);
    }

    @Test
    public void testListar() {
        List<Carrinho> carrinhos = Arrays.asList(new Carrinho(Arrays.asList(), new Cliente("1"), 1.0, StatusCarrinhoEnum.ABERTO));
        when(carrinhoServico.listar()).thenReturn(carrinhos);

        ResponseEntity<List<CarrinhoListadoResposta>> resposta = carrinhoController.listar();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        verify(carrinhoServico, times(1)).listar();
    }

    @Test
    public void testAtualizar() {
        Long id = 1L;
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(ProdutosReq.builder().idProduto("1").quantidadeProduto("1").build()), "123");
        Carrinho carrinhoAtualizado = new Carrinho(Arrays.asList(), new Cliente("1"), 1.0, StatusCarrinhoEnum.ABERTO);

        when(carrinhoServico.atualizar(id, carrinhoRequisicao)).thenReturn(carrinhoAtualizado);

        ResponseEntity resposta = carrinhoController.atualizar(id, carrinhoRequisicao);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(carrinhoServico, times(1)).atualizar(id, carrinhoRequisicao);
    }

    @Test
    public void testFecharCarrinho() {
        ResponseEntity resposta = carrinhoController.fecharCarrinho(1L);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(carrinhoServico, times(1)).fecharCarrinho(1L);
    }
}

