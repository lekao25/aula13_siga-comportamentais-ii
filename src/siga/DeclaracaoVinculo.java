package siga;

public class DeclaracaoVinculo extends DocumentoAcademico {
    @Override
    protected String corpo(Aluno aluno) {
        return "DECLARACAO DE VINCULO\nDeclaramos que " + aluno.getNome() + " possui vinculo ativo com esta instituicao.";
    }
    @Override
    protected void registrarEmissao(Aluno aluno) { LogAcademico.registrar("DECLARACAO", aluno); }
}