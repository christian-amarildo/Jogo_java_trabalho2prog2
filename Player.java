import java.util.List;

// A classe abstrata Player representa um jogador ou uma entidade que pode interagir no jogo, como heróis ou monstros.
// Ela define atributos comuns (como nome, HP, força de ataque, etc.) e métodos básicos, mas não implementa a lógica de ataque, que deve ser definida nas classes filhas.
public abstract class Player {

    // Atributo nome: nome do jogador (ou entidade).
    protected String nome;
    
    // Atributo hp: pontos de vida do jogador (ou entidade).
    protected int hp;
    
    // Atributo forcaAtaque: força de ataque do jogador (ou entidade).
    protected int forcaAtaque;
    
    // Atributo defesa: defesa do jogador (ou entidade), usada para reduzir o dano recebido.
    protected int defesa;
    
    // Atributo destreza: destreza do jogador (ou entidade), influencia na chance de acertar um ataque.
    protected int destreza;
    
    // Atributo velocidade: velocidade do jogador (ou entidade), pode influenciar na ordem de ação.
    protected int velocidade;

    // Construtor da classe Player. Este construtor é chamado para inicializar os atributos do jogador (ou entidade) com os valores fornecidos.
    public Player(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade) {
        this.nome = nome;  // Inicializa o nome do jogador.
        this.hp = hp;      // Inicializa os pontos de vida do jogador.
        this.forcaAtaque = forcaAtaque;  // Inicializa a força de ataque do jogador.
        this.defesa = defesa;  // Inicializa a defesa do jogador.
        this.destreza = destreza;  // Inicializa a destreza do jogador.
        this.velocidade = velocidade;  // Inicializa a velocidade do jogador.
    }

    // Método abstrato realizarAtaque: cada classe que herdar Player deverá implementar esse método,
    // definindo como o ataque será realizado.
    // Ele retorna um valor do tipo ResultadoAtaque, que pode ser ACERTOU, ERROU, ou CRITICAL_HIT.
    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    // Método que permite que o jogador (ou entidade) receba dano durante o combate.
    // O dano recebido é calculado levando em consideração a defesa do jogador.
    public void receberDano(int dano) {
        // O dano final é o dano recebido menos a defesa, mas nunca será negativo (dano não pode ser menor que 0).
        int danoFinal = Math.max(0, dano - this.defesa);
        
        // Reduz os pontos de vida do jogador de acordo com o dano final calculado.
        this.hp -= danoFinal;
        
        // Imprime uma mensagem informando o quanto de dano o jogador recebeu.
        System.out.println(this.nome + " recebeu " + danoFinal + " de dano.");
    }

    // Métodos getters e setters para acessar e modificar os atributos do jogador.

    // Retorna o nome do jogador.
    public String getNome() { return nome; }

    // Retorna os pontos de vida do jogador.
    public int getHp() { return hp; }

    // Retorna a força de ataque do jogador.
    public int getForcaAtaque() { return forcaAtaque; }

    // Retorna a defesa do jogador.
    public int getDefesa() { return defesa; }

    // Retorna a destreza do jogador.
    public int getDestreza() { return destreza; }

    // Retorna a velocidade do jogador.
    public int getVelocidade() { return velocidade; }

    // Define o valor dos pontos de vida (HP) do jogador.
    public void setHp(int hp) { this.hp = hp; }
}
