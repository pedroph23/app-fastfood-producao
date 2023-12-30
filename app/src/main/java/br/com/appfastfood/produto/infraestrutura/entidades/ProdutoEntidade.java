package br.com.appfastfood.produto.infraestrutura.entidades;


import jakarta.persistence.*;

@Entity(name = "produto")
public class ProdutoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
    @SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    private Long id;
    protected ProdutoEntidade() {}
    private String nome;
    private Double preco;
    private String uriImagem;
    private String categoria;
    private String descricao;

    public ProdutoEntidade(Long id, String nome, Double preco, String uriImagem, String categoria, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public ProdutoEntidade(String nome, Double preco, String uriImagem, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.uriImagem = uriImagem;
        this.categoria = categoria;
        this.descricao = descricao;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getUriImagem() {
        return uriImagem;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }
}

