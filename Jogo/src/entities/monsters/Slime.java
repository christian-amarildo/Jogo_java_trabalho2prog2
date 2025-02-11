package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Slime extends Monster {
    public Slime(String nome, String tipo, int dificuldade) {
        super(nome, 10, 8, 5, 5, 10, tipo, dificuldade, 10); // Dropa 10 moedas
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        if (chanceAcerto <= 70) { // 60% de chance de acerto
            int dano = Math.max(1, this.forcaAtaque - alvo.getDefesa()); // Dano mínimo de 1
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        return ResultadoAtaque.ERROU;
    }
}
