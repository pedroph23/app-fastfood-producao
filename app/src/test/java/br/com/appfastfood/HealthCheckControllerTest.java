package br.com.appfastfood;

import br.com.appfastfood.produto.aplicacao.adaptadores.ProdutoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
public class HealthCheckControllerTest {

    @InjectMocks
    private HealthCheckController checkController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        checkController = new HealthCheckController();
    }

    @Test
    void testHealthCheck() {
        String result = checkController.healthCheck();
        assertEquals("OK", result);
    }
}
