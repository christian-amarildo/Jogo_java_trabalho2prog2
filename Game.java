import java.util.*;
import java.util.List;

// Classe principal do jogo
public class Game {
    // Lista de heróis que participarão do jogo
    private List<Hero> herois = new ArrayList<>();
    // Lista de monstros que os heróis enfrentarão
    private List<Monster> monstros = new ArrayList<>();
    // Objeto para registrar logs do jogo
    private Log log;
    // Objeto que controla os turnos do jogo
    private Turno turno;
    // Interface de usuário para interação com o jogador
    private InterfaceUsuario ui;
    // Dificuldade do jogo, que afeta os parâmetros dos monstros e heróis
    private Dificuldade dificuldade;
    // Inventário que armazena itens coletados durante o jogo
    private Inventario inventario;

    // Construtor da classe Game
    public Game() {
        // Inicializa o objeto de log
        log = new Log();
        // Inicializa a interface de usuário
        ui = new InterfaceUsuario();
        // Inicializa o inventário
        inventario = new Inventario();
    }

    // Método para escolher a dificuldade do jogo
    public void escolherDificuldade() {
        // Scanner para capturar a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        // Exibe as opções de dificuldade
        System.out.println("Escolha a dificuldade: (1) Fácil (2) Médio (3) Difícil");
        // Captura a escolha do usuário
        int escolha = scanner.nextInt();
        // Switch case para definir a dificuldade com base na escolha do usuário
        switch (escolha) {
            case 1:
                // Define a dificuldade como Fácil com parâmetros específicos
                dificuldade = new Dificuldade("Fácil", 1.0f, 1.0f, 3);
                break;
            case 2:
                // Define a dificuldade como Médio com parâmetros específicos
                dificuldade = new Dificuldade("Médio", 1.5f, 1.2f, 5);
                break;
            case 3:
                // Define a dificuldade como Difícil com parâmetros específicos
                dificuldade = new Dificuldade("Difícil", 2.0f, 1.5f, 7);
                break;
            default:
                // Caso o usuário insira uma opção inválida, define a dificuldade como Fácil por padrão
                dificuldade = new Dificuldade("Fácil", 1.0f, 1.0f, 3);
                break;
        }
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        // Chama o método para escolher a dificuldade
        escolherDificuldade();

        // Cria habilidades para os heróis
        Habilidade habilidadeHero1 = new Habilidade("Golpe de Fogo", "Dano", 10, "Causa dano extra com fogo");
        Habilidade habilidadeHero2 = new Habilidade("Cura Rápida", "Cura", 5, "Recupera uma quantidade de HP");

        // Adiciona heróis à lista de heróis
        herois.add(new Hero("Tharos", 120, 15, 10, 8, 7, "Guerreiro", habilidadeHero1));
        herois.add(new Hero("Elaria", 80, 25, 5, 15, 10, "Mago", habilidadeHero2));

        // Adiciona monstros à lista de monstros
        monstros.add(new Monster("Goblin", 70, 10, 5, 12, 10, "Goblin", 1));
        monstros.add(new Monster("Ogro", 150, 20, 15, 5, 5, "Ogro", 2));
        monstros.add(new Monster("Dragão Sombrio", 300, 35, 25, 10, 8, "Dragão", 3));

        // Cria itens e adiciona ao inventário
        Item poçãoDeCura = new Item("Poção de Cura", "Consumível", "cura");
        inventario.adicionarItem(poçãoDeCura);

        // Inicia o turno de combate com a lista de heróis
        turno = new Turno(new ArrayList<>(herois));
        turno.iniciarTurno();
    }

    // Método para terminar o jogo
    public void terminarJogo() {
        // Exibe uma mensagem de fim de jogo
        System.out.println("Fim do Jogo!");
    }

    // Método principal que inicia o jogo
    public static void main(String[] args) {
        // Cria uma instância do jogo
        Game game = new Game();
        // Inicia o jogo
        game.iniciarJogo();
    }
}
