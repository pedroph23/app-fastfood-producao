package br.com.appfastfood.utils;


import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ProdutoHelper extends AbstractReader {

    private static String APPLICATION_PATH = System.getProperty("user.dir");
    private static String PATH_SCHEMA_REQUEST = APPLICATION_PATH + "/src/test/resources/schemas/produto/requisicao";
    public static ProdutoRequisicao criarProdutoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ProdutoRequisicao>(){}.getType();

        String produtoCriarRequisicao = readJson(PATH_SCHEMA_REQUEST + "/ProdutoCriarRequisicao.json");

        return gson.fromJson(produtoCriarRequisicao,collectionType);
    }

    public static ProdutoRequisicao criarProdutoCampoInvalidoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ProdutoRequisicao>(){}.getType();

        String ProdutoCriarCampoObrigatorioRequisicao = readJson(PATH_SCHEMA_REQUEST + "/ProdutoCriarCampoObrigatorioRequisicao.json");

        return gson.fromJson(ProdutoCriarCampoObrigatorioRequisicao,collectionType);
    }

    public static ProdutoRequisicao atualizarProdutoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ProdutoRequisicao>() {
        }.getType();

        String produtoAtualizarRequisicao = readJson(PATH_SCHEMA_REQUEST + "/ProdutoAtualizarRequisicao.json");

        return gson.fromJson(produtoAtualizarRequisicao, collectionType);
    }

    public static CarrinhoResposta atualizarProdutoResposta(String result) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ProdutoRequisicao>() {}.getType();

        return gson.fromJson(result, collectionType);
    }
}
