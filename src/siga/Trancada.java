package siga;

public class Trancada implements EstadoMatricula {
    @Override public void confirmar(Matricula matricula) { matricula.mudarPara(new Confirmada()); }
    @Override public void trancar(Matricula matricula) { throw new IllegalStateException("Nao e possivel trancar"); }
    @Override public void cancelar(Matricula matricula) { matricula.mudarPara(new Cancelada()); }
    @Override public void concluir(Matricula matricula) { throw new IllegalStateException("Nao e possivel concluir"); }
    @Override public String getNome() { return "TRANCADA"; }
}