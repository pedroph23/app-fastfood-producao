package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.exceptions.ExceptionsMessages;
import br.com.appfastfood.configuracoes.execption.BadRequestException;

public class Cliente {
    private String cliente;

    public Cliente(String cliente) {
        this.cliente = this.validarCliente(cliente);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    private String validarCliente(String cliente) {
        if (cliente == null || cliente.isEmpty()) {
            throw new BadRequestException(ExceptionsMessages.CAMPOS_OBRIGATORIOS.getValue());
        }

        for (char caractere : cliente.toCharArray()) {
            if (!Character.isDigit(caractere)) {
                throw new BadRequestException(ExceptionsMessages.CAMPO_DIGITOS.getValue()); // Se encontrar algum caractere que não é um dígito, a string não contém apenas números
            }
        }

        return cliente;
    }
}
