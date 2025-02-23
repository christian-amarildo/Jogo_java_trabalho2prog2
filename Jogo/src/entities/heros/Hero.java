package entities.heros;

import Enums.ResultadoAtaque;
import entities.Habilidade;
import entities.Player;

import java.util.List;

public abstract class Hero extends Player {
    private String classe;
    private Habilidade habilidadeEspecial;

    private boolean emBatalha = false;

    public Hero(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.classe = classe;
        this.habilidadeEspecial = habilidadeEspecial;

    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;

        // Se o ataque acertou, baseado na destreza do herói
        if (chanceAcerto <= this.destreza) {
            int dano = calcularDanoNormal(alvo);
            alvo.receberDano(dano);
            return ResultadoAtaque.ACERTOU;
        }

        // Verifica se houve um crítico
        if (chanceAcerto > 95) {
            int dano = calcularDanoCritico(alvo);
            alvo.receberDano(dano);
            return ResultadoAtaque.CRITICAL_HIT;
        }

        return ResultadoAtaque.ERROU; // Ataque falhou
    }

    public int calcularDanoNormal(Player alvo) {
        // O dano normal é calculado subtraindo a defesa do alvo
        int dano = this.forcaAtaque - alvo.getDefesa();
        if (dano < 0) dano = 0; // Impede dano negativo
        return dano;
    }

    public int calcularDanoCritico(Player alvo) {
        // O dano crítico é calculado da mesma forma, mas com um bônus multiplicador
        int dano = (this.forcaAtaque - alvo.getDefesa()) * 2; // Dano crítico dobra
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

    // Método para aumentar a força de ataque do herói
    public void aumentarForcaAtaque(int aumento) {
        this.forcaAtaque += aumento;
        System.out.println(this.nome + " teve sua força de ataque aumentada para " + this.forcaAtaque);
    }

    // Método para aumentar a defesa do herói
    public void aumentarDefesa(int aumento) {
        this.defesa += aumento;
        System.out.println(this.nome + " teve sua defesa aumentada para " + this.defesa);
    }

    public void curar(int quantidade) {
        this.hp += quantidade; // Aumenta a vida do herói
        System.out.println(this.nome + " recuperou " + quantidade + " de vida. Vida atual: " + this.hp);
    }

    public void verificaHPEmBatalha() {
        if(getHp() <= 0) {
            System.out.println("\nVocê está sem HP, se recupere antes de lutar!");
            setEmBatalha(false);
        }
    }



    public String getClasse() {
        return classe;
    }

    public Habilidade getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public boolean getEmBatalha() {
        return this.emBatalha;
    }

    public void setEmBatalha(boolean emBatalha) {
        this.emBatalha = emBatalha;
    }
}