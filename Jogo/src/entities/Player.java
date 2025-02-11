package entities;

import Enums.ResultadoAtaque;
import entities.heros.Hero;
import entities.monsters.Monster;

import java.util.List;
import java.util.Random;

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
            atacante.realizarAtaque(alvo);  // O herói realiza o ataque
        }
        // Se o atacante for um monstro
        else if (atacante instanceof Monster) {
            // O monstro escolhe um herói aleatório para atacar
            Player alvo = aliados.get(random.nextInt(aliados.size()));
            System.out.println(atacante.getNome() + " (monstro) decide atacar " + alvo.getNome());
            atacante.realizarAtaque(alvo);  // O monstro realiza o ataque
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
