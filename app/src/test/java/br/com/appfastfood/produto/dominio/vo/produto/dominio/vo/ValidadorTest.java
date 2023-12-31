package br.com.appfastfood.produto.dominio.vo.produto.dominio.vo;

import br.com.appfastfood.produto.dominio.vo.Validacoes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidacoesTest {

    @Test
    void validaTamanhoMaximoDoCampo() {
        String campo = "Hello, World!";
        int tamanhoMaximo = 10;

        boolean resultado = Validacoes.validaTamanhoMaximoDoCampo(campo, tamanhoMaximo);

        assertTrue(resultado);
    }


}
