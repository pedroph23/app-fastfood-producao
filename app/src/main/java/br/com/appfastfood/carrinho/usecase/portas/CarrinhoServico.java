package br.com.appfastfood.carrinho.usecase.portas;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarrinhoServico {
    void cadastrar (CarrinhoRequisicao carrinhoRequisicao);
    void remover (Long id);
    List<Carrinho> listar();

    Carrinho atualizar(Long id, CarrinhoRequisicao carrinhoRequisicao);

    void fecharCarrinho(Long id);

    @Transactional
    void fecharCarrinho(String mensagem);
}
