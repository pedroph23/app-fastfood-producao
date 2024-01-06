package br.com.appfastfood.produto.aplicacao.adaptadores.requisicao;

import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequisicaoExcecaoTest {

    @Test
    public void testRequisicaoExcecao() {
        // Criar um objeto fictício para o teste
        String mensagem = "Erro na requisição";
        int codigo = 404;

        // Criar uma instância da classe RequisicaoExcecao
        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, codigo);

        // Verificar se os atributos foram configurados corretamente
        assertEquals(mensagem, requisicaoExcecao.getMensagem());
        assertEquals(codigo, requisicaoExcecao.getCodigo());
    }
}
