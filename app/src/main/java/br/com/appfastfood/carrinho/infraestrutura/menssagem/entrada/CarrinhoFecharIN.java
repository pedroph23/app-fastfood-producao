package br.com.appfastfood.carrinho.infraestrutura.menssagem.entrada;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrinhoFecharIN {
    @JsonProperty("id")
    private String id;

    public CarrinhoFecharIN(String id) {
        this.id = id;
    }

    public CarrinhoFecharIN() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}