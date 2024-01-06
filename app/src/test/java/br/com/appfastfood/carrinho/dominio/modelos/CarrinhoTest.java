package br.com.appfastfood.carrinho.dominio.modelos;

import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CarrinhoTest {

    @Test
    public void testCarrinhoComId() {
        Long id = 1L;
        List<ProdutoVO> produtos = Arrays.asList(mock(ProdutoVO.class));
        Cliente cliente = mock(Cliente.class);
        Double valorTotal = 100.0;
        StatusCarrinhoEnum status = StatusCarrinhoEnum.ABERTO;

        Carrinho carrinho = new Carrinho(id, produtos, cliente, valorTotal, status);

        assertEquals(id, carrinho.getId());
        assertEquals(produtos, carrinho.getProdutoVOS());
        assertEquals(cliente, carrinho.getCliente());
        assertEquals(valorTotal, carrinho.getValorTotal());
        assertEquals(status, carrinho.getStatus());
    }

    @Test
    public void testCarrinhoSemId() {
        List<ProdutoVO> produtos = Arrays.asList(mock(ProdutoVO.class));
        Cliente cliente = mock(Cliente.class);
        StatusCarrinhoEnum status = StatusCarrinhoEnum.FECHADO;

        Carrinho carrinho = new Carrinho(produtos, cliente, status);

        assertEquals(produtos, carrinho.getProdutoVOS());
        assertEquals(cliente, carrinho.getCliente());
        assertEquals(status, carrinho.getStatus());
    }

    @Test
    public void testAtualizarCarrinho() {
        Carrinho carrinho = new Carrinho(Arrays.asList(mock(ProdutoVO.class)), mock(Cliente.class), StatusCarrinhoEnum.ABERTO);

        List<ProdutoVO> novosProdutos = Arrays.asList(mock(ProdutoVO.class));
        Cliente novoCliente = mock(Cliente.class);
        Double novoValorTotal = 150.0;

        carrinho.setProdutoVOS(novosProdutos);
        carrinho.setCliente(novoCliente);
        carrinho.setValorTotal(novoValorTotal);

        assertEquals(novosProdutos, carrinho.getProdutoVOS());
        assertEquals(novoCliente, carrinho.getCliente());
        assertEquals(novoValorTotal, carrinho.getValorTotal());
    }
}
