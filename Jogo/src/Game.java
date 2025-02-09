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
    private Scanner scanner;

    public Game() {
        log = new Log();
        ui = new InterfaceUsuario();
        inventario = new Inventario();
        scanner = new Scanner(System.in);  // Inicializado uma vez aqui
    }

    public void escolherDificuldade() {
        System.out.println("Escolha a dificuldade: (1) Fácil (2) Médio (3) Difícil");
        int escolha = scanner.nextInt();
        while (escolha < 1 || escolha > 3) {  // Verificação de entrada
            System.out.println("Escolha inválida! Digite um número entre 1 e 3.");
            escolha = scanner.nextInt();
        }

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
        }
    }

    public void escolherHeroi() {
        // Apresentando informações detalhadas sobre cada herói
        System.out.println("Escolha o tipo de herói:");

        // Exibindo informações sobre cada classe de herói
        System.out.println("(1) Guerreiro - Alta vida e boa defesa, forte no combate corpo a corpo.");
        System.out.println("   Características:");
        System.out.println("   - Vida: 110, Força de Ataque: 20, Defesa: 10, Destreza: 10, Velocidade: 8");
        System.out.println("   - Habilidade Especial: Golpe Especial (Causa 15 de dano adicional)");
        System.out.println("   - Estilo: Ideal para quem prefere combate direto e resistência.");

        System.out.println("(2) Mago - Alta destreza e forte no ataque à distância, mas com menos vida e defesa.");
        System.out.println("   Características:");
        System.out.println("   - Vida: 80, Força de Ataque: 25, Defesa: 5, Destreza: 15, Velocidade: 10");
        System.out.println("   - Habilidade Especial: Golpe Especial (Causa 15 de dano adicional)");
        System.out.println("   - Estilo: Ideal para quem prefere atacar de longe com alta potência.");

        System.out.println("(3) Arqueiro - Boa destreza e alta velocidade, pode atacar à distância com precisão.");
        System.out.println("   Características:");
        System.out.println("   - Vida: 90, Força de Ataque: 18, Defesa: 10, Destreza: 20, Velocidade: 12");
        System.out.println("   - Habilidade Especial: Golpe Especial (Causa 15 de dano adicional)");
        System.out.println("   - Estilo: Ideal para quem prefere agilidade e ataques de precisão à distância.");

        System.out.println("(4) Furtivo - Alta velocidade e destreza, ideal para ataques rápidos e furtivos.");
        System.out.println("   Características:");
        System.out.println("   - Vida: 100, Força de Ataque: 22, Defesa: 8, Destreza: 18, Velocidade: 14");
        System.out.println("   - Habilidade Especial: Golpe Especial (Causa 15 de dano adicional)");
        System.out.println("   - Estilo: Ideal para quem prefere furtividade e ataques rápidos com alta agilidade.");
        System.out.println(".");
        System.out.println(" escolha o heroi digitando um número de 1 a 4:");

        // Solicitando a escolha do herói
        int escolha = scanner.nextInt();
        while (escolha < 1 || escolha > 4) {  // Verificação de entrada
            System.out.println("Escolha inválida! Digite um número entre 1 e 4.");
            escolha = scanner.nextInt();
        }

        // Definindo a habilidade do herói
        Habilidade habilidadeHeroi = new Habilidade("Golpe Especial", "Dano", 15, "Causa dano especial");

        // Criando o herói baseado na escolha
        switch (escolha) {
            case 1:
                jogador = new Guerreiro("Tharos", 110, 20, 10, 10, 8, "Guerreiro", habilidadeHeroi, 100);
                break;
            case 2:
                jogador = new Mago("Elaria", 80, 25, 5, 15, 10, "Mago", habilidadeHeroi, 150);
                break;
            case 3:
                jogador = new Arqueiro("Lian", 90, 18, 10, 20, 12, "Arqueiro", habilidadeHeroi, 110);
                break;
            case 4:
                jogador = new Furtivo("Silas", 100, 22, 8, 18, 14, "Furtivo", habilidadeHeroi, 120);
                break;
        }

        // Passa o Log para o herói
        jogador.setLog(log);

        // Exibe as informações do herói escolhido
        System.out.println("Você escolheu o herói: " + jogador.getNome());
        System.out.println("Características do " + jogador.getNome() + ":");
        System.out.println("   - Vida: " + jogador.getHp());
        System.out.println("   - Força de Ataque: " + jogador.getForcaAtaque());
        System.out.println("   - Defesa: " + jogador.getDefesa());
        System.out.println("   - Destreza: " + jogador.getDestreza());
        System.out.println("   - Velocidade: " + jogador.getVelocidade());
        System.out.println("Com " + jogador.getDinheiro() + " moedas.");
    }

    public void inicializarMonstros() {
        monstros.add(new Slime("Slime", "Comum", 1));
        monstros.add(new Esqueleto("Esqueleto", "Normal", 2));
        monstros.add(new Leviatan("Leviatan", "Chefe", 3));
    }

    public void interagir() {
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            jogador.mostrarDinheiro(); // Mostra o dinheiro do jogador
            System.out.println("\nO que você deseja fazer?");
            System.out.println("1. Ir para a Dungeon");
            System.out.println("2. Ir para a Loja");
            System.out.println("3. Lutar contra o Leviatan");
            System.out.println("4. Sair");
            int escolha = scanner.nextInt();

            while (escolha < 1 || escolha > 4) {  // Verificação de entrada
                System.out.println("Opção inválida! Digite um número entre 1 e 4.");
                escolha = scanner.nextInt();
            }

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
            }
        }
    }

    public void irDungeon() {
        System.out.println("\nVocê entrou na Dungeon!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        System.out.println("Monstros disponíveis: Slime, Esqueleto.");

        Random random = new Random();
        Monster monstroEscolhido = monstros.get(random.nextInt(2)); // Slime ou Esqueleto
        System.out.println("Você encontrou um " + monstroEscolhido.getNome() + "!");
        System.out.println("Vida do " + monstroEscolhido.getNome() + ": " + monstroEscolhido.getHp());

        mostrarLogs();  // Exibe os logs antes da batalha iniciar
        lutarContraMonstro(monstroEscolhido);
    }


    public void irLoja() {
        System.out.println("\nBem-vindo à loja!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        System.out.println("Você pode comprar itens aqui.");
        System.out.println("1. Poção de Cura - 50 moedas (Recupera 20 de vida)");
        System.out.println("2. Espada - 200 moedas (Aumenta o ataque em 20)");
        System.out.println("3. Espada Melhorada - 300 moedas (Aumenta o ataque em 30)");
        System.out.println("4. Armadura - 250 moedas (Aumenta a defesa em 15)");
        System.out.println("Digite o número do item que deseja comprar:");

        int escolhaLoja = scanner.nextInt();

        while (escolhaLoja < 1 || escolhaLoja > 4) {  // Verificação de entrada para loja
            System.out.println("Opção inválida! Digite um número entre 1 e 4.");
            escolhaLoja = scanner.nextInt();
        }

        switch (escolhaLoja) {
            case 1:
                if (jogador.getDinheiro() >= 30) {
                    jogador.gastarDinheiro(30);
                    jogador.curar(20); // Recupera 20 de vida
                    System.out.println("Você comprou uma Poção de Cura!");
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Poção de Cura.");
                }
                break;
            case 2:
                if (jogador.getDinheiro() >= 200) {
                    jogador.gastarDinheiro(200);
                    Item espada = new Item("Espada", "Arma", "dano", 20, 1);
                    inventario.adicionarItem(espada);
                    jogador.equiparArma(espada); // Equipa a espada
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Espada.");
                }
                break;
            case 3:
                if (jogador.getDinheiro() >= 300) {
                    jogador.gastarDinheiro(300);
                    Item espadaMelhorada = new Item("Espada Melhorada", "Arma", "dano", 30, 2);
                    inventario.adicionarItem(espadaMelhorada);
                    jogador.equiparArma(espadaMelhorada); // Equipa a espada melhorada
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Espada Melhorada.");
                }
                break;
            case 4:
                if (jogador.getDinheiro() >= 250) {
                    jogador.gastarDinheiro(250);
                    Item armadura = new Item("Armadura", "Defesa", "proteção", 15, 20);
                    inventario.adicionarItem(armadura);
                    jogador.equiparArmadura(armadura); // Equipa a armadura
                } else {
                    System.out.println("Você não tem dinheiro suficiente para comprar a Armadura.");
                }
                break;
        }
    }

    public void lutarContraLeviatan() {
        System.out.println("\nVocê está enfrentando o Leviatan!");
        System.out.println("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
        Monster leviatan = monstros.get(2); // Leviatan
        lutarContraMonstro(leviatan);
    }

    public void lutarContraMonstro(Monster monstro) {
        log.adicionarLog("A batalha contra " + monstro.getNome() + " começou!");
        mostrarLogs();  // Exibe logs no início da batalha

        while (jogador.getHp() > 0 && monstro.getHp() > 0) {
            // Exibindo status de vida antes de cada rodada de combate
            log.adicionarLog("\n** Status Atual **");
            log.adicionarLog("Vida do Herói (" + jogador.getNome() + "): " + jogador.getHp());
            log.adicionarLog("Vida do " + monstro.getNome() + ": " + monstro.getHp());
            mostrarLogs();  // Exibe o status antes de cada rodada

            // Ação do herói
            log.adicionarLog(jogador.getNome() + " ataca " + monstro.getNome() + "!");
            ResultadoAtaque resultado = jogador.realizarAtaque(monstro);
            switch (resultado) {
                case ACERTOU:
                    log.adicionarLog("O ataque acertou!");
                    break;
                case CRITICAL_HIT:
                    log.adicionarLog("Golpe crítico! Causou dano massivo!");
                    break;
                case ERROU:
                    log.adicionarLog("O ataque errou! O " + monstro.getNome() + " se esquivou!");
                    break;
            }
            mostrarLogs();  // Exibe os resultados do ataque do herói

            // Verifica se o monstro ainda está vivo
            if (monstro.getHp() > 0) {
                // Ação do monstro
                log.adicionarLog(monstro.getNome() + " ataca " + jogador.getNome() + "!");
                ResultadoAtaque resultadoMonstro = monstro.realizarAtaque(jogador);
                switch (resultadoMonstro) {
                    case ACERTOU:
                        log.adicionarLog("O ataque do monstro acertou!");
                        break;
                    case CRITICAL_HIT:
                        log.adicionarLog("Golpe crítico do monstro! Causou dano severo!");
                        break;
                    case ERROU:
                        log.adicionarLog("O ataque do monstro errou! Você se esquivou!");
                        break;
                }
                mostrarLogs();  // Exibe os resultados do ataque do monstro
            }
        }

        // Resultado final da batalha
        if (jogador.getHp() > 0) {
            log.adicionarLog("Você venceu " + monstro.getNome() + "!");
            // Adiciona o dinheiro dropado pelo monstro ao jogador
            jogador.adicionarDinheiro(monstro.getDinheiroDropado());
            log.adicionarLog("Você ganhou " + monstro.getDinheiroDropado() + " moedas!");
        } else {
            log.adicionarLog("Você foi derrotado por " + monstro.getNome() + "!");
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
    }

    private void mostrarLogs() {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();  // Aguarda a entrada do jogador antes de continuar
        log.exibirEventos();  // Exibe os logs registrados até o momento
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.escolherDificuldade();
        game.escolherHeroi();
        game.inicializarMonstros();
        game.interagir();
    }
}
