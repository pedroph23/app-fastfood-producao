package br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarrinhoRequisicaoTest {
    

    @Test
    public void testCarrinhoRequisicao() {
        // Criar objetos fictícios para o teste
        ProdutosReq produtosReq = ProdutosReq.builder()
                .idProduto("1")
                .quantidadeProduto("2")
                .build();

        List<ProdutosReq> produtos = Arrays.asList(produtosReq);
        String idCliente = "123";

        // Criar uma instância da classe CarrinhoRequisicao
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(produtos, idCliente);

        // Verificações
        verificarAtributos(carrinhoRequisicao, idCliente, produtos);
        verificarGetters(carrinhoRequisicao, idCliente, produtos);
        verificarSetters(carrinhoRequisicao);
        verificarMetodoBuild(carrinhoRequisicao, idCliente, produtos);
    }

    private void verificarAtributos(CarrinhoRequisicao carrinhoRequisicao, String idCliente, List<ProdutosReq> produtos) {
        // Verificar se os atributos foram configurados corretamente
        assertEquals(idCliente, carrinhoRequisicao.getIdCliente());
        assertEquals(produtos, carrinhoRequisicao.getProdutos());
    }

    private void verificarGetters(CarrinhoRequisicao carrinhoRequisicao, String idCliente, List<ProdutosReq> produtos) {
        // Verificar os getters
        assertEquals(idCliente, carrinhoRequisicao.getIdCliente());
        assertEquals(produtos, carrinhoRequisicao.getProdutos());
    }

    private void verificarSetters(CarrinhoRequisicao carrinhoRequisicao) {
        // Verificar os setters
        String novoIdCliente = "456";
        carrinhoRequisicao.setIdCliente(novoIdCliente);
        assertEquals(novoIdCliente, carrinhoRequisicao.getIdCliente());

        List<ProdutosReq> novosProdutos = Arrays.asList(ProdutosReq.builder().idProduto("3").quantidadeProduto("1").build());
        carrinhoRequisicao.setProdutos(novosProdutos);
        assertEquals(novosProdutos, carrinhoRequisicao.getProdutos());
    }

    private void verificarMetodoBuild(CarrinhoRequisicao carrinhoRequisicao, String idCliente, List<ProdutosReq> produtos) {
        // Verificar o método build
        CarrinhoRequisicao outroCarrinhoRequisicao = CarrinhoRequisicao.builder()
                .idCliente(idCliente)
                .produtos(produtos)
                .build();

        assertEquals(outroCarrinhoRequisicao.getIdCliente(), "123");
    }

}