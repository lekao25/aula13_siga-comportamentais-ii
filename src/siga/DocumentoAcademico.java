package siga;

import java.time.LocalDate;

public abstract class DocumentoAcademico {
    public final String emitir(Aluno aluno) {
        validar(aluno);
        StringBuilder documento = new StringBuilder();
        documento.append(cabecalho());
        documento.append(corpo(aluno));
        if (exigeAssinatura()) {
            documento.append(rodape());
        }
        registrarEmissao(aluno);
        return documento.toString();
    }
    private void validar(Aluno aluno) {
        if (!aluno.isRegular()) {
            throw new IllegalStateException("Aluno irregular: " + aluno.getMatricula());
        }
    }
    private String cabecalho() {
        return "FATEC DE PORTO FERREIRA\nSistema de Gestao Academica\n---\n";
    }
    protected abstract String corpo(Aluno aluno);
    protected boolean exigeAssinatura() { return true; }
    private String rodape() {
        return "\n---\nEmitido em " + LocalDate.now() + "\nAssinado digitalmente pela secretaria academica.";
    }
    protected abstract void registrarEmissao(Aluno aluno);
}