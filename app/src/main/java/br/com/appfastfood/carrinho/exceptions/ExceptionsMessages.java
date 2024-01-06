package br.com.appfastfood.carrinho.exceptions;

public enum ExceptionsMessages {

    CAMPOS_OBRIGATORIOS("Todos os campos são obrigatórios!"),
    CATEGORIA_NAO_ENCONTRADA("Categoria não existente!"),
    CAMPO_DIGITOS("Apenas campos de dígitos!"),
    CAMPO_PRODUTO_OBRIGATORIO("Produtos é um campo obrigatório. Deve inserir pelo menos um produto!"),
    ID_NAO_ENCONTRADO("Id de carrinho não encontrado!"),
    URI_IMAGEM_FORMATO_INVALIDO("URI com formato inválido!"),
    URI_IMAGEM_OBRIGATORIO("URI é um campo obrigatório!");


    private final String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

