package br.com.appfastfood.produto.infraestrutura;

import br.com.appfastfood.produto.infraestrutura.entidades.ProdutoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataProdutoRepository extends JpaRepository<ProdutoEntidade, Long> {
    Optional<List<ProdutoEntidade>> findProdutoEntidadeByCategoria(String categoria);
    ProdutoEntidade findProdutoById(Long id);
}

