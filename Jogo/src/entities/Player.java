package entities;

import Enums.ResultadoAtaque;
import entities.heros.Hero;
import entities.monsters.Monster;
import utils.Cores;


import java.util.List;
import java.util.Random;

/*
A classe player é uma classe abstrata, ou seja, não pode ser instanciada. Ela é superclasse de Heroi e Monster.
* Suas duas subclasses também são abstratas, que finalmente serão implementadas por outras classes (Mago, Slime, etc).
*
* O conceito de abstração está sendo utilizado nessas três classes, e também no método abstrato realizarAtaque(),
* que é implementado pelas subclasses Heroi e Monster.
*
* O conceito de Herança pode ser encontrado no relacionamento de Player, Heroi, Monster e suas subclasses.
*/


public abstract class Player {
    protected String nome;
    protected float hp;
    protected float forcaAtaque;
    protected float defesa;
    protected float destreza;
    protected float velocidade;
    protected Log log;

    // Construtor que inicializa os atributos
    public Player(String nome, float hp, float forcaAtaque, float defesa, float destreza, float velocidade) {
        this.nome = nome;
        this.hp = hp;
        this.forcaAtaque = forcaAtaque;
        this.defesa = defesa;
        this.destreza = destreza;
        this.velocidade = velocidade;
        this.log = new Log();  // Inicializa o log aqui
    }

    // Método para definir o Log (se quiser configurar após a criação)
    public void setLog(Log log) {
        this.log = log;
    }

    // Método abstrato para realizar ataque
    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    // Método para receber dano
    public void receberDano(float dano) {
        this.hp -= dano;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println("O " + this.nome + " recebeu " + dano + " de dano! Vida restante: " + this.hp);
    }

    // Método para a inteligência artificial de ataque
    public void inteligenciaArtificial(List<Monster> inimigos, List<Hero> aliados) {
        Random random = new Random();

        // Determina quem ataca primeiro com base na maior velocidade
        Player atacante = (this.velocidade > getMaiorVelocidade(inimigos)) ? this : inimigos.get(random.nextInt(inimigos.size()));

        // Se o atacante for um herói
        if (atacante instanceof Hero) {
            // O herói escolhe um monstro aleatório para atacar
            Player alvo = inimigos.get(random.nextInt(inimigos.size()));
            String evento = atacante.getNome() + " (herói) decide atacar " + alvo.getNome();
            System.out.println(evento);  // Exibe no terminal
            log.adicionarLog(evento);  // Adiciona ao log

            if (atacante.realizarAtaque(alvo) == ResultadoAtaque.ACERTOU) {
                evento = Cores.VERDE + atacante.getNome() + " acertou o ataque em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            } else if (atacante.realizarAtaque(alvo) == ResultadoAtaque.CRITICAL_HIT) {
                evento = Cores.CYAN + atacante.getNome() + " acertou um ataque crítico em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            } else {
                evento = Cores.VERMELHO + atacante.getNome() + " errou o ataque em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            }

        }
        // Se o atacante for um monstro
        else if (atacante instanceof Monster) {
            // O monstro escolhe um herói aleatório para atacar
            Player alvo = aliados.get(random.nextInt(aliados.size()));
            String evento = atacante.getNome() + " (monstro) decide atacar " + alvo.getNome();
            System.out.println(evento);  // Exibe no terminal
            log.adicionarLog(evento);  // Adiciona ao log

            if (atacante.realizarAtaque(alvo) == ResultadoAtaque.ACERTOU) {
                evento = Cores.VERDE + atacante.getNome() + " acertou o ataque em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            } else if (atacante.realizarAtaque(alvo) == ResultadoAtaque.CRITICAL_HIT) {
                evento = Cores.CYAN + atacante.getNome() + " acertou um ataque crítico em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            } else {
                evento = Cores.VERMELHO + atacante.getNome() + " errou o ataque em " + alvo.getNome() + Cores.RESET;
                System.out.println(evento);  // Exibe no terminal
                log.adicionarLog(evento);  // Adiciona ao log
            }
        }
    }

    // Método para obter a maior velocidade da lista de jogadores
    private float getMaiorVelocidade(List<Monster> players) {
        float maiorVelocidade = 0;
        for (Player player : players) {
            if (player.getVelocidade() > maiorVelocidade) {
                maiorVelocidade = player.getVelocidade();
            }
        }
        return maiorVelocidade;
    }

    // Getters e Setters
    public String getNome() { return nome; }

    public float getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public float getForcaAtaque() { return forcaAtaque; }

    public float getDefesa() { return defesa; }

    public float getDestreza() { return destreza; }

    public float getVelocidade() { return velocidade; }
}