package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    public void testStatus() {
        StatusCarrinhoEnum statusEnum = StatusCarrinhoEnum.ABERTO;

        Status status = new Status(statusEnum);

        // Verifica se a criação do objeto ocorreu corretamente
        assertEquals(statusEnum, status.getStatus());

        // Modifica o status
        StatusCarrinhoEnum novoStatus = StatusCarrinhoEnum.FECHADO;
        status.setStatus(novoStatus);

        // Verifica se o método setStatus funciona corretamente
        assertEquals(novoStatus, status.getStatus());
    }
}
