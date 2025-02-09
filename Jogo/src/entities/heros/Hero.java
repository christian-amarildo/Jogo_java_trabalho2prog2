package entities.heros;

import entities.Habilidade;
import entities.Player;

public abstract class Hero extends Player {
    private String classe;
    private Habilidade habilidadeEspecial;
    private int dinheiro;
    private boolean emBatalha = false;

    public Hero(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial, int dinheiro) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.classe = classe;
        this.habilidadeEspecial = habilidadeEspecial;
        this.dinheiro = dinheiro;
    }

    public void equiparArma(Item arma) {
        if (arma.getTipo().equals("Arma")) {
            this.forcaAtaque += arma.getBonusAtaque(); // Aumenta o ataque do herói
            System.out.println("Você equipou " + arma.getNome() + "! Força de ataque aumentada para " + this.forcaAtaque + ".");
        } else {
            System.out.println("Este item não é uma arma.");
        }
    }

    public void equiparArmadura(Item armadura) {
        if (armadura.getTipo().equals("Defesa")) {
            this.defesa += armadura.getBonusDefesa(); // Aumenta a defesa do herói
            System.out.println("Você equipou " + armadura.getNome() + "! Defesa aumentada para " + this.defesa + ".");
        } else {
            System.out.println("Este item não é uma armadura.");
        }
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

    public int getDinheiro() {
        return dinheiro;
    }

    public void gastarDinheiro(int valor) {
        if (dinheiro >= valor) {
            dinheiro -= valor;
            System.out.println("Compra realizada com sucesso!");
        } else {
            System.out.println("Dinheiro insuficiente.");
        }
    }

    public String getClasse() {
        return classe;
    }

    public Habilidade getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public void mostrarDinheiro() {
        System.out.println("Dinheiro disponível: " + dinheiro);
    }

    public boolean getEmBatalha() {
        return this.emBatalha;
    }

    public void setEmBatalha(boolean emBatalha) {
        this.emBatalha = emBatalha;
    }
}