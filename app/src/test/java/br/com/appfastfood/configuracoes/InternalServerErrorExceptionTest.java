package br.com.appfastfood.configuracoes;

import br.com.appfastfood.configuracoes.execption.InternalServerErrorException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalServerErrorExceptionTest {

    @Test
    void testInternalServerErrorException() {
        // Arrange
        InternalServerErrorException exception = new InternalServerErrorException();

        // Act
        String actualMessage = exception.getMessage();
        String expectedMessage = InternalServerErrorException.MESSAGER;

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }
}
