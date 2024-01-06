package br.com.appfastfood.produto.dominio.vo.produto.dominio.vo.enums;

import br.com.appfastfood.produto.dominio.vo.enums.CategoriaEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoriaEnumTest {

    @Test
    public void testCategoriaEnumLanche() {
        CategoriaEnum categoria = CategoriaEnum.lanche;
        assertNotNull(categoria);
    }

    @Test
    public void testCategoriaEnumBebida() {
        CategoriaEnum categoria = CategoriaEnum.bebida;
        assertNotNull(categoria);
    }

    @Test
    public void testCategoriaEnumSobremesa() {
        CategoriaEnum categoria = CategoriaEnum.sobremesa;
        assertNotNull(categoria);
    }

}
