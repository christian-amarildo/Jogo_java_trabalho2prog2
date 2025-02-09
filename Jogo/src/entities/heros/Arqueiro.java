package entities.heros;

import Enums.ResultadoAtaque;
import entities.Habilidade;
import entities.Player;

public class Arqueiro extends Hero {
    public Arqueiro(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial, int dinheiro) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade, classe, habilidadeEspecial, dinheiro);
    }

    @Override
    public void equiparArma(Item arma) {
        if (arma.getTipo().equals("Arma")) {
            int bonus = arma.getBonusAtaque();
            if (arma.getNome().equals("Arco")) {
                bonus += 6; // Bônus adicional para arqueiros com arcos
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
            int dano = this.forcaAtaque - alvo.getDefesa();
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        } else if (chanceAcerto > 90) {
            int dano = (int) ((this.forcaAtaque - alvo.getDefesa()) * 1.4);
            if (dano < 0) dano = 0;
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU;
    }
}