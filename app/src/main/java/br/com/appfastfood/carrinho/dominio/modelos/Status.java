package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;

public class Status {
    private StatusCarrinhoEnum status;

    public Status(StatusCarrinhoEnum status) {
        this.status = status;
    }

    public StatusCarrinhoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusCarrinhoEnum status) {
        this.status = status;
    }
}
