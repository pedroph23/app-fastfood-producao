package br.com.appfastfood.carrinho.dominio.repositorios;

import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;

import java.util.List;

public interface CarrinhoRepositorio {
    void criar(Carrinho carrinho);
    Carrinho buscarPorId(Long id);

    void remover(Long id);

    List<CarrinhoEntidade> listar();

    Carrinho atualizar(Long id, Carrinho carrinho);

    void fecharCarrinho(Long id);
}
