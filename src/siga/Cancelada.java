package siga;

public class Cancelada implements EstadoMatricula {
    @Override public void confirmar(Matricula matricula) { throw new IllegalStateException("Nao e possivel confirmar"); }
    @Override public void trancar(Matricula matricula) { throw new IllegalStateException("Nao e possivel trancar"); }
    @Override public void cancelar(Matricula matricula) { throw new IllegalStateException("Nao e possivel cancelar"); }
    @Override public void concluir(Matricula matricula) { throw new IllegalStateException("Nao e possivel concluir"); }
    @Override public String getNome() { return "CANCELADA"; }
}