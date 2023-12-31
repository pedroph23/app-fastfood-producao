package br.com.appfastfood.produto.dominio.vo.produto.dominio.vo;

import br.com.appfastfood.produto.dominio.vo.Descricao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DescricaoTest {

    @Test
    void getDescricao_DeveRetornarDescricaoCorreta() {
        String descricao = "Deliciosa pizza";
        Descricao objetoDescricao = new Descricao(descricao);

        assertEquals(descricao, objetoDescricao.getDescricao());
    }
}

