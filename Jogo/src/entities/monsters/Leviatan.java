package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import utils.Dificuldade;

public class Leviatan extends Monster {
    public Leviatan(String nome, String tipo, Dificuldade dificuldade) {
        super(nome, 250, 35, 15, 20, 10, tipo, 100, dificuldade);
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertou
        if (chanceAcerto <= this.destreza) {
            float dano = Math.max(1, this.forcaAtaque - alvo.getDefesa());
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Se houve ataque crítico
        if (chanceAcerto > (80)) {
            float dano = Math.max(1, (this.forcaAtaque - alvo.getDefesa()) * 2); // Dano crítico
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU;
    }
}
