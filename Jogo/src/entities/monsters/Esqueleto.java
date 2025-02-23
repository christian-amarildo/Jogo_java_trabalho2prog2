package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import utils.Dificuldade;

public class Esqueleto extends Monster {
    public Esqueleto(String nome, String tipo, Dificuldade dificuldade) {
        super(nome, 15, 10, 5, 15, 12, tipo, 20, dificuldade); // Dropa 20 moedas
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Verifica se o ataque acertou baseado na destreza
        if (chanceAcerto <= this.destreza) {
            float dano = Math.max(1, this.forcaAtaque - alvo.getDefesa()); // Dano mínimo de 1
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Verifica se houve ataque crítico (exemplo: 20% de chance de crítico)
        if (chanceAcerto > (100 - 20)) { // 20% de chance de crítico
            float dano = Math.max(1, (this.forcaAtaque - alvo.getDefesa()) * 2); // Dano crítico
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        // Caso o ataque falhe
        return ResultadoAtaque.ERROU;
    }
}
