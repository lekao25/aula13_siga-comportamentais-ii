package siga;

public class Concluida implements EstadoMatricula {
    @Override public void confirmar(Matricula matricula) { throw new IllegalStateException("Nao e possivel confirmar"); }
    @Override public void trancar(Matricula matricula) { throw new IllegalStateException("Nao e possivel trancar"); }
    @Override public void cancelar(Matricula matricula) { throw new IllegalStateException("Nao e possivel cancelar"); }
    @Override public void concluir(Matricula matricula) { throw new IllegalStateException("Ja esta concluida"); }
    @Override public String getNome() { return "CONCLUIDA"; }
}