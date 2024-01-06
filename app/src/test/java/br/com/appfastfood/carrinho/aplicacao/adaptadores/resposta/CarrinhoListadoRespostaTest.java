package br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarrinhoListadoRespostaTest {

    @Test
    void testConstrutorComBuilder() {
        List<ProdutosReq> produtos = Arrays.asList(
                ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build(),
                ProdutosReq.builder().idProduto("3").quantidadeProduto("4").build()
        );

        CarrinhoListadoResposta carrinhoListadoResposta = CarrinhoListadoResposta.builder()
                .id(123L)
                .produtos(produtos)
                .idCliente("cliente123")
                .valorTotal(100.0)
                .status("ABERTO")
                .build();

        assertThat(carrinhoListadoResposta).isNotNull();
        assertThat(carrinhoListadoResposta.getId()).isEqualTo(123L);
        assertThat(carrinhoListadoResposta.getProdutos()).hasSize(2);
        assertThat(carrinhoListadoResposta.getIdCliente()).isEqualTo("cliente123");
        assertThat(carrinhoListadoResposta.getValorTotal()).isEqualTo(100.0);
        assertThat(carrinhoListadoResposta.getStatus()).isEqualTo("ABERTO");
    }

    @Test
    void testGettersAndSetters() {
        List<ProdutosReq> produtos = Arrays.asList(
                ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build(),
                ProdutosReq.builder().idProduto("3").quantidadeProduto("4").build()
        );

        CarrinhoListadoResposta carrinhoListadoResposta = new CarrinhoListadoResposta(null, null, null, null);
        carrinhoListadoResposta.setId(456L);
        carrinhoListadoResposta.setProdutos(produtos);
        carrinhoListadoResposta.setIdCliente("cliente456");
        carrinhoListadoResposta.setValorTotal(150.0);
        carrinhoListadoResposta.setStatus("FECHADO");

        assertThat(carrinhoListadoResposta.getId()).isEqualTo(456L);
        assertThat(carrinhoListadoResposta.getProdutos()).hasSize(2);
        assertThat(carrinhoListadoResposta.getIdCliente()).isEqualTo("cliente456");
        assertThat(carrinhoListadoResposta.getValorTotal()).isEqualTo(150.0);
        assertThat(carrinhoListadoResposta.getStatus()).isEqualTo("FECHADO");
    }
}
