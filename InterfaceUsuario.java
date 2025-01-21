import java.util.List;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        scanner = new Scanner(System.in);
    }

    public void mostrarStatusJogadores(List<Player> jogadores) {
        for (Player player : jogadores) {
            System.out.println(player.getNome() + " - HP: " + player.getHp() + " | Destreza: " + player.getDestreza() + " | Velocidade: " + player.getVelocidade());
        }
    }

    public void mostrarLogs(Log log) {
        log.exibirEventos();
    }

    public void exibirResultado(String resultado) {
        System.out.println(resultado);
    }

    public int obterEscolha() {
        System.out.print("Escolha uma ação: ");
        return scanner.nextInt();
    }
}
