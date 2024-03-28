package tortora.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListaTest {

    @Mock
    private List<String> letras;

    @Test
    void adicionarItemNaLista() {
        when(letras.get(0)).thenReturn("B");

        assertEquals("B", letras.get(0));
    }

}
