package br.com.appfastfood.produto.dominio.vo.produto.dominio.vo;

import br.com.appfastfood.produto.dominio.vo.Preco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrecoTest {

    @Test
    void getPreco_DeveRetornarPrecoCorreto() {
         Double preco = 19.99;
        Preco objetoPreco = new Preco(preco);

        assertEquals(preco, objetoPreco.getPreco());
    }
}
