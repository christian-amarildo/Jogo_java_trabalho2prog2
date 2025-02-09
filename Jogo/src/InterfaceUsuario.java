import entities.Log;
import entities.Player;

import java.util.List;
import java.util.Scanner;

// A classe InterfaceUsuario gerencia a interação com o usuário (jogador), exibindo informações e recebendo entradas.
public class InterfaceUsuario {
    
    // Atributo scanner para ler as entradas do usuário.
    private Scanner scanner;

    // Construtor da classe InterfaceUsuario. Inicializa o scanner para ler entradas do usuário.
    public InterfaceUsuario() {
        scanner = new Scanner(System.in);  // Cria uma nova instância de Scanner para capturar a entrada do usuário via teclado.
    }

    // Método que exibe o status atual de todos os jogadores passados na lista
    public void mostrarStatusJogadores(List<Player> jogadores) {
        
        // Laço que percorre a lista de jogadores e exibe o nome e status de cada um.
        for (Player player : jogadores) {
            // Exibe o nome do jogador, HP, Destreza e Velocidade de cada jogador.
            System.out.println(player.getNome() + " - HP: " + player.getHp() + " | Destreza: " + player.getDestreza() + " | Velocidade: " + player.getVelocidade());
        }
    }

    // Método que exibe os logs de eventos registrados. 
    // Recebe um objeto entities.Log, que provavelmente contém uma lista de eventos.
    public void mostrarLogs(Log log) {
        log.exibirEventos();  // Chama o método exibirEventos() da classe entities.Log para exibir os eventos registrados.
    }

    // Método que exibe um resultado na interface do usuário, como uma mensagem de vitória ou derrota.
    public void exibirResultado(String resultado) {
        System.out.println(resultado);  // Imprime o resultado no console (por exemplo, "Vitória!" ou "Derrota!").
    }

    // Método que solicita uma escolha do usuário e retorna essa escolha como um número inteiro.
    public int obterEscolha() {
        System.out.print("Escolha uma ação: ");  // Exibe uma mensagem pedindo para o usuário escolher uma ação.
        return scanner.nextInt();  // Captura e retorna a escolha do usuário, que é um número inteiro.
    }
}
