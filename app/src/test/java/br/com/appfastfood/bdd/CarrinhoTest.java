package br.com.appfastfood.bdd;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoListadoResposta;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.resposta.CarrinhoResposta;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.appfastfood.utils.CarrinhoHelper;
import com.google.gson.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/carrinho.feature")
public class CarrinhoTest {

    private Response response;
    private String URI = "/carrinho";

    private String URL_APPLICATION = "http://localhost:8080" + URI ;

    private List<CarrinhoListadoResposta> carrinhoListadoResposta;


    @Quando("que eu envio uma requisição para cadastrar um carrinho")
    public void que_eu_envio_uma_requisição_para_cadastrar_um_carrinho() throws IOException {

        CarrinhoRequisicao requisicao = CarrinhoHelper.criarCarrinhoRequisicao();

        response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .post(this.URL_APPLICATION);
    }

    @Quando("que eu envio uma requisição para cadastrar um carrinho, com id do produto não existente")
    public void que_eu_envio_uma_requisição_para_cadastrar_um_carrinho_com_id_do_produto_não_existente() throws IOException {
        CarrinhoRequisicao requisicao = CarrinhoHelper.criarCarrinhoIdProdutoInvalidoRequisicao();

        response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .post(this.URL_APPLICATION);
    }


    @Então("a resposta deve ter o status {int}")
    public void a_resposta_deve_ter_o_status(Integer status) {

        System.out.println(response.getBody().asString());

        response
            .then()
            .statusCode(status);
    }

    @Então("deve retornar uma mensagem de erro: {string} com status {int}")
    public void deve_retornar_uma_mensagem_de_erro_com_status(String mensagem, Integer status) {

        RequisicaoExcecao requisicaoExcecao = new RequisicaoExcecao(mensagem, status);
        Gson gson =  new Gson();

        this.response
                .then()
                .statusCode(status)
                .body(matchesJsonSchema(gson.toJson(requisicaoExcecao)));
    }

    @Quando("eu enviar esse id de carrinho, com valor: {int}, devo realizar uma requisição de remover carrinho")
    public void eu_enviar_esse_id_de_carrinho_com_valor_devo_realizar_uma_requisição_de_remover_carrinho(Integer id) {
        response = given()
                .when()
                .delete(this.URL_APPLICATION + "/" + id);
    }

    @Dado("que existem os seguintes carrinhos cadastrados:")
    public void que_existem_os_seguintes_carrinhos_cadastrados(DataTable dataTable) {

        List<CarrinhoListadoResposta> listarResposta = new ArrayList<>();

        List<Map<String, String>> dataTableMaps = dataTable.asMaps(String.class, String.class);

        dataTableMaps.stream().forEach(data -> {
            JsonArray jsonArray = new JsonParser().parse(data.get("produtos")).getAsJsonArray();
            ProdutosReq[] produtosReqs = new ProdutosReq[jsonArray.size()];

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);

                ProdutosReq produtosReq = ProdutosReq
                        .builder()
                        .idProduto(((JsonObject) jsonElement).get("id").getAsString())
                        .quantidadeProduto(((JsonObject) jsonElement).get("quantidade").getAsString())
                        .build();

                produtosReqs[i] = produtosReq;

               listarResposta.add(
                       CarrinhoListadoResposta
                        .builder()
                        .id(Long.valueOf(data.get("id")))
                        .produtos(Arrays.asList(produtosReqs))
                        .status(data.get("status"))
                        .idCliente(data.get("idCliente"))
                        .build()
               );
            }

        });

        this.carrinhoListadoResposta = listarResposta;
    }

    @Quando("eu envio uma requisição para listar os carrinhos")
    public void eu_envio_uma_requisição_para_listar_os_carrinhos() {
        this.response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get(this.URL_APPLICATION);
    }

    @Quando("eu envio uma requisição para fechar o carrinho com id {int}")
    public void eu_envio_uma_requisição_para_fechar_o_carrinho_com_id(Integer id) {
        this.response = given()
                .when()
                .put(this.URL_APPLICATION + "/fecharCarrinho/" + id);

    }

    @Dado("que exista um carrinho cadastrado, e envio uma requisição para atualizar o carrinho com id {int}")
    public void que_exista_um_carrinho_cadastrado_e_envio_uma_requisição_para_atualizar_o_carrinho_com_id(Integer id) throws IOException {

        CarrinhoRequisicao requisicao = CarrinhoHelper.atualizarCarrinhoRequisicao();

        response = given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requisicao)
                .put(this.URL_APPLICATION + "/" + id);
    }

    @Então("o retorno deve conter a seguinte estrutura {string}")
    public void o_retorno_deve_conter_a_seguinte_estrutura(String result) throws IOException {
        CarrinhoResposta resposta = CarrinhoHelper.atualizarCarrinhoResposta(result);
        assertEquals(new Gson().toJson(resposta), response.getBody().asString());
    }

    @Então("a resposta da listagem deve conter a seguinte estrutura {string}")
    public void a_resposta_da_listagem_deve_conter_a_seguinte_estrutura(String result) {
        List<CarrinhoListadoResposta> resposta = CarrinhoHelper.listagemCarrinhoResposta(result);
        assertEquals(new Gson().toJson(resposta), response.getBody().asString());
    }
}
