import Enums.ResultadoAtaque;
import entities.Habilidade;
import entities.Log;
import entities.Player;
import entities.heros.*;
import entities.monsters.*;
import exceptions.DificuldadeRangeException;
import utils.Cores;

import java.util.*;

import static entities.heros.Hero.*;

public class Game {
    private List<Hero> herois = new ArrayList<>();
    private List<Monster> monstros = new ArrayList<>();
    private Log log;
    private Turno turno = new Turno();
    private InterfaceUsuario ui;
    private Dificuldade dificuldade;
    private Inventario inventario;

    public Game() {
        log = new Log();
        ui = new InterfaceUsuario();
        inventario = new Inventario();
    }

    public void escolherDificuldade() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha a dificuldade: (1) Fácil (2) Médio (3) Difícil");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                dificuldade = new Dificuldade("Fácil", 0.5f, 1.0f, 3, 1);
                break;
            case 2:
                dificuldade = new Dificuldade("Médio", 1.0f, 1.2f, 5, 2);
                break;
            case 3:
                dificuldade = new Dificuldade("Difícil", 1.5f, 1.5f, 7, 3);
                break;
            default:
                throw new DificuldadeRangeException("Erro ao escolher a dificuldade. Escolha um número de (1) a (3).");
        }

    }

    public void gerarHerois() {
        // Gerar heróis automaticamente
        Habilidade habilidadeHeroi = new Habilidade("Golpe Especial", "Dano", 15, "Causa dano especial");
        herois.add(new Guerreiro("Tharos", 150 + dificuldade.getNivelDificuldade() * 30, 5 + dificuldade.getNivelDificuldade() * 2, 10, 10, 8, "Guerreiro", habilidadeHeroi));
        herois.add(new Mago("Elaria", 110 + dificuldade.getNivelDificuldade() * 20, 25, 5, 15, 10, "Mago", habilidadeHeroi));
        herois.add(new Arqueiro("Lian", 120 + dificuldade.getNivelDificuldade() * 20, 18, 5, 20, 12, "Arqueiro", habilidadeHeroi));
        herois.add(new Furtivo("Silas", 130 + dificuldade.getNivelDificuldade() * 25, 22, 8, 18, 14, "Furtivo", habilidadeHeroi));

        // Passar o log para cada herói
        for (Hero heroi : herois) {
            heroi.setLog(log);
        }

        // Exibir informações sobre os heróis
        for (Hero heroi : herois) {
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getHp() + ", Força de Ataque: " + heroi.getForcaAtaque());
        }
    }

    public void inicializarMonstros() {
        // Gerar uma quantidade aleatória de monstros (Slimes e Esqueletos)
        Random rand = new Random();
        int quantidadeMonstros = rand.nextInt(4) + 1; // Até 6 monstros

        for (int i = 0; i < quantidadeMonstros; i++) {
            // Alteração aqui para garantir que o código gere tanto Slimes quanto Esqueletos corretamente
            if (rand.nextInt(2) == 0) {  // 50% de chance para Slime ou Esqueleto
                monstros.add(new Slime("Slime", "Comum", dificuldade.getNivelDificuldade()));
            } else {
                monstros.add(new Esqueleto("Esqueleto", "Normal", dificuldade.getNivelDificuldade()));
            }
        }
    }


    public void iniciarCombate() {
        boolean batalhaAtiva = true;
        while (batalhaAtiva) {
            // Remover heróis mortos da lista
            removerHeroisMortos();
            removerMonstrosMortos();

            // Decidindo os turnos:
            turno.decidirTurnoBatalhaDungeon(herois, monstros);

            batalhaAtiva = turno.heroisVivos(herois, false) && turno.monstrosVivos(monstros);

            // Exibir o status dos participantes após cada rodada
            exibirStatus();
        }
    }

    private void removerHeroisMortos() {
        // Remover heróis mortos da lista
        Iterator<Hero> iterator = herois.iterator();
        while (iterator.hasNext()) {
            Player heroi = iterator.next();
            if (heroi.getHp() <= 0) {
                System.out.println(heroi.getNome() + " foi derrotado!");
                iterator.remove(); // Remove o herói da lista
            }
        }
    }

    private void removerMonstrosMortos() {
        // Remover monstros mortos da lista
        Iterator<Monster> iterator = monstros.iterator();
        while (iterator.hasNext()) {
            Monster monstro = iterator.next();
            if (monstro.getHp() <= 0) {
                // Quando o monstro for derrotado, realiza o drop de dinheiro
                monstro.dropDinheiro();
                System.out.println(monstro.getNome() + " foi derrotado!");
                iterator.remove(); // Remove o monstro da lista
            }
        }
    }    

    private void exibirStatus() {
        System.out.println("\n" + Cores.AMARELO + "Status Atual:" + Cores.RESET);

        // Exibir heróis restantes
        System.out.println(Cores.AZUL + "Heróis:" + Cores.RESET);
        for (Player heroi : herois) {
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getHp());
        }

        // Exibir monstros restantes
        System.out.println(Cores.VERMELHO + "Monstros:" + Cores.RESET);
        for (Player monstro : monstros) {
            System.out.println(monstro.getNome() + " - Vida: " + monstro.getHp());
        }

        // Mostrar quantidade de monstros em batalha
        System.out.println("\n" + Cores.MAGENTA + "Monstros restantes em batalha: " + monstros.size() + Cores.RESET);

        // Registrar no log o status atual da batalha
        log.adicionarLog("Status atual - Heróis restantes: " + herois.size() + ", Monstros restantes: " + monstros.size());
    }

    public void interagir() throws InputMismatchException{
        Scanner scanner = new Scanner(System.in);
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            // Verificar se todos os heróis estão mortos
            if (!turno.heroisVivos(herois, false)) {
                System.out.println(Cores.VERMELHO + "Todos os heróis foram derrotados. Fim do jogo!" + Cores.RESET);
                break; // Se todos os heróis foram derrotados, termina o jogo
            }

            // Mostra o dinheiro compartilhado entre os heróis
            System.out.println("\n" + Cores.AMARELO + "Dinheiro compartilhado: " + Item.getDinheiro() + Cores.RESET);
            System.out.println("\n" + Cores.CYAN + "O que você deseja fazer?" + Cores.RESET);
            System.out.println("1. Ir para a Dungeon");
            System.out.println("2. Ir para a Loja");
            System.out.println("3. Enfrentar o Leviatã");
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
                    enfrentarLeviata();
                    break;
                case 4:
                    jogoAtivo = false;
                    System.out.println(Cores.VERMELHO + "Você saiu do jogo." + Cores.RESET);
                    break;
                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida. Tente novamente." + Cores.RESET);
            }
        }
    }

    // LEVIATÃ
    public void enfrentarLeviata() {
        System.out.println("\nVocê escolheu enfrentar o Leviatã!");

        // Adicionando o Leviatã à batalha
        Monster leviatan = new Leviatan("Leviatã", "Chefe", dificuldade.getNivelDificuldade());
        monstros.clear(); // Limpa os monstros existentes
        monstros.add(leviatan); // Adiciona o Leviatã à batalha

        // Exibe o status do Leviatã
        System.out.println("Você está enfrentando o Leviatã!");
        System.out.println("Leviatã - Vida: " + leviatan.getHp() + ", Força de Ataque: " + leviatan.getForcaAtaque());

        // Iniciar a batalha contra o Leviatã
        boolean batalhaAtiva = true;
        while (batalhaAtiva) {
            // Decidindo os turnos:
            turno.decidirturnoBatalhaBoss(herois, leviatan);

            // Exibe o status da batalha
            exibirStatus();

            // Verificações finais do turno
            batalhaAtiva = turno.heroisVivos(herois, true) && turno.bossVivo(leviatan);

        }
    }
    // DUNGEON
    public void irDungeon() {
        // Exibe quais heróis estão vivos e seus atributos
        System.out.println("\nHeróis vivos:");
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                System.out.println("Nome: " + heroi.getNome());
                System.out.println("  - Vida: " + heroi.getHp());
                System.out.println("  - Força de Ataque: " + heroi.getForcaAtaque());
                System.out.println("  - Defesa: " + heroi.getDefesa());
                System.out.println("  - Destreza: " + heroi.getDestreza());
                System.out.println("  - Velocidade: " + heroi.getVelocidade());
                System.out.println("----------------------");
            }
        }

        // Inicializar monstros novamente antes de iniciar a batalha
        inicializarMonstros();

        // Iniciar o combate
        iniciarCombate();

        // Regenerar a vida dos monstros ao sair da Dungeon
        regenerarVidaMonstros();
    }


    public void regenerarVidaMonstros() {
        // Itera sobre a lista de monstros e regenera a vida
        for (Monster monstro : monstros) {
            if (monstro.getNome() == "Slime") {
                monstro.setHp(15);
            } else {
                monstro.setHp(20);
            }
            // Regenera a vida do monstro
            log.adicionarLog(monstro.getNome() + " teve sua vida regenerada!");
        }
    }

    // LOJA
    public void irLoja() {
        System.out.println("\nBem-vindo à loja!");
        System.out.println(Cores.AMARELO + "Dinheiro compartilhado: " + Item.getDinheiro() + Cores.RESET);



        // Registrar o acesso à loja e o dinheiro atual
        log.adicionarLog("Acessou a loja. Dinheiro atual: " + Item.getDinheiro());

        System.out.println("Você pode comprar itens aqui.");
        System.out.println("1. Poção de Cura - 30 moedas (Recupera 50 de vida para todos os heróis)");
        System.out.println("2. Poção de Força de Ataque - 40 moedas (Aumenta a força de ataque em 10 para todos os heróis)");
        System.out.println("3. Poção de Defesa - 50 moedas (Aumenta a defesa em 10 para todos os heróis)");
        System.out.println("Digite o número do item que deseja comprar:");

        Scanner scanner = new Scanner(System.in);
        int escolhaLoja = scanner.nextInt();

        switch (escolhaLoja) {
            case 1:
                if (Item.getDinheiro() >= 30) {
                    Item.adicionarDinheiro(-30); // Gastar 30 moedas
                    log.adicionarLog("Comprou uma Poção de Cura por 30 moedas.");
                    System.out.println(Cores.VERDE + "Você comprou uma Poção de Cura!" + Cores.RESET);
                    curarTodosOsHerois(herois, 50); // Recuperar 50 de vida para todos os heróis
                } else {
                    System.out.println(Cores.VERMELHO + "Você não tem dinheiro suficiente para comprar a Poção de Cura." + Cores.RESET);
                    log.adicionarLog("Tentou comprar uma Poção de Cura, mas não tinha dinheiro suficiente.");
                }
                break;

            case 2:
                if (Item.getDinheiro() >= 40) {
                    Item.adicionarDinheiro(-40); // Gastar 40 moedas
                    log.adicionarLog("Comprou uma Poção de Força de Ataque por 40 moedas.");
                    System.out.println(Cores.VERDE + "Você comprou uma Poção de Força de Ataque!" + Cores.RESET);
                    aumentarForcaAtaqueTodosOsHerois(herois, 5); // Aumentar 5 de força de ataque para todos os heróis
                } else {
                    System.out.println(Cores.VERMELHO + "Você não tem dinheiro suficiente para comprar a Poção de Força de Ataque." + Cores.RESET);
                    log.adicionarLog("Tentou comprar uma Poção de Força de Ataque, mas não tinha dinheiro suficiente.");
                }
                break;

            case 3:
                if (Item.getDinheiro() >= 50) {
                    Item.adicionarDinheiro(-50); // Gastar 50 moedas
                    log.adicionarLog("Comprou uma Poção de Defesa por 50 moedas.");
                    System.out.println(Cores.VERDE + "Você comprou uma Poção de Defesa!" + Cores.RESET);
                    aumentarDefesaTodosOsHerois(herois, 3); // Aumentar 3 de defesa para todos os heróis
                } else {
                    System.out.println(Cores.VERMELHO + "Você não tem dinheiro suficiente para comprar a Poção de Defesa." + Cores.RESET);
                    log.adicionarLog("Tentou comprar uma Poção de Defesa, mas não tinha dinheiro suficiente.");
                }
                break;


            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }


    public static void main(String[] args) {

        // Criação da instância do jogo
        Game jogo = new Game();

        // tratamento de exceção: Cada método que tiver uma exceção irá propaga-lá para ser tratada no main.

        boolean finalizaDificuldade = false;
        while (finalizaDificuldade == false) {

            try {
                jogo.escolherDificuldade();
                finalizaDificuldade = true;
            }
            catch (InputMismatchException e){
                System.out.println(Cores.VERMELHO + "Erro na digitação da dificuldade. Digite um número inteiro." + Cores.RESET);
            }
            catch (DificuldadeRangeException e){
                System.out.println(Cores.VERMELHO + e.getMessage() + Cores.RESET);
            }
        }

        jogo.gerarHerois();

        jogo.inicializarMonstros();

        boolean finalizaInteragir = false;
        while (finalizaInteragir == false) {
            try {
                jogo.interagir();
                finalizaInteragir = true;
            } catch (InputMismatchException e){
                System.out.println(Cores.VERMELHO + "Erro ao escolher uma opção no menu. Digite um número inteiro." + Cores.RESET);
            }
        }

    }
}
