package br.com.appfastfood.produto.aplicacao.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.CarrinhoController;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.produto.aplicacao.adaptadores.requisicao.ProdutoRequisicao;
import br.com.appfastfood.produto.aplicacao.adaptadores.resposta.ProdutoResposta;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.*;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProdutoControllerTest {

    @Mock
    private ProdutoServico produtoServico;

    @Mock
    private Log logger;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoController = new ProdutoController(produtoServico, logger);
    }
    @Test
    void testCadastrarProduto() {
        // Arrange
        ProdutoRequisicao requisicao = ProdutoRequisicao.builder().nome("NomeProduto").preco(10.0).uriImagem("https://wwww.").categoria("lanche").descricao("Descrição").build();
        Produto produtoMock = new Produto(new Nome(requisicao.getNome()), new Preco(requisicao.getPreco()),
                new UriImagem(requisicao.getUriImagem()), new Categoria(requisicao.getCategoria()).getCategoria(),
                new Descricao(requisicao.getDescricao()));

        // Act
        ResponseEntity responseEntity = produtoController.cadastrar(requisicao);

        // Assert
        verify(produtoServico, Mockito.times(1)).cadastrar(any(Produto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        ProdutoResposta produtoResposta = (ProdutoResposta) responseEntity.getBody();
        assertEquals(requisicao.getNome(), produtoResposta.getNome());
        // Adicione mais verificações conforme necessário para outros campos.
    }

    @Test
    void testRemoverProduto() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity responseEntity = produtoController.remover(id);

        // Assert
        verify(produtoServico, Mockito.times(1)).remover(eq(id));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testAtualizarProduto() {
        // Arrange
        Long id = 1L;
        ProdutoRequisicao requisicao = ProdutoRequisicao.builder()
                .nome("NomeAtualizado")
                .preco(15.0)
                .uriImagem("http://www")
                .categoria("bebida")
                .descricao("NovaDescrição")
                .build();

        Produto produtoMock = new Produto(new Nome(requisicao.getNome()), new Preco(requisicao.getPreco()),
                new UriImagem(requisicao.getUriImagem()), new Categoria(requisicao.getCategoria()).getCategoria(),
                new Descricao(requisicao.getDescricao()));

        when(produtoServico.atualizar(eq(id), any(Produto.class))).thenReturn(produtoMock);

        // Act
        ResponseEntity responseEntity = produtoController.atualizar(id, requisicao);

        // Assert
        verify(produtoServico, Mockito.times(1)).atualizar(eq(id), any(Produto.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProdutoResposta produtoResposta = (ProdutoResposta) responseEntity.getBody();
        assertEquals(requisicao.getNome(), produtoResposta.getNome());
        // Adicione mais verificações conforme necessário para outros campos.
    }

    @Test
    void testBuscarPorCategoria() {
        // Arrange
        String categoria = "lanche";
        List<Produto> produtosMock = Arrays.asList(
                new Produto(1l,new Nome("Produto1"), new Preco(10.0), new UriImagem("http://www"), CategoriaEnum.lanche, new Descricao("Descrição1")),
                new Produto(2l,new Nome("Produto2"), new Preco(12.0), new UriImagem("http://www"), CategoriaEnum.lanche, new Descricao("Descrição2"))
        );

        when(produtoServico.buscarPorCategoria(eq(categoria))).thenReturn(produtosMock);

        // Act
        ResponseEntity responseEntity = produtoController.buscarPorCategoria(categoria);

        // Assert
        verify(produtoServico, Mockito.times(1)).buscarPorCategoria(eq(categoria));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<ProdutoResposta> produtosResposta = (List<ProdutoResposta>) responseEntity.getBody();
        assertEquals(produtosMock.size(), produtosResposta.size());
        // Adicione mais verificações conforme necessário para outros campos.
    }
}
