package br.com.appfastfood.carrinho.dominio.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoVOTest {

    @Test
    public void testProdutoVO() {
        String idProduto = "1";
        String quantidadeProduto = "2";

        ProdutoVO produtoVO = new ProdutoVO(idProduto, quantidadeProduto);

        assertEquals(idProduto, produtoVO.getIdProduto());
        assertEquals(quantidadeProduto, produtoVO.getQuantidadeProduto());
    }
}
