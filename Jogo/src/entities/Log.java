package entities;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<String> logs;

    public Log() {
        logs = new ArrayList<>();
    }

    // Adiciona um log à lista
    public void adicionarLog(String log) {
        logs.add(log);
    }

    // Exibe todos os logs armazenados
    public void exibirEventos() {
        if (logs.isEmpty()) {
            System.out.println("Nenhum evento registrado.");
        } else {
            // Exibe todos os eventos registrados na lista de logs
            for (String log : logs) {
                System.out.println(log);
            }
        }
    }
}
