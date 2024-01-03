package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import ch.qos.logback.core.net.server.Client;

import java.util.List;

public class Carrinho {
    private Long id;
    private List<ProdutoVO> produtoVOS;
    private Cliente cliente;
    private Double valorTotal;
    private StatusCarrinhoEnum status;

    public Carrinho(List<ProdutoVO> produtoVOS, Cliente cliente, Double valorTotal, StatusCarrinhoEnum status) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Carrinho(List<ProdutoVO> produtoVOS, Cliente cliente, StatusCarrinhoEnum status) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.status = status;
    }

    public Carrinho(Long id, List<ProdutoVO> produtoVOS, Cliente cliente, Double valorTotal, StatusCarrinhoEnum status) {
        this.id = id;
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Carrinho(List<ProdutoVO> produtoVOS, Cliente cliente, Double valorTotal) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public StatusCarrinhoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusCarrinhoEnum status) {
        this.status = status;
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
