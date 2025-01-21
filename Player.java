import java.util.List;

public abstract class Player {
    protected String nome;
    protected int hp;
    protected int forcaAtaque;
    protected int defesa;
    protected int destreza;
    protected int velocidade;
    
    public Player(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade) {
        this.nome = nome;
        this.hp = hp;
        this.forcaAtaque = forcaAtaque;
        this.defesa = defesa;
        this.destreza = destreza;
        this.velocidade = velocidade;
    }

    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    public void receberDano(int dano) {
        int danoFinal = Math.max(0, dano - this.defesa);
        this.hp -= danoFinal;
        System.out.println(this.nome + " recebeu " + danoFinal + " de dano.");
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getHp() { return hp; }
    public int getForcaAtaque() { return forcaAtaque; }
    public int getDefesa() { return defesa; }
    public int getDestreza() { return destreza; }
    public int getVelocidade() { return velocidade; }
    public void setHp(int hp) { this.hp = hp; }
}
