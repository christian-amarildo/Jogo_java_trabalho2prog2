package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

import java.util.List;

public abstract class Monster extends Player {
    private String tipo;
    private int dificuldade;
    private int dinheiroDropado; // Novo atributo

    public Monster(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String tipo, int dificuldade, int dinheiroDropado) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.tipo = tipo;
        this.dificuldade = dificuldade;
        this.dinheiroDropado = dinheiroDropado;
    }

    // Método para obter o dinheiro dropado
    public int getDinheiroDropado() {
        return dinheiroDropado;
    }

    @Override
    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    public void inteligenciaArtificial(List<Player> herois) {
        Player alvo = herois.stream()
                .min((h1, h2) -> Integer.compare(h1.getHp(), h2.getHp()))
                .orElse(null);

        if (alvo != null) {
            realizarAtaque(alvo);
        }
    }



    public String getTipo() { return tipo; }
    public int getDificuldade() { return dificuldade; }
}