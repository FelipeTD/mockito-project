package tortora.mockito;

public class Email {

    private String enderecoEmail;
    private String mensagem;
    private Formato formato;

    public Email(String enderecoEmail, String mensagem, Formato formato) {
        this.enderecoEmail = enderecoEmail;
        this.mensagem = mensagem;
        this.formato = formato;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }
}
