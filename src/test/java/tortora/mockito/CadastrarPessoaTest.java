package tortora.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTest {

    @Mock
    private APIDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro() {
        when(apiDosCorreios.buscaDadosComBaseNoCep("30350140")).thenReturn(new DadosLocalizacao("MG", "Belo Horizonte", "Rua Professor Arduino Bolivar", "AP 10", "Santo Antonio"));

        LocalDate date = LocalDate.of(1994, 2,17);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Filipe", "11457308681", date, "30350140");

        assertEquals("Filipe", pessoa.getNome());
        assertEquals("11457308681", pessoa.getDocumento());
        assertEquals("MG", pessoa.getEndereco().getUf());
    }

    @Test
    void cadastrarPessoa() {
        DadosLocalizacao localizacao = new DadosLocalizacao("MG", "Belo Horizonte", "Rua Professor Arduino Bolivar", "AP 10", "Santo Antonio");
        LocalDate date = LocalDate.of(1994, 2,17);
        when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(localizacao);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Filipe", "11457308681", date, "30350140");

        DadosLocalizacao endereco = pessoa.getEndereco();
        assertEquals(localizacao.getBairro(), endereco.getBairro());
        assertEquals(localizacao.getCidade(), endereco.getCidade());
        assertEquals(localizacao.getUf(), endereco.getUf());
    }

    @Test
    void lancarExceptionCorreios() {
        when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("Filipe", "11457308681", LocalDate.now(), "30350140"));
    }

    @Test
    void validarNull() {
        when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(null);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Filipe", "11457308681", LocalDate.now(), "30350140");

        DadosLocalizacao endereco = pessoa.getEndereco();
        assertNull(endereco);
    }

    @Test
    void lancarExceptionComDoThrowCorreios() {
        doThrow(IllegalArgumentException.class)
                .when(apiDosCorreios)
                    .buscaDadosComBaseNoCep(anyString());

        assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("Filipe", "11457308681", LocalDate.now(), "30350140"));
    }

}
