import Enums.ResultadoAtaque;
import entities.Log;
import entities.Player;
import entities.heros.Hero;
import entities.monsters.Monster;

import java.util.Random;

public class Turno {

    private Hero jogador;
    private Monster monstro;
    private Log log;

    public Turno(Hero jogador, Monster monstro) {
        this.jogador = jogador;
        this.monstro = monstro;
        this.log = new Log();
    }

    public void iniciarTurno() {
        while (jogador.getHp() > 0 && monstro.getHp() > 0) {
            // Turno do jogador
            jogarTurnoHeroi(jogador);

            // Verifica se o monstro foi derrotado após o ataque do jogador
            if (monstro.getHp() <= 0) {
                log.adicionarLog("Você derrotou o " + monstro.getNome() + "!");
                break;
            }

            // Turno do monstro
            jogarTurnoMonstro(monstro);

            // Verifica se o jogador foi derrotado após o ataque do monstro
            if (jogador.getHp() <= 0) {
                log.adicionarLog("Você foi derrotado pelo " + monstro.getNome() + "!");
                break;
            }
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
    }

    public void jogarTurnoHeroi(Hero heroi) {
        // A lógica do turno do herói
        System.out.println("\nTurno do herói: " + heroi.getNome());

        // Realiza o ataque normal
        ResultadoAtaque resultado = heroi.realizarAtaque(monstro);
        log.adicionarLog("Resultado do ataque de " + heroi.getNome() + ": " + resultado);
    }

    public void jogarTurnoMonstro(Monster monstro) {
        // A lógica do turno do monstro
        System.out.println("\nTurno do monstro: " + monstro.getNome());

        // Realiza a ação do monstro
        ResultadoAtaque resultado = monstro.realizarAtaque(jogador);
        log.adicionarLog("Resultado do ataque do monstro: " + resultado);
    }
}
