package br.com.appfastfood.utils;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoListadoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public abstract class CarrinhoHelper extends AbstractReader {

    private static String APPLICATION_PATH = System.getProperty("user.dir");
    private static String PATH_SCHEMA_REQUEST = APPLICATION_PATH + "/src/test/resources/schemas/carrinho/requisicao";
    private static String PATH_SCHEMA_RESPONSE = APPLICATION_PATH + "/src/test/resources/schemas/carrinho/resposta";
    public static CarrinhoRequisicao criarCarrinhoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<CarrinhoRequisicao>(){}.getType();

        String carrinhoCriarRequisicao = readJson( PATH_SCHEMA_REQUEST + "/CarrinhoCriarRequisicao.json");

        return gson.fromJson(carrinhoCriarRequisicao,collectionType);
    }

    public static CarrinhoRequisicao criarCarrinhoIdProdutoInvalidoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<CarrinhoRequisicao>(){}.getType();

        String carrinhoCriarRequisicao = readJson(PATH_SCHEMA_REQUEST + "/CarrinhoCriarIdProdutoInvalidoRequisicao.json");

        return gson.fromJson(carrinhoCriarRequisicao,collectionType);
    }

    public static CarrinhoRequisicao atualizarCarrinhoRequisicao() throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<CarrinhoRequisicao>(){}.getType();

        String carrinhoAtualizarRequisicao = readJson(PATH_SCHEMA_REQUEST + "/CarrinhoAtualizarRequisicao.json");

        return gson.fromJson(carrinhoAtualizarRequisicao,collectionType);
    }

    public static CarrinhoResposta atualizarCarrinhoResposta(String result) throws IOException {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<CarrinhoResposta>(){}.getType();

//        String carrinhoAtualizarResposta = readJson(PATH_SCHEMA_RESPONSE + "/CarrinhoAtualizarResposta.json");

        return gson.fromJson(result,collectionType);
    }

    public static List<CarrinhoListadoResposta> listagemCarrinhoResposta(String result) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<CarrinhoListadoResposta[]>(){}.getType();

        return List.of(gson.fromJson(result,collectionType));
    }
}
