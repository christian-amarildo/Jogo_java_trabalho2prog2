package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;

public class Leviatan extends Monster {
    public Leviatan(String nome, String tipo, int dificuldade) {
        super(nome, 300, 40, 20, 20, 15, tipo, dificuldade, 100); // Atributos ajustados
        // HP = 200 (era 300)
        // Força de Ataque = 40 (era 50)
        // Defesa = 20 (era 30)
        // Destreza = 20 (era 25)
        // Velocidade = 15 (era 20)
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100; // Determina a chance de acerto

        // Verifica se o ataque acertou (75% de chance de acerto)
        if (chanceAcerto <= 75) {
            // Cálculo de dano, considerando a defesa do alvo
            int dano = this.forcaAtaque - alvo.getDefesa();
            if (dano < 0) dano = 0; // Impede dano negativo

            // Garantir que o dano não seja zero
            if (dano == 0) {
                dano = 1; // Dano mínimo
            }

            // Implementando chance de dano crítico (exemplo: 20% de chance de crítico)
            double chanceCritico = Math.random() * 100;
            if (chanceCritico <= 20) {
                // Dano crítico dobra o valor
                dano *= 2;
                System.out.println(this.getNome() + " realizou um ataque CRÍTICO!");
            }

            // Aplica o dano ao alvo
            alvo.receberDano(dano);
            System.out.println(this.getNome() + " atacou e causou " + dano + " de dano a " + alvo.getNome());

            return ResultadoAtaque.ACERTOU;
        }

        // Caso o ataque erre
        System.out.println(this.getNome() + " errou o ataque!");
        return ResultadoAtaque.ERROU;
    }

}
