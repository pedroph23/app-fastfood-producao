package br.com.appfastfood.carrinho.infraestrutura;

import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCarrinhoRepository extends JpaRepository<CarrinhoEntidade, Long> { }

