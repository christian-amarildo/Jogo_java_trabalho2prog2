// A classe Monster representa um monstro que herda as propriedades e comportamentos de um jogador (Player).
// Ela possui atributos adicionais como tipo e dificuldade, e um método de Inteligência Artificial.
public class Monster extends Player {
    
    // Atributo tipo: define o tipo do monstro (exemplo: "Dragão", "Esqueleto", etc.)
    private String tipo;
    
    // Atributo dificuldade: define a dificuldade do monstro, que pode influenciar nas suas ações ou estatísticas.
    private int dificuldade;

    // Construtor da classe Monster. Inicializa todos os atributos, incluindo os da classe pai Player.
    public Monster(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String tipo, int dificuldade) {
        // Chama o construtor da classe pai Player para inicializar atributos comuns a todos os jogadores (como nome, HP, etc.)
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        
        // Inicializa o tipo do monstro.
        this.tipo = tipo;
        
        // Inicializa a dificuldade do monstro.
        this.dificuldade = dificuldade;
    }

    // Método sobrescrito da classe Player para realizar um ataque. Retorna um valor do tipo ResultadoAtaque.
    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        // Calcula a chance de acerto do monstro. A chance é influenciada pela destreza do monstro.
        double chanceAcerto = Math.random() * 100;
        
        // Verifica se o ataque acerta. A chance de acerto é determinada pela destreza do monstro.
        if (chanceAcerto <= this.destreza) {
            // Se a chance de acerto for menor ou igual à destreza, o ataque acerta.
            return ResultadoAtaque.ACERTOU;
        } else if (chanceAcerto > 95) {
            // Se a chance de acerto for maior que 95, o ataque é um golpe crítico.
            return ResultadoAtaque.CRITICAL_HIT;
        }
        
        // Se nenhuma das condições acima for atendida, o ataque erra.
        return ResultadoAtaque.ERROU;
    }

    // Método de Inteligência Artificial (IA) do monstro. 
    // A IA decide qual herói atacar com base em um critério simples: atacar o herói com menor HP.
    public void inteligenciaArtificial(List<Player> herois) {
        // Seleciona o herói com menor HP da lista de heróis.
        // A operação "min" compara os HPs de todos os heróis e retorna o herói com o menor HP.
        Player alvo = herois.stream()
                .min((h1, h2) -> Integer.compare(h1.getHp(), h2.getHp()))  // Compara dois heróis com base no HP.
                .orElse(null);  // Se não houver heróis, retorna null.

        // Se o alvo não for nulo (ou seja, se existe ao menos um herói para atacar),
        // o monstro realiza o ataque no herói selecionado.
        if (alvo != null) {
            realizarAtaque(alvo);  // Chama o método realizarAtaque para atacar o herói com menor HP.
        }
    }

    // Métodos getters para acessar os atributos tipo e dificuldade do monstro.
    public String getTipo() { return tipo; }  // Retorna o tipo do monstro (exemplo: "Dragão").
    public int getDificuldade() { return dificuldade; }  // Retorna a dificuldade do monstro (exemplo: 1, 2, 3).
}
