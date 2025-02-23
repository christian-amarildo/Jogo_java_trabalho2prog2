package utils;

// Classe que representa a dificuldade do jogo
public class Dificuldade {
    // Nome da dificuldade (ex: Fácil, Médio, Difícil)
    private String nome;
    // Fator que multiplica o dano dos monstros com base na dificuldade
    private float fatorDano;
    // Fator que multiplica a resistência dos monstros com base na dificuldade
    private float fatorResistencia;
    private int nivelDificuldade;

    // Construtor da classe utils.Dificuldade
    public Dificuldade(String nome, float fatorDano, float fatorResistencia, int nivelDificuldade) {
        // Inicializa o nome da dificuldade
        this.nome = nome;
        // Inicializa o fator de dano
        this.fatorDano = fatorDano;
        // Inicializa o fator de resistência
        this.fatorResistencia = fatorResistencia;
        // Inicializa o nível de dificuldade
        this.nivelDificuldade = nivelDificuldade;
    }

    // Método para ajustar os parâmetros dos monstros com base na dificuldade
    // (Atualmente comentado, mas pode ser usado para modificar os monstros)
    // public void ajustarParametros(List<entities.monsters.Monster> monstros) {
    //     // Itera sobre a lista de monstros
    //     for (entities.monsters.Monster monstro : monstros) {
    //         // Aumenta o dano do monstro multiplicando pelo fator de dano
    //         monstro.setForcaAtaque((int) (monstro.getForcaAtaque() * fatorDano));
    //         // Aumenta a defesa do monstro multiplicando pelo fator de resistência
    //         monstro.setDefesa((int) (monstro.getDefesa() * fatorResistencia));
    //     }
    // }

    // Método getter para obter o nome da dificuldade
    public String getNome() { return nome; }

    // Método getter para obter o fator de dano
    public float getFatorDano() { return fatorDano; }

    // Método getter para obter o fator de resistência
    public float getFatorResistencia() { return fatorResistencia; }
    
    // Método getter para obter o nível da dificuldade
    public int getNivelDificuldade() { return this.nivelDificuldade; }
}
