


package br.com.appfastfood.carrinho.infraestrutura.entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity(name = "carrinho")
public class CarrinhoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected CarrinhoEntidade() {}
    @ElementCollection
    List<ProdEnt> produtos;
    private String clienteId;
    private Double valorTotal;
    private String status;

    public CarrinhoEntidade(Long id, List<ProdEnt> produtos, String clienteId, Double valorTotal, String status) {
        this.id = id;
        this.produtos = produtos;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public CarrinhoEntidade(List<ProdEnt> produtos, String clienteId, Double valorTotal, String status) {
        this.produtos = produtos;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public CarrinhoEntidade(Long id, List<ProdEnt> produtos, String clienteId, Double valorTotal) {
        this.id = id;
        this.produtos = produtos;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ProdEnt> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdEnt> produtos) {
        this.produtos = produtos;
    }
}

