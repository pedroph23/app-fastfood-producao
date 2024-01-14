package br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrinhoRequisicao {

    @JsonProperty("produtos")
    private List<ProdutosReq> produtos;

    private String idCliente;

    public CarrinhoRequisicao(List<ProdutosReq> produtos, String idCliente){
        this.idCliente = idCliente;
        this.produtos = produtos;
    }
}
