package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Leviatan extends Monster {
    public Leviatan(String nome, String tipo, int dificuldade) {
        super(nome, 300, 40, 20, 20, 15, tipo, dificuldade, 100); // Atributos ajustados
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertou
        if (chanceAcerto <= 75) { // 75% de chance de acerto
            int dano = this.forcaAtaque - alvo.getDefesa();
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
