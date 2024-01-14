package br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProdutoRespostaTest {

    @Test
    void testConstrutorComBuilder() {
        ProdutoResposta produtoResposta = ProdutoResposta.builder()
                .id("1")
                .nome("ProdutoTeste")
                .preco(10.0)
                .uriImagem("imagem.jpg")
                .categoria("CategoriaTeste")
                .descricao("Descrição do produto")
                .build();

        assertThat(produtoResposta).isNotNull();
        assertThat(produtoResposta.getId()).isEqualTo("1");
        assertThat(produtoResposta.getNome()).isEqualTo("ProdutoTeste");
        assertThat(produtoResposta.getPreco()).isEqualTo(10.0);
        assertThat(produtoResposta.getUriImagem()).isEqualTo("imagem.jpg");
        assertThat(produtoResposta.getCategoria()).isEqualTo("CategoriaTeste");
        assertThat(produtoResposta.getDescricao()).isEqualTo("Descrição do produto");
    }

    @Test
    void testSerializacaoJson() throws Exception {
        ProdutoResposta produtoResposta = ProdutoResposta.builder()
                .id("1")
                .nome("ProdutoTeste")
                .preco(10.0)
                .uriImagem("imagem.jpg")
                .categoria("CategoriaTeste")
                .descricao("Descrição do produto")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = objectMapper.writeValueAsString(produtoResposta);

        String expectedJson = "{\n" +
                "  \"id\" : \"1\",\n" +
                "  \"nome\" : \"ProdutoTeste\",\n" +
                "  \"preco\" : 10.0,\n" +
                "  \"uriImagem\" : \"imagem.jpg\",\n" +
                "  \"categoria\" : \"CategoriaTeste\",\n" +
                "  \"descricao\" : \"Descrição do produto\"\n" +
                "}";

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }
}
