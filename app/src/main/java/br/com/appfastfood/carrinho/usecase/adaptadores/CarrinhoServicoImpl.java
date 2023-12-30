package br.com.appfastfood.carrinho.usecase.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.dominio.modelos.Cliente;
import br.com.appfastfood.carrinho.dominio.repositorios.CarrinhoRepositorio;
import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import br.com.appfastfood.carrinho.exceptions.ExceptionsMessages;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoServicoImpl implements CarrinhoServico {

    private final CarrinhoRepositorio carrinhoRepositorio;
    private final ProdutoServico produtoServico;

    public CarrinhoServicoImpl(CarrinhoRepositorio carrinhoRepositorio, ProdutoServico produtoServico) {
        this.carrinhoRepositorio = carrinhoRepositorio;
        this.produtoServico = produtoServico;
    }

    @Override
    public void cadastrar(CarrinhoRequisicao carrinhoRequisicao) {
        Produto produtoBuscaId = null;
        Double valorTotal = 0.0;
        List<ProdutoVO> produtosVO = new ArrayList<>();

        if(carrinhoRequisicao.getProdutos().isEmpty()) {
            throw new BadRequestException(ExceptionsMessages.CAMPO_PRODUTO_OBRIGATORIO.getValue());
        }

        for(ProdutosReq produtoReq : carrinhoRequisicao.getProdutos()){
            ProdutoVO produtoVO = new ProdutoVO(produtoReq.getIdProduto(), produtoReq.getQuantidadeProduto());
            produtoBuscaId = produtoServico.buscaProdutoPorId(Long.valueOf(produtoReq.getIdProduto()));
            valorTotal += produtoBuscaId.getPreco().getPreco() * Double.parseDouble(produtoReq.getQuantidadeProduto());
            produtosVO.add(produtoVO);
        }

        Carrinho carrinho = new Carrinho(produtosVO, new Cliente(carrinhoRequisicao.getIdCliente()), valorTotal);

        this.carrinhoRepositorio.criar(carrinho);
    }

    @Override
    public void remover(Long id) {
        this.carrinhoRepositorio.buscarPorId(id);
        this.carrinhoRepositorio.remover(id);
    }

    @Override
    public List<Carrinho> listar() {

        List<CarrinhoEntidade> carrinhoEntidades = this.carrinhoRepositorio.listar();

        List<Carrinho> carrinhoList = carrinhoEntidades.stream().map(carrinhoEntidade -> {
            List<ProdutoVO> produtosVO = carrinhoEntidade.getProdutos().stream().map(prodEnt -> new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto())).toList();
            return new Carrinho(carrinhoEntidade.getId(), produtosVO, new Cliente(carrinhoEntidade.getClienteId()), carrinhoEntidade.getValorTotal());
        }).toList();

        return carrinhoList;
    }

    @Override
    public Carrinho atualizar(Long id, CarrinhoRequisicao carrinhoRequisicao) {
        Produto produtoBuscaId = null;
        Double valorTotal = 0.0;

        List<ProdutoVO> produtosVO = new ArrayList();

        if(carrinhoRequisicao.getProdutos().isEmpty()) {
            throw new BadRequestException(ExceptionsMessages.CAMPO_PRODUTO_OBRIGATORIO.getValue());
        }

        for(ProdutosReq produtoReq : carrinhoRequisicao.getProdutos()){
            ProdutoVO produtoVO = new ProdutoVO(produtoReq.getIdProduto(), produtoReq.getQuantidadeProduto());
            produtoBuscaId = produtoServico.buscaProdutoPorId(Long.valueOf(produtoReq.getIdProduto()));
            Double quantidade = Double.parseDouble(produtoReq.getQuantidadeProduto());
            valorTotal += produtoBuscaId.getPreco().getPreco() * quantidade;
            produtosVO.add(produtoVO);
        }

        Carrinho carrinho = new Carrinho(produtosVO, new Cliente(carrinhoRequisicao.getIdCliente()), valorTotal);

        Carrinho carrinhoAlterado = this.carrinhoRepositorio.atualizar(id, carrinho);

        return carrinhoAlterado;
    }

}
