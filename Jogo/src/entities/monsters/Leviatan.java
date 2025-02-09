package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

import Enums.ResultadoAtaque;
import entities.Player;

public class Leviatan extends Monster {
    public Leviatan(String nome, String tipo, int dificuldade) {
        super(nome, 200, 40, 20, 20, 15, tipo, dificuldade, 100); // Atributos ajustados
        // HP = 200 (era 300)
        // Força de Ataque = 40 (era 50)
        // Defesa = 20 (era 30)
        // Destreza = 20 (era 25)
        // Velocidade = 15 (era 20)
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        if (chanceAcerto <= 75) {
            int dano = this.forcaAtaque - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        return ResultadoAtaque.ERROU;
    }
}
