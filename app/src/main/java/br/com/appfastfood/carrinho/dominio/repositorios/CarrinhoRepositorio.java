package br.com.appfastfood.carrinho.dominio.repositorios;

import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import br.com.appfastfood.produto.dominio.modelos.Produto;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepositorio {
    void criar(Carrinho carrinho);
    Carrinho buscarPorId(Long id);

    void remover(Long id);

    List<CarrinhoEntidade> listar();

    Carrinho atualizar(Long id, Carrinho carrinho);
}
