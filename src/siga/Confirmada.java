package siga;

public class Confirmada implements EstadoMatricula {
    @Override public void confirmar(Matricula matricula) { throw new IllegalStateException("Nao e possivel confirmar"); }
    @Override public void trancar(Matricula matricula) { matricula.mudarPara(new Trancada()); }
    @Override public void cancelar(Matricula matricula) { matricula.mudarPara(new Cancelada()); }
    @Override public void concluir(Matricula matricula) { matricula.mudarPara(new Concluida()); }
    @Override public String getNome() { return "CONFIRMADA"; }
}