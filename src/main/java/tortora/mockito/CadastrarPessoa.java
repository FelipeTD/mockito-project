package tortora.mockito;

import java.time.LocalDate;

public class CadastrarPessoa {

    private APIDosCorreios apiDosCorreios;

    public CadastrarPessoa(final APIDosCorreios apiDosCorreios) {
        this.apiDosCorreios = apiDosCorreios;
    }

    public Pessoa cadastrarPessoa(String nome, String documento, LocalDate nascimento, String cep) {
        Pessoa pessoa = new Pessoa(nome, documento, nascimento);
        DadosLocalizacao dadosLocalizacao = apiDosCorreios.buscaDadosComBaseNoCep(cep);
        pessoa.adicionaDadosDoEndereco(dadosLocalizacao);
        return pessoa;
    }

}
