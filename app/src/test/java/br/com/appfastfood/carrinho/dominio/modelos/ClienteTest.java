package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    public void testClienteValido() {
        String clienteNome = "123456789";

        Cliente cliente = new Cliente(clienteNome);

        assertEquals(clienteNome, cliente.getCliente());
    }

    @Test
    public void testClienteNuloOuVazio() {
        String clienteNome = null;

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            new Cliente(clienteNome);
        });

        assertEquals("Todos os campos são obrigatórios!", exception.getMessage());
    }

    @Test
    public void testClienteComCaracteresNaoNumericos() {
        String clienteNome = "123abc";

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            new Cliente(clienteNome);
        });

        assertEquals("Apenas campos de dígitos!", exception.getMessage());
    }

    @Test
    public void testClienteVazio() {
        String clienteNome = "";

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            new Cliente(clienteNome);
        });

        assertEquals("Todos os campos são obrigatórios!", exception.getMessage());
    }
}
