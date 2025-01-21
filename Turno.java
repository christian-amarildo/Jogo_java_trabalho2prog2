import java.util.List;
import java.util.Random;

public class Turno {
    private List<Player> jogadores;
    private int indiceJogadorAtual;

    public Turno(List<Player> jogadores) {
        this.jogadores = jogadores;
        this.indiceJogadorAtual = 0;
    }

    public void iniciarTurno() {
        while (true) {
            Player jogadorAtual = jogadores.get(indiceJogadorAtual);
            if (jogadorAtual instanceof Hero) {
                jogarTurnoHeroi((Hero) jogadorAtual);
            } else {
                jogarTurnoMonstro((Monster) jogadorAtual);
            }

            // Alternar para o próximo jogador
            indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();

            if (verificarFimDeJogo()) {
                break;
            }
        }
    }

    public void jogarTurnoHeroi(Hero heroi) {
        System.out.println("\nTurno do herói: " + heroi.getNome());
        // Exemplo de interação com a habilidade
        heroi.usarHabilidadeEspecial(jogadores.get(indiceJogadorAtual + 1));

        // Fazer um ataque normal
        Player alvo = escolherAlvo();
        ResultadoAtaque resultado = heroi.realizarAtaque(alvo);
        System.out.println("Resultado do ataque: " + resultado);

        if (resultado == ResultadoAtaque.ACERTOU) {
            alvo.receberDano(heroi.getForcaAtaque());
        }
    }

    public void jogarTurnoMonstro(Monster monstro) {
        System.out.println("\nTurno do monstro: " + monstro.getNome());
        monstro.inteligenciaArtificial(jogadores);
    }

    public Player escolherAlvo() {
        // Exemplo simples: escolha aleatória de um alvo
        return jogadores.get(new Random().nextInt(jogadores.size()));
    }

    public boolean verificarFimDeJogo() {
        boolean heroisVivos = jogadores.stream().anyMatch(p -> p instanceof Hero && p.getHp() > 0);
        boolean monstrosVivos = jogadores.stream().anyMatch(p -> p instanceof Monster && p.getHp() > 0);

        if (!heroisVivos || !monstrosVivos) {
            System.out.println("Fim do combate!");
            return true;
        }

        return false;
    }
}
