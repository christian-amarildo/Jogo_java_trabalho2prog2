import java.util.*;
import java.util.List;

public class Game {
    private List<Hero> herois = new ArrayList<>();
    private List<Monster> monstros = new ArrayList<>();
    private Log log;
    private Turno turno;
    private InterfaceUsuario ui;
    private Dificuldade dificuldade;
    private Inventario inventario;

    public Game() {
        log = new Log();
        ui = new InterfaceUsuario();
        inventario = new Inventario();
    }

    public void escolherDificuldade() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a dificuldade: (1) Fácil (2) Médio (3) Difícil");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                dificuldade = new Dificuldade("Fácil", 1.0f, 1.0f, 3);
                break;
            case 2:
                dificuldade = new Dificuldade("Médio", 1.5f, 1.2f, 5);
                break;
            case 3:
                dificuldade = new Dificuldade("Difícil", 2.0f, 1.5f, 7);
                break;
            default:
                dificuldade = new Dificuldade("Fácil", 1.0f, 1.0f, 3);
                break;
        }
    }

        // Criar heróis
    public void iniciarJogo() {
            escolherDificuldade();
    
            // Criar heróis
            Habilidade habilidadeHero1 = new Habilidade("Golpe de Fogo", "Dano", 10, "Causa dano extra com fogo");
            Habilidade habilidadeHero2 = new Habilidade("Cura Rápida", "Cura", 5, "Recupera uma quantidade de HP");
            herois.add(new Hero("Tharos", 120, 15, 10, 8, 7, "Guerreiro", habilidadeHero1));
            herois.add(new Hero("Elaria", 80, 25, 5, 15, 10, "Mago", habilidadeHero2));
    
            // Criar monstros
            monstros.add(new Monster("Goblin", 70, 10, 5, 12, 10, "Goblin", 1));
            monstros.add(new Monster("Ogro", 150, 20, 15, 5, 5, "Ogro", 2));
            monstros.add(new Monster("Dragão Sombrio", 300, 35, 25, 10, 8, "Dragão", 3));
    
            // Ajustar parâmetros da dificuldade
            dificuldade.ajustarParametros(monstros);
    
            // Criar itens
            Item poçãoDeCura = new Item("Poção de Cura", "Consumível", "cura");
            inventario.adicionarItem(poçãoDeCura);
    
            // Iniciar turno de combate
            turno = new Turno(new ArrayList<>(herois));
            turno.iniciarTurno();
        }
    
        public void terminarJogo() {
            System.out.println("Fim do Jogo!");
        }
    
        public static void main(String[] args) {
            Game game = new Game();
            game.iniciarJogo();
        }
    }


