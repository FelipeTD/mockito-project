package tortora.mockito;

public class ServiceEnviaEmail {

    private PlataformaDeEnvio plataformaDeEnvio;

    void enviaEmail(String enderecoEmail, String mensagem, boolean formatoHTML) {

        Email email = null;

        if (formatoHTML) {
            email = new Email(enderecoEmail, mensagem, Formato.HTML);
        } else {
            email = new Email(enderecoEmail, mensagem, Formato.TEXTO);
        }

        plataformaDeEnvio.enviaEmail(email);

    }

}
