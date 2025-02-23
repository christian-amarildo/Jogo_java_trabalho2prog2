package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Slime extends Monster {
    public Slime(String nome, String tipo, int dificuldade) {
        super(nome, 10 + dificuldade * 2, 8 + dificuldade * 3, 5, 5, 10, tipo, dificuldade, 10 + dificuldade * 2); // Ajuste de vida, força de ataque e dinheiro dropado
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertou
        if (chanceAcerto <= this.destreza) {
            int dano = Math.max(1, this.forcaAtaque - alvo.getDefesa());
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Se houve ataque crítico
        if (chanceAcerto > (100 - 20)) {  // 20% de chance de crítico
            int dano = Math.max(1, (this.forcaAtaque - alvo.getDefesa()) * 2); // Dano crítico
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU; // Ataque falhou
    }
}
