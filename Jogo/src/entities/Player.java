package entities;

import Enums.ResultadoAtaque;

public abstract class Player {
    protected String nome;
    protected int hp;
    protected int forcaAtaque;
    protected int defesa;
    protected int destreza;
    protected int velocidade;
    protected Log log; // Atributo Log

    // Construtor da classe Player
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
        System.out.println("O herói recebeu " + dano + " de dano! Vida restante: " + this.hp);
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