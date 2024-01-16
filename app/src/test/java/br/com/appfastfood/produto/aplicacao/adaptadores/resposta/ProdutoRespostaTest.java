package br.com.appfastfood.produto.aplicacao.adaptadores.resposta;

import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoRespostaTest {

    @Mock
    private Double precoMock;

    @InjectMocks
    private ProdutoRequisicao produtoRequisicao;

    @Test
    public void testMetodosGet() {
        String nome = "Hambúrguer";
        String uriImagem = "imagem.jpg";
        String categoria = "Alimentos";
        String descricao = "Delicioso hambúrguer caseiro";

        produtoRequisicao = ProdutoRequisicao.builder()
                .nome(nome)
                .preco(precoMock)
                .uriImagem(uriImagem)
                .categoria(categoria)
                .descricao(descricao)
                .build();

        ProdutoResposta resposta = new ProdutoResposta("0", nome, precoMock, uriImagem, categoria, descricao);

        assertEquals(nome, produtoRequisicao.getNome());
        assertEquals(precoMock, produtoRequisicao.getPreco());
        assertEquals(uriImagem, produtoRequisicao.getUriImagem());
        assertEquals(categoria, produtoRequisicao.getCategoria());
        assertEquals(descricao, produtoRequisicao.getDescricao());
        assertEquals(nome, resposta.getNome());
        assertEquals(precoMock, resposta.getPreco());
        assertEquals(uriImagem, resposta.getUriImagem());
        assertEquals(categoria, resposta.getCategoria());
        assertEquals(descricao, resposta.getDescricao());
    }
}
