package br.com.appfastfood.carrinho.infraestrutura.entidades;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrinhoEntidadeTest {

    @Test
    public void testCarrinhoEntidade() {
        Long id = 1L;
        List<ProdEnt> produtos = Arrays.asList(new ProdEnt("1", "2"));
        String clienteId = "123";
        Double valorTotal = 100.0;
        String status = "ABERTO";

        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(id, produtos, clienteId, valorTotal, status);

        // Verifica se os métodos de acesso estão funcionando corretamente
        assertEquals(id, carrinhoEntidade.getId());
        assertEquals(produtos, carrinhoEntidade.getProdutos());
        assertEquals(clienteId, carrinhoEntidade.getClienteId());
        assertEquals(valorTotal, carrinhoEntidade.getValorTotal());
        assertEquals(status, carrinhoEntidade.getStatus());

        // Modifica alguns valores usando setters
        carrinhoEntidade.setId(2L);
        carrinhoEntidade.setProdutos(Arrays.asList(new ProdEnt("3", "4")));
        carrinhoEntidade.setClienteId("456");
        carrinhoEntidade.setValorTotal(200.0);
        carrinhoEntidade.setStatus("FECHADO");

        // Verifica se os setters estão funcionando corretamente
        assertEquals(2L, carrinhoEntidade.getId());
        assertEquals("3", carrinhoEntidade.getProdutos().get(0).getIdProduto());
        assertEquals("456", carrinhoEntidade.getClienteId());
        assertEquals(200.0, carrinhoEntidade.getValorTotal());
        assertEquals("FECHADO", carrinhoEntidade.getStatus());
    }
}

