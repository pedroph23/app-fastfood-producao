package br.com.appfastfood.carrinho.dominio.modelos.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusCarrinhoEnumTest {

    @Test
    public void testBuscaEnumPorStatusString() {
        StatusCarrinhoEnum aberto = StatusCarrinhoEnum.ABERTO;
        StatusCarrinhoEnum fechado = StatusCarrinhoEnum.FECHADO;

        assertEquals(aberto, StatusCarrinhoEnum.buscaEnumPorStatusString("ABERTO"));
        assertEquals(fechado, StatusCarrinhoEnum.buscaEnumPorStatusString("FECHADO"));
    }

    @Test
    public void testRetornaNomeEnum() {
        StatusCarrinhoEnum aberto = StatusCarrinhoEnum.ABERTO;
        StatusCarrinhoEnum fechado = StatusCarrinhoEnum.FECHADO;

        assertEquals("ABERTO", StatusCarrinhoEnum.retornaNomeEnum(aberto));
        assertEquals("FECHADO", StatusCarrinhoEnum.retornaNomeEnum(fechado));
    }
}
