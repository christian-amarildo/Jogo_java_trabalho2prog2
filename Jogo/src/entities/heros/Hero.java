package entities.heros;

import Enums.ResultadoAtaque;
import entities.Player;

import java.util.List;

public abstract class Hero extends Player {
    private String classe;
    private boolean emBatalha = false;

    // Defina os limites máximos para força de ataque, vida e defesa
    private static final int LIMITE_FORCA_ATAQUE = 50;  // Exemplo de limite máximo para força de ataque
    private static final int LIMITE_DEFESA = 30;         // Exemplo de limite máximo para defesa
    private static final int LIMITE_HP = 500;            // Exemplo de limite máximo para vida

    public Hero(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.classe = classe;
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertou, baseado na destreza do herói
        if (chanceAcerto <= this.destreza) {
            float dano = calcularDanoNormal(alvo);
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Verifica se houve um crítico
        if (chanceAcerto > 75) {
            float dano = Math.max(1, (this.forcaAtaque - alvo.getDefesa()) * 2);
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU; // Ataque falhou
    }

    public float calcularDanoNormal(Player alvo) {
        // O dano normal é calculado subtraindo a defesa do alvo
        float dano = this.forcaAtaque - alvo.getDefesa();
        if (dano < 0) dano = 0; // Impede dano negativo
        return dano;
    }

    public static void curarTodosOsHerois(List<Hero> herois, int quantidade) {
        for (Hero heroi : herois) {
            heroi.curar(quantidade); // Cura cada herói
        }
    }

    public static void aumentarForcaAtaqueTodosOsHerois(List<Hero> herois, int aumento) {
        for (Hero heroi : herois) {
            heroi.aumentarForcaAtaque(aumento); // Aumenta a força de ataque de cada herói
        }
    }

    public static void aumentarDefesaTodosOsHerois(List<Hero> herois, int aumento) {
        for (Hero heroi : herois) {
            heroi.aumentarDefesa(aumento); // Aumenta a defesa de cada herói
        }
    }

    // Método para aumentar a força de ataque do herói com limite
    public void aumentarForcaAtaque(int aumento) {
        if (this.forcaAtaque + aumento > LIMITE_FORCA_ATAQUE) {
            this.forcaAtaque = LIMITE_FORCA_ATAQUE; // Aplica o limite
            System.out.println(this.nome + " atingiu o limite de força de ataque: " + this.forcaAtaque);
        } else {
            this.forcaAtaque += aumento;
            System.out.println(this.nome + " teve sua força de ataque aumentada para " + this.forcaAtaque);
        }
    }

    // Método para aumentar a defesa do herói com limite
    public void aumentarDefesa(int aumento) {
        if (this.defesa + aumento > LIMITE_DEFESA) {
            this.defesa = LIMITE_DEFESA; // Aplica o limite
            System.out.println(this.nome + " atingiu o limite de defesa: " + this.defesa);
        } else {
            this.defesa += aumento;
            System.out.println(this.nome + " teve sua defesa aumentada para " + this.defesa);
        }
    }

    // Método para curar o herói com limite
    public void curar(int quantidade) {
        if (this.hp + quantidade > LIMITE_HP) {
            this.hp = LIMITE_HP; // Aplica o limite
            System.out.println(this.nome + " atingiu o limite de vida: " + this.hp);
        } else {
            this.hp += quantidade; // Aumenta a vida do herói
            System.out.println(this.nome + " recuperou " + quantidade + " de vida. Vida atual: " + this.hp);
        }
    }

    public void setEmBatalha(boolean emBatalha) {
        this.emBatalha = emBatalha;
    }
}
