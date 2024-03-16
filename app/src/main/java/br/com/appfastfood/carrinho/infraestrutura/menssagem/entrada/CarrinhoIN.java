package br.com.appfastfood.carrinho.infraestrutura.menssagem.entrada;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
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
public class CarrinhoIN {

    @JsonProperty("produtos")
    private List<ProdutosReq> produtos;

    private String idCliente;

    public CarrinhoIN(List<ProdutosReq> produtos, String idCliente){
        this.idCliente = idCliente;
        this.produtos = produtos;
    }
}