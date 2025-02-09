package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Slime extends Monster {
    public Slime(String nome, String tipo, int dificuldade) {
        super(nome, 15, 10, 5, 5, 10, tipo, dificuldade, 10); // Dropa 10 moedas
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        // Definindo uma chance de acerto de 60%, ajustável
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertar
        if (chanceAcerto <= 60) { // 60% de chance de acerto
            // O dano é baseado na força de ataque do Slime e na defesa do alvo
            // Se o valor do dano for menor que 1, garantimos o valor mínimo de 1
            int dano = Math.max(1, this.forcaAtaque - alvo.getDefesa());
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Se o ataque for crítico (chanceAcerto > 90), calculamos dano adicional
        if (chanceAcerto > 90) {
            int danoCritico = Math.max(1, (int) (this.forcaAtaque * 1.5 - alvo.getDefesa()));
            alvo.receberDano(danoCritico); // Aplica o dano crítico
            return ResultadoAtaque.CRITICAL_HIT;
        }

        // Se não acertou, retorna ERROU
        return ResultadoAtaque.ERROU;
    }
}
