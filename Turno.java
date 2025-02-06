import java.util.List;
import java.util.Random;

// A classe Turno controla o ciclo de turnos no combate. Ela gerencia os jogadores e alterna entre eles durante o combate.
public class Turno {
    
    // Lista de jogadores que participam do combate.
    private List<Player> jogadores;
    
    // Índice que indica qual jogador está realizando o turno atualmente.
    private int indiceJogadorAtual;

    // Construtor da classe Turno, que recebe uma lista de jogadores e inicializa o índice do jogador atual.
    public Turno(List<Player> jogadores) {
        this.jogadores = jogadores;  // Atribui a lista de jogadores à variável da classe.
        this.indiceJogadorAtual = 0;  // Inicializa o índice com o primeiro jogador.
    }

    // Método que inicia o turno, alternando entre os jogadores até que o jogo termine.
    public void iniciarTurno() {
        while (true) {  // O loop continuará até que o fim do jogo seja verificado.
            Player jogadorAtual = jogadores.get(indiceJogadorAtual);  // Obtém o jogador que está realizando o turno.

            // Verifica se o jogador atual é um herói ou um monstro e chama o respectivo método de turno.
            if (jogadorAtual instanceof Hero) {
                jogarTurnoHeroi((Hero) jogadorAtual);  // Se for herói, joga o turno do herói.
            } else {
                jogarTurnoMonstro((Monster) jogadorAtual);  // Se for monstro, joga o turno do monstro.
            }

            // Alterna para o próximo jogador. Se chegar ao final da lista, volta para o início.
            indiceJogadorAtual = (indiceJogadorAtual + 1) % jogadores.size();

            // Verifica se o jogo terminou após o turno do jogador atual.
            if (verificarFimDeJogo()) {
                break;  // Se o jogo terminou, sai do loop.
            }
        }
    }

    // Método que define a lógica do turno de um herói.
    public void jogarTurnoHeroi(Hero heroi) {
        System.out.println("\nTurno do herói: " + heroi.getNome());  // Imprime o nome do herói.

        // Exemplo de como o herói pode usar sua habilidade especial.
        heroi.usarHabilidadeEspecial(jogadores.get(indiceJogadorAtual + 1));  // Usa a habilidade especial do herói no próximo jogador (isso pode ser ajustado).

        // Seleciona um alvo aleatório para o ataque normal do herói.
        Player alvo = escolherAlvo();

        // Realiza o ataque normal e captura o resultado.
        ResultadoAtaque resultado = heroi.realizarAtaque(alvo);
        System.out.println("Resultado do ataque: " + resultado);  // Imprime o resultado do ataque.

        // Se o ataque acertou, o herói causa dano ao alvo.
        if (resultado == ResultadoAtaque.ACERTOU) {
            alvo.receberDano(heroi.getForcaAtaque());  // Aplica o dano ao alvo com base na força de ataque do herói.
        }
    }

    // Método que define a lógica do turno de um monstro.
    public void jogarTurnoMonstro(Monster monstro) {
        System.out.println("\nTurno do monstro: " + monstro.getNome());  // Imprime o nome do monstro.

        // O monstro realiza sua ação com base na inteligência artificial.
        monstro.inteligenciaArtificial(jogadores);  // Chama a inteligência artificial do monstro para decidir a ação.
    }

    // Método que escolhe aleatoriamente um alvo da lista de jogadores.
    public Player escolherAlvo() {
        // Escolhe um alvo aleatório da lista de jogadores usando a classe Random.
        return jogadores.get(new Random().nextInt(jogadores.size()));  // Retorna o jogador aleatório escolhido.
    }

    // Método que verifica se o jogo terminou, ou seja, se todos os heróis ou monstros foram derrotados.
    public boolean verificarFimDeJogo() {
        // Verifica se há heróis vivos no jogo.
        boolean heroisVivos = jogadores.stream().anyMatch(p -> p instanceof Hero && p.getHp() > 0);
        
        // Verifica se há monstros vivos no jogo.
        boolean monstrosVivos = jogadores.stream().anyMatch(p -> p instanceof Monster && p.getHp() > 0);

        // Se não houver heróis ou monstros vivos, o combate terminou.
        if (!heroisVivos || !monstrosVivos) {
            System.out.println("Fim do combate!");  // Imprime mensagem de fim de combate.
            return true;  // Retorna verdadeiro, indicando que o jogo terminou.
        }

        // Se houver heróis e monstros vivos, o combate continua.
        return false;
    }
}
