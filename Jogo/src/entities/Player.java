package entities;

import Enums.ResultadoAtaque;
import entities.heros.Hero;
import entities.monsters.Monster;
import utils.Cores;


import java.util.List;
import java.util.Random;

/* A classe player é uma classe abstrata, ou seja, não pode ser instanciada. Ela é superclasse de Heroi e Monster.
* Suas duas subclasses também são abstratas, que finalmente serão implementadas por outras classes.
*
* O conceito de abstração está sendo utilizado nessas três classes, e também no método abstrato realizarAtaque(),
* sendo implementado pelas subclasses Heroi e Monster.
*
* O conceito de Herança pode ser encontrado na relação de Player, Heroi, Monster e as subclasses dessas ultimas duas classes.*/


public abstract class Player {
    protected String nome;
    protected int hp;
    protected int forcaAtaque;
    protected int defesa;
    protected int destreza;
    protected int velocidade;
    protected Log log;

    public Player(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade) {
        this.nome = nome;
        this.hp = hp;
        this.forcaAtaque = forcaAtaque;
        this.defesa = defesa;
        this.destreza = destreza;
        this.velocidade = velocidade;
    }

    // Método para definir o Log
    public void setLog(Log log) {
        this.log = log;
    }

    // Método abstrato para realizar ataque
    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    // Método para receber dano
    public void receberDano(int dano) {
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
            System.out.println(atacante.getNome() + " (herói) decide atacar " + alvo.getNome());

            if (atacante.realizarAtaque(alvo) == ResultadoAtaque.ACERTOU) {
                System.out.println(Cores.VERDE + atacante.getNome() + " acertou o ataque em " + alvo.getNome() + Cores.RESET);
            } else if (atacante.realizarAtaque(alvo) == ResultadoAtaque.CRITICAL_HIT) {
                System.out.println(Cores.CYAN + atacante.getNome() + " acertou um ataque crítico em " + alvo.getNome() + Cores.RESET);
            } else {
                System.out.println(Cores.VERMELHO + atacante.getNome() + " errou o ataque em " + alvo.getNome() + Cores.RESET);
            }


        }
        // Se o atacante for um monstro
        else if (atacante instanceof Monster) {
            // O monstro escolhe um herói aleatório para atacar
            Player alvo = aliados.get(random.nextInt(aliados.size()));
            System.out.println(atacante.getNome() + " (monstro) decide atacar " + alvo.getNome());

            // provavel mudança:
            if (atacante.realizarAtaque(alvo) == ResultadoAtaque.ACERTOU) {
                System.out.println(Cores.VERDE + atacante.getNome() + " acertou o ataque em " + alvo.getNome() + Cores.RESET);
            } else if (atacante.realizarAtaque(alvo) == ResultadoAtaque.CRITICAL_HIT) {
                System.out.println(Cores.CYAN + atacante.getNome() + " acertou um ataque crítico em " + alvo.getNome() + Cores.RESET);
            } else {
                System.out.println(Cores.VERMELHO + atacante.getNome() + " errou o ataque em " + alvo.getNome() + Cores.RESET);
            }// O monstro realiza o ataque
        }
    }


    // Método para obter a maior velocidade da lista de jogadores
    private int getMaiorVelocidade(List<Monster> players) {
        int maiorVelocidade = 0;
        for (Player player : players) {
            if (player.getVelocidade() > maiorVelocidade) {
                maiorVelocidade = player.getVelocidade();
            }
        }
        return maiorVelocidade;
    }

    // Getters e Setters
    public String getNome() { return nome; }

    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public int getForcaAtaque() { return forcaAtaque; }

    public int getDefesa() { return defesa; }

    public int getDestreza() { return destreza; }

    public int getVelocidade() { return velocidade; }
}
