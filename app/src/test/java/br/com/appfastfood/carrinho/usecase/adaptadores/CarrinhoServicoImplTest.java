package br.com.appfastfood.carrinho.usecase.adaptadores;

import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.CarrinhoRequisicao;
import br.com.appfastfood.carrinho.aplicacao.adaptadores.requisicao.ProdutosReq;
import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.dominio.modelos.Cliente;
import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.dominio.repositorios.CarrinhoRepositorio;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.produto.dominio.modelos.Produto;
import br.com.appfastfood.produto.dominio.vo.Descricao;
import br.com.appfastfood.produto.dominio.vo.Nome;
import br.com.appfastfood.produto.dominio.vo.Preco;
import br.com.appfastfood.produto.dominio.vo.UriImagem;
import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class CarrinhoServicoImplTest {

    @Mock
    private CarrinhoRepositorio carrinhoRepositorio;

    @Mock
    private ProdutoServico produtoServico;

    @InjectMocks
    private CarrinhoServicoImpl carrinhoServico;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carrinhoServico = new CarrinhoServicoImpl(carrinhoRepositorio, produtoServico);
    }

    @Test
    public void testCadastrarCarrinhoComProdutos() {
        // Arrange
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build()), "123");
        Produto produto = new Produto(1L, new Nome("Produto Teste"),  new Preco(10.0), new UriImagem("http://www"), CategoriaEnum.bebida, new Descricao("Ola mundo") );
        when(produtoServico.buscaProdutoPorId(anyLong())).thenReturn(produto);

        // Act
        assertDoesNotThrow(() -> carrinhoServico.cadastrar(carrinhoRequisicao));

        // Assert
        verify(carrinhoRepositorio, times(1)).criar(any(Carrinho.class));
    }

    @Test
    public void testCadastrarCarrinhoSemProdutos() {
        // Arrange
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(), "123");

        // Act & Assert
        assertThrows(BadRequestException.class, () -> carrinhoServico.cadastrar(carrinhoRequisicao));

        // Assert
        verify(carrinhoRepositorio, never()).criar(any(Carrinho.class));
    }

    @Test
    public void testRemoverCarrinhoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        Carrinho carrinho = new Carrinho(carrinhoId, Arrays.asList(), new Cliente("123"), 0.0, StatusCarrinhoEnum.ABERTO);
        when(carrinhoRepositorio.buscarPorId(anyLong())).thenReturn(carrinho);

        // Act
        assertDoesNotThrow(() -> carrinhoServico.remover(carrinhoId));

        // Assert
        verify(carrinhoRepositorio, times(1)).remover(anyLong());
    }

    @Test
    public void testRemoverCarrinhoNaoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        when(carrinhoRepositorio.buscarPorId(anyLong())).thenThrow(BadRequestException.class);
        // Act & Assert
        assertThrows(BadRequestException.class, () -> carrinhoServico.remover(carrinhoId));

        // Assert
        verify(carrinhoRepositorio, never()).remover(anyLong());
    }

    @Test
    public void testListarCarrinhos() {
        // Arrange
        List<CarrinhoEntidade> carrinhoEntidades = Arrays.asList(
                new CarrinhoEntidade(1L, Arrays.asList(), "123", 0.0, StatusCarrinhoEnum.ABERTO.getNome()),
                new CarrinhoEntidade(2L, Arrays.asList(), "456", 0.0, StatusCarrinhoEnum.FECHADO.getNome())
        );
        when(carrinhoRepositorio.listar()).thenReturn(carrinhoEntidades);

        // Act
        List<Carrinho> carrinhos = carrinhoServico.listar();

        // Assert
        assertNotNull(carrinhos);
        assertEquals(2, carrinhos.size());
    }

    @Test
    public void testAtualizarCarrinhoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        Carrinho carrinhoAtualizado = new Carrinho(carrinhoId, Arrays.asList(), new Cliente("123"), 0.0, StatusCarrinhoEnum.ABERTO);
        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(ProdutosReq.builder().idProduto("1").quantidadeProduto("2").build()), "123");
        Produto produto = new Produto(1L, new Nome("Produto Teste"),  new Preco(10.0), new UriImagem("http://www"), CategoriaEnum.bebida, new Descricao("Ola mundo") );
        when(produtoServico.buscaProdutoPorId(anyLong())).thenReturn(produto);
        when(carrinhoRepositorio.atualizar(anyLong(), any(Carrinho.class))).thenReturn(carrinhoAtualizado);

        // Act
        Carrinho carrinho = carrinhoServico.atualizar(carrinhoId, carrinhoRequisicao);

        // Assert
        assertNotNull(carrinho);
        assertEquals(carrinhoId, carrinho.getId());
        assertEquals(StatusCarrinhoEnum.ABERTO, carrinho.getStatus());
    }

    @Test
    public void testAtualizarCarrinhoNaoExistente() {
        // Arrange
        Long carrinhoId = 1L;
//        CarrinhoRequisicao carrinhoRequisicao = new CarrinhoRequisicao(Arrays.asList(new ProdutosReq("1", "2")), "123");
//        Produto produto = new Produto(1L, "Produto Teste", "Descri
    }
}
