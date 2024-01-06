package br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarrinhoRespostaTest {

    @Test
    void testConstrutorComBuilder() {
        List<ProdutosReq> produtos = Arrays.asList(
                ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build(),
                ProdutosReq.builder().idProduto("3").quantidadeProduto("4").build()
        );

        CarrinhoResposta carrinhoResposta = CarrinhoResposta.builder()
                .produtos(produtos)
                .idCliente("1")
                .valorTotal(100.0)
                .build();

        assertThat(carrinhoResposta).isNotNull();
        assertThat(carrinhoResposta.getProdutos()).hasSize(2);
        assertThat(carrinhoResposta.getIdCliente()).isEqualTo("1");
        assertThat(carrinhoResposta.getValorTotal()).isEqualTo(100.0);
    }

    @Test
    void testGettersAndSetters() {
        List<ProdutosReq> produtos = Arrays.asList(
                ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build(),
                ProdutosReq.builder().idProduto("3").quantidadeProduto("4").build()
        );

        CarrinhoResposta carrinhoResposta = new CarrinhoResposta(null, null, null);
        carrinhoResposta.setProdutos(produtos);
        carrinhoResposta.setIdCliente("1");
        carrinhoResposta.setValorTotal(150.0);

        assertThat(carrinhoResposta.getProdutos()).hasSize(2);
        assertThat(carrinhoResposta.getIdCliente()).isEqualTo("1");
        assertThat(carrinhoResposta.getValorTotal()).isEqualTo(150.0);
    }

}

