package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import ch.qos.logback.core.net.server.Client;

import java.util.List;

public class Carrinho {
    private Long id;
    private List<ProdutoVO> produtoVOS;
    private Cliente cliente;
    private Double valorTotal;

    public Carrinho(List<ProdutoVO> produtoVOS, Cliente cliente, Double valorTotal) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Carrinho(List<ProdutoVO> produtoVOS, Cliente cliente) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
    }

    public Carrinho(Long id, List<ProdutoVO> produtoVOS, Cliente cliente, Double valorTotal) {
        this.id = id;
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProdutoVO> getProdutoVOS() {
        return produtoVOS;
    }

    public void setProdutoVOS(List<ProdutoVO> produtoVOS) {
        this.produtoVOS = produtoVOS;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
