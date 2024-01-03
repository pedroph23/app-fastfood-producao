package br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta;

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
public class CarrinhoResposta {

    @JsonProperty("produtos")
    private List<ProdutosReq> produtos;

    @JsonProperty("id_cliente")
    private String idCliente;

    @JsonProperty("valor_total")
    private Double valorTotal;


    public CarrinhoResposta(List<ProdutosReq> produtos, String idCliente, Double valorTotal) {
        this.produtos = produtos;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
    }
}
