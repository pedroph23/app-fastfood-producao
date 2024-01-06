package br.com.appfastfood.carrinho.infraestrutura.entidades;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdEntTest {

    @Test
    public void testProdEnt() {
        String idProduto = "1";
        String quantidadeProduto = "2";

        ProdEnt prodEnt = new ProdEnt(idProduto, quantidadeProduto);

        // Verifica se os métodos de acesso estão funcionando corretamente
        assertEquals(idProduto, prodEnt.getIdProduto());
        assertEquals(quantidadeProduto, prodEnt.getQuantidadeProduto());

        // Modifica alguns valores usando setters
        prodEnt.setIdProduto("3");
        prodEnt.setQuantidadeProduto("4");

        // Verifica se os setters estão funcionando corretamente
        assertEquals("3", prodEnt.getIdProduto());
        assertEquals("4", prodEnt.getQuantidadeProduto());
    }
}
