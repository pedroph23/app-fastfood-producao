package br.com.appfastfood.carrinho.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsMessagesTest {

    @Test
    public void testExceptionsMessages() {
        // Verifica se as mensagens estão atribuídas corretamente
        assertEquals("Todos os campos são obrigatórios!", ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        assertEquals("Categoria não existente!", ExceptionsMessages.CATEGORIA_NAO_ENCONTRADA.getValue());
        assertEquals("Apenas campos de dígitos!", ExceptionsMessages.CAMPO_DIGITOS.getValue());
        assertEquals("Produtos é um campo obrigatório. Deve inserir pelo menos um produto!", ExceptionsMessages.CAMPO_PRODUTO_OBRIGATORIO.getValue());
        assertEquals("Id de carrinho não encontrado!", ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        assertEquals("URI com formato inválido!", ExceptionsMessages.URI_IMAGEM_FORMATO_INVALIDO.getValue());
        assertEquals("URI é um campo obrigatório!", ExceptionsMessages.URI_IMAGEM_OBRIGATORIO.getValue());
    }
}


