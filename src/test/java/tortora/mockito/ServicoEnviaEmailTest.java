package tortora.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoEnviaEmailTest {

    @Mock
    private PlataformaDeEnvio plataformaDeEnvio;

    @InjectMocks
    private ServiceEnviaEmail serviceEnviaEmail;

    @Captor
    private ArgumentCaptor<Email> captor;

    @Test
    void validarDadosEnviadosParaPlataforma() {

        String enderecoEmail = "fedispato@gmail.com";
        String mensagem = "Hello World";
        boolean formatoHtml = false;

        serviceEnviaEmail.enviaEmail(enderecoEmail, mensagem, formatoHtml);

        Mockito.verify(plataformaDeEnvio).enviaEmail(captor.capture());

        Email emailCapturado = captor.getValue();

        Assertions.assertEquals(enderecoEmail, emailCapturado.getEnderecoEmail());
        Assertions.assertEquals(mensagem, emailCapturado.getMensagem());
        Assertions.assertEquals(Formato.TEXTO, emailCapturado.getFormato());

    }

}
