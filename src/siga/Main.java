package siga;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 13: Template Method e State ===");

        Aluno regular = new Aluno("2024001", "Maria Silva");
        regular.setCargaCumprida(560);

        Aluno irregular = new Aluno("2024002", "Joao Souza");
        irregular.setRegular(false);

        System.out.println("\n1) Tres documentos, com Template Method");
        System.out.println(recuar(new HistoricoEscolar().emitir(regular)));
        System.out.println(recuar(new AtestadoFrequencia().emitir(regular)));

        System.out.println("\n2) A declaracao de vinculo (Erro corrigido)");
        try {
            new DeclaracaoVinculo().emitir(irregular);
        } catch (IllegalStateException e) {
            System.out.println("   [erro] " + e.getMessage());
        }
        System.out.println("   Registros no log: " + LogAcademico.getRegistros());

        System.out.println("\n3) O ciclo de vida da matricula com State");
        Matricula m = new Matricula(regular, "DSM-2A");
        System.out.println("   situacao inicial: " + m.getSituacao());
        m.confirmar();
        System.out.println("   apos confirmar:   " + m.getSituacao());
        m.concluir();
        System.out.println("   apos concluir:    " + m.getSituacao());
    }

    private static String recuar(String texto) {
        StringBuilder saida = new StringBuilder();
        for (String linha : texto.split("\n")) {
            saida.append("     ").append(linha).append("\n");
        }
        return saida.toString().stripTrailing();
    }
}