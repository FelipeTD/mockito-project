package tortora.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class GeradorDeNumerosTest {

    private MockedStatic<GeradorDeNumeros> gerador = Mockito.mockStatic(GeradorDeNumeros.class);

    @AfterEach
    void limparMockedStatic() {
        gerador.close();
    }

    @Test
    void validaGeracaoListaDeNumeros() {

        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        gerador.when(() -> GeradorDeNumeros.geraNumerosAleatorios(anyInt()))
                .thenReturn(integers);

        assertEquals(integers, GeradorDeNumeros.geraNumerosAleatorios(8));
    }

    @Test
    void validaGeracaoListaDeNumerosSemInformarTamanho() {
        List<Integer> integers = List.of(10, 9, 8, 6);

        gerador.when(GeradorDeNumeros::geraNumerosAleatorios).thenReturn(integers);

        assertEquals(integers, GeradorDeNumeros.geraNumerosAleatorios());
    }

}
