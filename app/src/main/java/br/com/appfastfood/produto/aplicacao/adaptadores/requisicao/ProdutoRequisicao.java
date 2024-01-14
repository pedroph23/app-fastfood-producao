package br.com.appfastfood.produto.aplicacao.adaptadores.requisicao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder()
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoRequisicao {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("preco")
    private Double preco;

    @JsonProperty("uriImagem")
    private String uriImagem;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("descricao")
    private String descricao;
}
