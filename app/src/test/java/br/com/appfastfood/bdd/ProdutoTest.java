package br.com.appfastfood.bdd;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import br.com.appfastfood.produto.aplicacao.adaptadores.resposta.ProdutoResposta;
import br.com.appfastfood.utils.CarrinhoHelper;
import br.com.appfastfood.utils.ProdutoHelper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Mas;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/produto.feature")
public class ProdutoTest {

    private Response response;

    private String URI = "/produtos";

    private String URL_APPLICATION = "http://localhost:8080" + URI ;

    /** Cadastrar Produto */

    @Quando("eu enviar uma requisição para cadastrar um produto")
    public void eu_enviar_uma_requisição_para_cadastrar_um_produto() throws IOException {

        ProdutoRequisicao requisicao = ProdutoHelper.criarProdutoRequisicao();

        this.response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .post(this.URL_APPLICATION);
    }

    /** Remover Produto */

    @Quando("eu enviar esse id de produto, com valor: {int}, devo realizar uma requisição")
    public void eu_enviar_esse_id_de_produto_com_valor_devo_realizar_uma_requisição(Integer id) {
        this.response = given()
                .when()
                .delete(this.URL_APPLICATION + "/" + id);
    }

    @Então("a resposta deve conter status {int}")
    public void a_resposta_deve_conter_status(Integer status) {
        this.response
                .then()
                .statusCode(status);
    }

    @Então("a resposta deve retornar uma mensagem de erro: {string} com status {int}")
    public void a_resposta_deve_retornar_uma_mensagem_de_erro_com_status(String mensagem, Integer status) {

        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, status);
        Gson gson = new Gson();

        RequisicaoExcecao respostaExcessao = gson.fromJson(this.response.getBody().asString(), RequisicaoExcecao.class);


        System.out.println(this.response.getBody().asString());


        this.response
                .then()
                .statusCode(status);

        assertEquals(requisicaoExcecao.getMensagem(), respostaExcessao.getMensagem());
    }


    @Quando("eu enviar uma requisição para atualizar o produto com o id {int}")
    public void eu_enviar_uma_requisição_para_atualizar_o_produto_com_o_id_e_os_seguintes_dados(Integer id) throws IOException {


        ProdutoRequisicao requisicao = ProdutoHelper.atualizarProdutoRequisicao();

        this.response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .put(this.URL_APPLICATION + "/" + id);
    }

    @Quando("eu enviar uma requisição para buscar produtos por categoria {string}")
    public void eu_enviar_uma_requisição_para_buscar_produtos_por_categoria(String categoria) {
        this.response = given()
                .when()
                .get(this.URL_APPLICATION + "?categoria=" + categoria);

    }
    @Então("a resposta deve conter uma lista de produtos na categoria {string} e conter status {int}")
    public void a_resposta_deve_conter_uma_lista_de_produtos_na_categoria(String categoria, Integer status) {

        Gson gson = new Gson();
        Type listaTipo = new TypeToken<List<ProdutoResposta>>() {}.getType();

        ResponseBody responseBody =  this.response.getBody();
        List<ProdutoResposta> listaProdutoResposta = gson.fromJson(responseBody.asString(), listaTipo);

        assertEquals(listaProdutoResposta.isEmpty(), false);
        listaProdutoResposta.forEach(produtoResposta -> assertEquals(produtoResposta.getCategoria(), categoria));
        this.response
                .then()
                .statusCode(status);
    }

    @Quando("eu enviar uma requisição para cadastrar um produto com um campo invalido")
    public void eu_enviar_uma_requisição_para_cadastrar_um_produto_com_um_campo_nome_sem_valor() throws IOException {
        ProdutoRequisicao requisicao = ProdutoHelper.criarProdutoCampoInvalidoRequisicao();

        this.response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .post(this.URL_APPLICATION);
    }

    @Então("o retorno da atualizacao do produto deve conter a seguinte estrutura {string}")
    public void o_retorno_deve_conter_a_seguinte_estrutura(String result) {
        assertEquals(result, response.getBody().asString());
    }


}
