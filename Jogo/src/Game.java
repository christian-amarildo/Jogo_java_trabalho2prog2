import Enums.ResultadoAtaque;
import entities.Habilidade;
import entities.Log;
import entities.heros.*;
import entities.monsters.*;
import java.util.*;

public class Game {
    private List<Hero> herois = new ArrayList<>();
    private List<Monster> monstros = new ArrayList<>();
    private Log log;
    private Turno turno;
    private InterfaceUsuario ui;
    private Dificuldade dificuldade;
    private Inventario inventario;
    private Hero jogador;

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

    public void escolherHeroi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o tipo de herói: (1) Guerreiro (2) Mago (3) Arqueiro (4) Furtivo");
        int escolha = scanner.nextInt();
        Habilidade habilidadeHeroi = new Habilidade("Golpe Especial", "Dano", 15, "Causa dano especial");

        switch (escolha) {
            case 1:
                jogador = new Guerreiro("Tharos", 110, 20, 3, 10, 8, "Guerreiro", habilidadeHeroi, 100);
                break;
            case 2:
                jogador = new Mago("Elaria", 80, 25, 2, 2, 10, "Mago", habilidadeHeroi, 150);
                break;
            case 3:
                jogador = new Arqueiro("Lian", 90, 18, 2, 2, 12, "Arqueiro", habilidadeHeroi, 110);
                break;
            case 4:
                jogador = new Furtivo("Silas", 100, 22, 1, 8, 14, "Furtivo", habilidadeHeroi, 120);
                break;
            default:
                jogador = new Guerreiro("Tharos", 110, 20, 1, 10, 8, "Guerreiro", habilidadeHeroi, 100);
                break;
        }

        // Passa o Log para o herói
        jogador.setLog(log);

