import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<String> eventos;

    public Log() {
        eventos = new ArrayList<>();
    }

    public void adicionarEvento(String evento) {
        eventos.add(evento);
    }

    public void exibirEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Sem eventos registrados.");
        } else {
            System.out.println("Eventos do combate:");
            eventos.forEach(System.out::println);
        }
    }
}
