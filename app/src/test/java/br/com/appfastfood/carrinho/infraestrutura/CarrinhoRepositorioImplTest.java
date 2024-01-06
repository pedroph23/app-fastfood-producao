package br.com.appfastfood.carrinho.infraestrutura;

import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.dominio.modelos.Cliente;
import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import br.com.appfastfood.carrinho.infraestrutura.entidades.ProdEnt;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarrinhoRepositorioImplTest {

    @Mock
    private SpringDataCarrinhoRepository springDataCarrinhoRepository;

    private CarrinhoRepositorioImpl carrinhoRepositorio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carrinhoRepositorio = new CarrinhoRepositorioImpl(springDataCarrinhoRepository);
    }

    @Test
    public void testCriar() {
        Carrinho carrinho = new Carrinho(
                Arrays.asList(new ProdutoVO("1", "2")),
                new Cliente("123"),
                100.0,
                StatusCarrinhoEnum.ABERTO
        );

        when(springDataCarrinhoRepository.save(any())).thenReturn(new CarrinhoEntidade(1L, Arrays.asList(new ProdEnt("1", "2")),"1", 1.0, StatusCarrinhoEnum.ABERTO.getNome() ));

        carrinhoRepositorio.criar(carrinho);

        verify(springDataCarrinhoRepository, times(1)).save(any());
    }

    @Test
    public void testBuscarPorId() {
        Long id = 1L;

        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(id, Arrays.asList(new ProdEnt("1", "2")), "123", 100.0, "ABERTO");

        when(springDataCarrinhoRepository.findById(id)).thenReturn(Optional.of(carrinhoEntidade));

        Carrinho carrinho = carrinhoRepositorio.buscarPorId(id);

        assertEquals(Arrays.asList(new ProdutoVO("1", "2")).get(0).getIdProduto(), carrinho.getProdutoVOS().get(0).getIdProduto());
        assertEquals("123", carrinho.getCliente().getCliente());
        assertEquals(100.0, carrinho.getValorTotal());
        assertEquals(StatusCarrinhoEnum.ABERTO, carrinho.getStatus());
    }

    @Test
    public void testBuscarPorId_IdNaoEncontrado() {
        Long id = 1L;

        when(springDataCarrinhoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> carrinhoRepositorio.buscarPorId(id));
    }

    @Test
    public void testRemoverCarrinhoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(carrinhoId, Arrays.asList(), "123", 0.0, StatusCarrinhoEnum.ABERTO.getNome());
        when(springDataCarrinhoRepository.findById(anyLong())).thenReturn(Optional.of(carrinhoEntidade));

        // Act
        assertDoesNotThrow(() -> carrinhoRepositorio.remover(carrinhoId));

        // Assert
        verify(springDataCarrinhoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testListarCarrinhos() {
        // Arrange
        List<CarrinhoEntidade> carrinhoEntidades = Arrays.asList(
                new CarrinhoEntidade(1L, Arrays.asList(), "123", 0.0, StatusCarrinhoEnum.ABERTO.getNome()),
                new CarrinhoEntidade(2L, Arrays.asList(), "456", 0.0, StatusCarrinhoEnum.FECHADO.getNome())
        );
        when(springDataCarrinhoRepository.findAll()).thenReturn(carrinhoEntidades);

        // Act
        List<CarrinhoEntidade> result = carrinhoRepositorio.listar();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testAtualizarCarrinhoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        Carrinho carrinhoAtualizado = new Carrinho(carrinhoId, Arrays.asList(), new Cliente("123"), 0.0, StatusCarrinhoEnum.ABERTO);
        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(carrinhoId, Arrays.asList(), "123", 0.0, StatusCarrinhoEnum.ABERTO.getNome());
        when(springDataCarrinhoRepository.findById(anyLong())).thenReturn(Optional.of(carrinhoEntidade));
        when(springDataCarrinhoRepository.save(any(CarrinhoEntidade.class))).thenReturn(carrinhoEntidade);

        // Act
        Carrinho carrinho = carrinhoRepositorio.atualizar(carrinhoId, carrinhoAtualizado);

        // Assert
        assertNotNull(carrinho);
        assertEquals(carrinhoId, carrinho.getId());
        assertEquals(StatusCarrinhoEnum.ABERTO, carrinho.getStatus());
    }

    @Test
    public void testAtualizarCarrinhoNaoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        Carrinho carrinhoAtualizado = new Carrinho(carrinhoId, Arrays.asList(), new Cliente("123"), 0.0, StatusCarrinhoEnum.ABERTO);
        when(springDataCarrinhoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BadRequestException.class, () -> carrinhoRepositorio.atualizar(carrinhoId, carrinhoAtualizado));

        // Assert
        verify(springDataCarrinhoRepository, never()).save(any(CarrinhoEntidade.class));
    }

    @Test
    public void testFecharCarrinhoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(carrinhoId, Arrays.asList(), "123", 0.0, StatusCarrinhoEnum.ABERTO.getNome());
        when(springDataCarrinhoRepository.existsById(anyLong())).thenReturn(true);
        when(springDataCarrinhoRepository.findById(anyLong())).thenReturn(Optional.of(carrinhoEntidade));

        // Act
        assertDoesNotThrow(() -> carrinhoRepositorio.fecharCarrinho(carrinhoId));

        // Assert
        verify(springDataCarrinhoRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testFecharCarrinhoNaoExistente() {
        // Arrange
        Long carrinhoId = 1L;
        when(springDataCarrinhoRepository.existsById(anyLong())).thenReturn(false);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> carrinhoRepositorio.fecharCarrinho(carrinhoId));

        // Assert
        verify(springDataCarrinhoRepository, never()).findById(anyLong());
    }
}
