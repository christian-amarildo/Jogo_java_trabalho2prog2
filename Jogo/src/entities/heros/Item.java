package entities.heros;

import entities.Player;

public class Item {
    private String nome;
    private String tipo; // Pode ser "Arma", "Poção", etc.
    private String efeito; // Exemplo: "cura", "dano", etc.
    private int bonusAtaque;
    private int bonusDefesa; // Bônus de defesa que o item fornece.
    private static int dinheiro; // Variável estática para dinheiro compartilhado entre todos os heróis.

    public Item(String nome, String tipo, String efeito, int bonusAtaque, int bonusDefesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
    }

    public static void adicionarDinheiro(int valor) {
        dinheiro += valor;
    }

    public static int getDinheiro() {
        return dinheiro;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public void setBonusDefesa(int bonusDefesa) {
        this.bonusDefesa = bonusDefesa;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }
}
