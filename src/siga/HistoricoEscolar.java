package siga;

public class HistoricoEscolar extends DocumentoAcademico {
    @Override
    protected String corpo(Aluno aluno) {
        return "HISTORICO ESCOLAR\n" + aluno.getNome() + "\nCarga horaria cumprida: " + aluno.getCargaCumprida() + "h";
    }
    @Override
    protected void registrarEmissao(Aluno aluno) { LogAcademico.registrar("HISTORICO", aluno); }
}