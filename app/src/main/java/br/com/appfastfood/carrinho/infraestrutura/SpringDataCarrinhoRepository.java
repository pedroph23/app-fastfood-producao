package br.com.appfastfood.carrinho.infraestrutura;

import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCarrinhoRepository extends JpaRepository<CarrinhoEntidade, Long> {
    @Override
    Optional<CarrinhoEntidade> findById(Long aLong);
}

