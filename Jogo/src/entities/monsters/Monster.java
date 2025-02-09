package entities.monsters;

import Enums.ResultadoAtaque;
import entities.Player;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Player {
    private String tipo;
    private int dificuldade;
    private int dinheiroDropado; // Novo atributo

    public Monster(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String tipo, int dificuldade, int dinheiroDropado) {
        switch(dificuldade){
            case 1:
                break;
            case 2:
                hp *= 1.2;
                forcaAtaque *= 1.2;
                defesa *= 1.2;
                destreza *= 1.2;
                velocidade *= 1.2;
                dinheiroDropado *= 1.4;
                break;
            case 3:
                hp *= 1.4;
                forcaAtaque *= 1.5;
                defesa *= 1.5;
                destreza *= 1.5;
                velocidade *= 1.5;
                dinheiroDropado *= 1.8;
                break;
            default:
                System.out.println("Valor inválido!");
        }

        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.tipo = tipo;
        this.dificuldade = dificuldade;
        this.dinheiroDropado = dinheiroDropado;
    }

    // Método para obter o dinheiro dropado
    public int getDinheiroDropado() {
        return dinheiroDropado;
    }

    @Override
    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    public void inteligenciaArtificial(Player alvo) {
        // Seleciona aleatoriamente um herói para atacar
        Random random = new Random();
        // Escolhe aleatoriamente entre os heróis

        // A IA do monstro pode ter diferentes comportamentos:
        // Por exemplo, o monstro pode atacar o herói com o menor HP ou decidir aleatoriamente
        if (this.getHp() < 30) {
            // Se o monstro estiver com menos de 30 HP, ele pode tentar se curar ou evitar atacar
            System.out.println(this.getNome() + " está tentando fugir ou se curar, mas no momento ele atacará!");
        } else {
            // Ataque normal contra o alvo escolhido
            System.out.println(this.getNome() + " decide atacar " + alvo.getNome());
            realizarAtaque(alvo); // Ataca o alvo selecionado
        }
    }

    public String getTipo() {
        return tipo;
    }

    public int getDificuldade() {
        return dificuldade;
    }
}
