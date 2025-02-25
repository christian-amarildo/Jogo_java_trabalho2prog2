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
        if (chanceAcerto <= 75) { // 75% de chance de acerto
            float dano = this.forcaAtaque - alvo.getDefesa();
            if (dano < 0) dano = 0; // Impede dano negativo

            // Se houve dano crítico (exemplo: 20% de chance)
            double chanceCritico = Math.random() * 100;
            if (chanceCritico <= 20) { // 20% de chance de crítico
                dano *= 2;
                System.out.println(this.getNome() + " realizou um ataque CRÍTICO!");
                return ResultadoAtaque.CRITICAL_HIT;
            }

            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Ataque errou
        return ResultadoAtaque.ERROU;
    }
}
