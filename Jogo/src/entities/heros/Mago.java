package entities.heros;

import Enums.ResultadoAtaque;
import entities.Habilidade;
import entities.Player;

public class Mago extends Hero {
    public Mago(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial, int dinheiro) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade, classe, habilidadeEspecial, dinheiro);
    }

    @Override
    public void equiparArma(Item arma) {
        if (arma.getTipo().equals("Arma")) {
            int bonus = arma.getBonusAtaque();
            if (arma.getNome().equals("Varinha")) {
                bonus += 8; // Bônus adicional para magos com varinhas
            }
            this.forcaAtaque += bonus; // Usando forcaAtaque
            System.out.println("Você equipou " + arma.getNome() + "! Força de ataque aumentada para " + this.forcaAtaque + ".");
        } else {
            System.out.println("Este item não é uma arma.");
        }
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        if (chanceAcerto <= this.destreza) {
            int dano = this.forcaAtaque - (int) (alvo.getDefesa() * 0.5);
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        } else if (chanceAcerto > 95) {
            int dano = (int) ((this.forcaAtaque - (alvo.getDefesa() * 0.5)) * 1.3);
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU;
    }
}