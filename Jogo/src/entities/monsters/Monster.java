package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import entities.heros.Item;
import utils.Dificuldade;

public abstract class Monster extends Player {
    private String tipo;
    private int dinheiroDropado;
    private Dificuldade dificuldade;

    public Monster(String nome, float hp, float forcaAtaque, float defesa, float destreza, float velocidade, String tipo, int dinheiroDropado, Dificuldade dificuldade) {
        super(nome, hp,forcaAtaque, defesa ,destreza, velocidade);
        this.forcaAtaque = forcaAtaque * dificuldade.getFatorDano();
        this.defesa = defesa * dificuldade.getFatorResistencia();
        this.tipo = tipo;
        this.dinheiroDropado = dinheiroDropado;
        this.dificuldade = dificuldade;
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        // Lógica de ataque do monstro
        float dano = this.forcaAtaque - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        return ResultadoAtaque.ACERTOU;
    }

    public void dropDinheiro(){
        if(this.getHp() <= 0){
            System.out.println(this.getNome() + " foi derrotado e dropou " + this.dinheiroDropado + " moedas!");
            Item.adicionarDinheiro(this.dinheiroDropado);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }


}
