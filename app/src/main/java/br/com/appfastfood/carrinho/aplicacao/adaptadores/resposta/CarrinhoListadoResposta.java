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
public class CarrinhoListadoResposta {

    private Long id;

    @JsonProperty("produtos")
    private List<ProdutosReq> produtos;

    @JsonProperty("id_cliente")
    private String idCliente;

    @JsonProperty("valor_total")
    private Double valorTotal;


    @JsonProperty("status")
    private String status;


    public CarrinhoListadoResposta(Long id, List<ProdutosReq> produtos, String idCliente, Double valorTotal, String status) {
        this.id = id;
        this.produtos = produtos;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public CarrinhoListadoResposta(List<ProdutosReq> produtos, String idCliente, Double valorTotal, String status) {
        this.produtos = produtos;
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }
}
