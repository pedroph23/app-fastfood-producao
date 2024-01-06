package br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutosReqTest {

    @Test
    public void testProdutosReq() {
        // Criar um objeto fictício para o teste
        ProdutosReq produtosReq = ProdutosReq.builder()
                .idProduto("1")
                .quantidadeProduto("2")
                .build();

        // Verificações
        verificarAtributos(produtosReq, "1", "2");
        verificarGetters(produtosReq, "1", "2");
        verificarSetters(produtosReq);
        verificarMetodoBuild("1", "2");
    }

    private void verificarAtributos(ProdutosReq produtosReq, String idProduto, String quantidadeProduto) {
        // Verificar se os atributos foram configurados corretamente
        assertEquals(idProduto, produtosReq.getIdProduto());
        assertEquals(quantidadeProduto, produtosReq.getQuantidadeProduto());
    }

    private void verificarGetters(ProdutosReq produtosReq, String idProduto, String quantidadeProduto) {
        // Verificar os getters
        assertEquals(idProduto, produtosReq.getIdProduto());
        assertEquals(quantidadeProduto, produtosReq.getQuantidadeProduto());
    }

    private void verificarSetters(ProdutosReq produtosReq) {
        // Verificar os setters
        String novoIdProduto = "3";
        produtosReq.setIdProduto(novoIdProduto);
        assertEquals(novoIdProduto, produtosReq.getIdProduto());

        String novaQuantidadeProduto = "1";
        produtosReq.setQuantidadeProduto(novaQuantidadeProduto);
        assertEquals(novaQuantidadeProduto, produtosReq.getQuantidadeProduto());
    }

    private void verificarMetodoBuild(String idProduto, String quantidadeProduto) {
        // Verificar o método build
        ProdutosReq outroProdutosReq = ProdutosReq.builder()
                .idProduto(idProduto)
                .quantidadeProduto(quantidadeProduto)
                .build();

        assertEquals(outroProdutosReq.getIdProduto(), idProduto);
    }
}
