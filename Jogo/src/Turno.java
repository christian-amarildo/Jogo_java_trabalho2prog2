import Enums.ResultadoAtaque;
import entities.Log;
import entities.Player;
import entities.heros.Hero;
import entities.monsters.Monster;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Turno {

    private List<Player> jogadores;
    private int indiceJogadorAtual;
    private Log log;

    public Turno(List<Player> jogadores) {
        this.jogadores = jogadores;
        this.indiceJogadorAtual = 0;
        // Ordena os jogadores com base na velocidade (maior velocidade vem primeiro)
        jogadores.sort(Comparator.comparingInt(Player::getVelocidade).reversed());
    }

    public void iniciarTurno() {
        while (true) {
            Player jogadorAtual = jogadores.get(indiceJogadorAtual);

            if (jogadorAtual instanceof Hero) {
                jogarTurnoHeroi((Hero) jogadorAtual);
            } else {
                jogarTurnoMonstro((Monster) jogadorAtual);
            }

            // Avança para o próximo jogador no turno
            indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();

            if (verificarFimDeJogo()) {
                break;
            }
        }
    }

    public void jogarTurnoHeroi(Hero heroi) {
        // A lógica do turno do herói
        System.out.println("\nTurno do herói: " + heroi.getNome());

        // Realiza o ataque normal
        Player alvo = escolherAlvo();
        ResultadoAtaque resultado = heroi.realizarAtaque(alvo);
        log.adicionarLog("Resultado do ataque de " + heroi.getNome() + ": " + resultado);
    }

    public void jogarTurnoMonstro(Monster monstro) {
        // A lógica do turno do monstro
        System.out.println("\nTurno do monstro: " + monstro.getNome());

        // Realiza a ação do monstro com a IA
        monstro.inteligenciaArtificial(jogadores);
    }

    public Player escolherAlvo() {
        return jogadores.get(new Random().nextInt(jogadores.size())); // Escolhe aleatoriamente um alvo
    }

    public boolean verificarFimDeJogo() {
        // Verificação se o jogo acabou (não há heróis ou monstros vivos)
        boolean heroisVivos = jogadores.stream().anyMatch(p -> p instanceof Hero && p.getHp() > 0);
        boolean monstrosVivos = jogadores.stream().anyMatch(p -> p instanceof Monster && p.getHp() > 0);
        return !heroisVivos || !monstrosVivos;
    }
}
