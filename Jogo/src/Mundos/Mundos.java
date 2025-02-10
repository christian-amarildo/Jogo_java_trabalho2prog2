package Mundos;

import java.util.ArrayList;

import entities.monsters.Monster;

public class Mundos {
    private String nome;
    private ArrayList<Monster> monstrosLocais;
    private boolean mundoAtual = false;

    public Mundos(String nome, ArrayList<Monster> monstrosLocais) {
        this.nome = nome;
        this.monstrosLocais = monstrosLocais;
    }
}
