package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Esqueleto extends Monster {
    public Esqueleto(String nome, String tipo, int dificuldade) {
        super(nome, 20, 11, 8, 15, 12, tipo, dificuldade, 20); // Dropa 20 moedas
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
