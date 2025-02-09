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

    // Método comum de realização de ataque para todos os monstros
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertar
        if (chanceAcerto <= getChanceAcerto()) {
            // O dano é calculado com base na força do monstro e a defesa do alvo
            int dano = Math.max(1, this.forcaAtaque - alvo.getDefesa());
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Se o ataque for crítico
        if (chanceAcerto > 90) { // Golpe crítico com 10% de chance
            int danoCritico = Math.max(1, (int) (this.forcaAtaque * 1.5 - alvo.getDefesa()));
            alvo.receberDano(danoCritico); // Aplica o dano crítico
            return ResultadoAtaque.CRITICAL_HIT;
        }

        // Caso o ataque falhe
        return ResultadoAtaque.ERROU;
    }

    // Método que pode ser sobrescrito por cada monstro para definir sua chance de acerto
    protected double getChanceAcerto() {
        return 60; // 60% de chance de acerto padrão, mas pode ser sobrescrito pelas subclasses
    }

    // Método de inteligência artificial (IA) para escolher o alvo mais vulnerável (menor HP)
    public void inteligenciaArtificial(List<Player> herois) {
        Player alvo = herois.stream()
                .min((h1, h2) -> Integer.compare(h1.getHp(), h2.getHp()))
                .orElse(null);

        if (alvo != null) {
            realizarAtaque(alvo);
        }
    }

    // Getters
    public String getTipo() { return tipo; }
    public int getDificuldade() { return dificuldade; }
}
