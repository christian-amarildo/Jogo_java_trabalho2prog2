package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import entities.heros.Item;

public abstract class Monster extends Player {
    private String tipo;
    private int dificuldade;
    private int dinheiroDropado;

    public Monster(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String tipo, int dificuldade, int dinheiroDropado) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.tipo = tipo;
        this.dificuldade = dificuldade;
        this.dinheiroDropado = dinheiroDropado;
    }

    public int getDinheiroDropado() {
        return dinheiroDropado;
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        // Implementar a lógica de ataque do monstro
        int dano = this.forcaAtaque - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        return ResultadoAtaque.ACERTOU;
    }

    public void dropDinheiro(){
        if(this.getHp() <=0 ){
            System.out.println(this.getNome() + " foi derrotado e dropou " + this.dinheiroDropado + " moedas!");
            Item.adicionarDinheiro(this.dinheiroDropado);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getDificuldade() {
        return dificuldade;
    }
}
