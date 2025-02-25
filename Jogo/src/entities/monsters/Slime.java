package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import utils.Dificuldade;

public class Slime extends Monster {
    public Slime(String nome, String tipo, Dificuldade dificuldade) {
        super(nome, 10, 8, 5, 5, 10, tipo, 10, dificuldade); // Dropa 10 moedas
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
        if (chanceAcerto > (60)) {
            float dano = Math.max(1, (this.forcaAtaque - alvo.getDefesa()) * 2); // Dano crítico
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU; // Ataque falhou
    }
}