        System.out.println("Você escolheu o herói: " + jogador.getNome() + " com " + jogador.getDinheiro() + " moedas.");
    }

    public void inicializarMonstros() {
        monstros.add(new Slime("Slime", "Comum", 1));
        monstros.add(new Esqueleto("Esqueleto", "Normal", 2));
        monstros.add(new Leviatan("Leviatan", "Chefe", 3));
    }

    public void interagir() {
        Scanner scanner = new Scanner(System.in);
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            jogador.mostrarDinheiro(); // Mostra o dinheiro do jogador
            System.out.println("\nO que você deseja fazer?");
            System.out.println("1. Ir para a Dungeon");
            System.out.println("2. Ir para a Loja");
            System.out.println("3. Lutar contra o Leviatan");
            System.out.println("4. Sair");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    irDungeon();
                    break;
                case 2:
                    irLoja();
                    break;
                case 3:
                    lutarContraLeviatan();
                    break;
                case 4:
                    jogoAtivo = false;
                    System.out.println("Você saiu do jogo.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void irDungeon() {
        System.out.println("\nVocê entrou na Dungeon!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        System.out.println("Monstros disponíveis: Slime, Esqueleto.");

        Monster monstroEscolhido = monstros.get(new Random().nextInt(2)); // Slime ou Esqueleto
        lutarContraMonstro(monstroEscolhido);

        // Regenerar a vida dos monstros ao sair da Dungeon
        regenerarVidaMonstros();
    }

    public void regenerarVidaMonstros() {
        // Itera sobre a lista de monstros e regenera a vida
        for (Monster monstro : monstros) {
            if (monstro.getNome() == "Slime"){
                monstro.setHp(15);
            } else {
                monstro.setHp(20);
            }
            // Regenera a vida do monstro
            log.adicionarLog(monstro.getNome() + " teve sua vida regenerada!");
        }
    }


    public void irLoja() {
        System.out.println("\nBem-vindo à loja!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        System.out.println("Você pode comprar itens aqui.");
        System.out.println("1. Poção de Cura - 30 moedas (Recupera 30 de vida)");
        System.out.println("2. Espada - 200 moedas (Aumenta o ataque em 20)");
        System.out.println("3. Espada Melhorada - 300 moedas (Aumenta o ataque em 30)");
        System.out.println("4. Armadura - 250 moedas (Aumenta a defesa em 15)");
        System.out.println("Digite o número do item que deseja comprar:");

        Scanner scanner = new Scanner(System.in);
        int escolhaLoja = scanner.nextInt();

        switch (escolhaLoja) {
            case 1:
                if (jogador.getDinheiro() >= 30) {
                    jogador.gastarDinheiro(30);
                    jogador.curar(50);
                    System.out.println("Você comprou uma Poção de Cura!");
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Poção de Cura.");
                }
                break;
            case 2:
                if (jogador.getDinheiro() >= 200) {
                    jogador.gastarDinheiro(200);
                    Item espada = new Item("Espada", "Arma", "dano", 10, 1);
                    inventario.adicionarItem(espada);
                    jogador.equiparArma(espada); // Equipa a espada
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Espada.");
                }
                break;
            case 3:
                if (jogador.getDinheiro() >= 300) {
                    jogador.gastarDinheiro(300);
                    Item espadaMelhorada = new Item("Espada Melhorada", "Arma", "dano", 15, 2);
                    inventario.adicionarItem(espadaMelhorada);
                    jogador.equiparArma(espadaMelhorada); // Equipa a espada melhorada
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Espada Melhorada.");
                }
                break;
            case 4:
                if (jogador.getDinheiro() >= 250) {
                    jogador.gastarDinheiro(250);
                    Item armadura = new Item("Armadura", "Defesa", "proteção", 15, 3);
                    inventario.adicionarItem(armadura);
                    jogador.equiparArmadura(armadura); // Equipa a armadura
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Armadura.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void lutarContraLeviatan() {
        System.out.println("\nVocê está enfrentando o Leviatan!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        Monster leviatan = monstros.get(2); // Leviatan
        lutarContraMonstro(leviatan);
    }

    public void lutarContraMonstro(Monster monstro) {
        log.adicionarLog("Você está lutando contra " + monstro.getNome() + "!");

        // Exibir os atributos do herói antes da batalha
        log.adicionarLog("Atributos do Herói:");
        log.adicionarLog("Ataque: " + jogador.getForcaAtaque() + ", Defesa: " + jogador.getDefesa());
        log.adicionarLog("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        log.adicionarLog("Vida do Monstro (" + monstro.getNome() + "): " + monstro.getHp());

        while (jogador.getHp() > 0 && monstro.getHp() > 0) {
            // Exibir os status de vida antes de cada rodada de ataques
            log.adicionarLog("\n--- Início da rodada ---");
            log.adicionarLog("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
            log.adicionarLog("Vida do Monstro (" + monstro.getNome() + "): " + monstro.getHp());

            // Atributos do herói
            log.adicionarLog("Atributos atuais do Herói:");
            log.adicionarLog("Ataque: " + jogador.getForcaAtaque() + ", Defesa: " + jogador.getDefesa());

            // Herói ataca
            log.adicionarLog(jogador.getNome() + " ataca " + monstro.getNome() + "!");
            ResultadoAtaque resultado = jogador.realizarAtaque(monstro);
            switch (resultado) {
                case ACERTOU:
                    log.adicionarLog("O ataque acertou!");
                    break;
                case CRITICAL_HIT:
                    log.adicionarLog("Golpe crítico!");
                    break;
                case ERROU:
                    log.adicionarLog("O ataque errou!");
                    break;
            }

            // Verifica se o monstro ainda está vivo
            if (monstro.getHp() > 0) {
                // Monstro ataca
                log.adicionarLog(monstro.getNome() + " ataca " + jogador.getNome() + "!");
                ResultadoAtaque resultadoMonstro = monstro.realizarAtaque(jogador);
                switch (resultadoMonstro) {
                    case ACERTOU:
                        log.adicionarLog("O ataque do monstro acertou!");
                        break;
                    case CRITICAL_HIT:
                        log.adicionarLog("Golpe crítico do monstro!");
                        break;
                    case ERROU:
                        log.adicionarLog("O ataque do monstro errou!");
                        break;
                }
            }
        }

        // Resultado da batalha
        if (jogador.getHp() > 0) {
            log.adicionarLog("Você venceu " + monstro.getNome() + "!");
            // Adiciona o dinheiro dropado pelo monstro ao jogador
            jogador.gastarDinheiro(-monstro.getDinheiroDropado()); // Usa negativo para adicionar dinheiro
            log.adicionarLog("Você ganhou " + monstro.getDinheiroDropado() + " moedas!");
        } else {
            log.adicionarLog("Você foi derrotado por " + monstro.getNome() + "!");
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.escolherDificuldade();
        game.escolherHeroi();
        game.inicializarMonstros();
        game.interagir();
    }
}