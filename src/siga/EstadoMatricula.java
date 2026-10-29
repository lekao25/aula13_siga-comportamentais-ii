package siga;

public interface EstadoMatricula {
    void confirmar(Matricula matricula);
    void trancar(Matricula matricula);
    void cancelar(Matricula matricula);
    void concluir(Matricula matricula);
    String getNome();
}