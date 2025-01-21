import java.util.List;

public class Dificuldade {
    private String nome;
    private float fatorDano;
    private float fatorResistencia;
    private int numeroMonstros;

    public Dificuldade(String nome, float fatorDano, float fatorResistencia, int numeroMonstros) {
        this.nome = nome;
        this.fatorDano = fatorDano;
        this.fatorResistencia = fatorResistencia;
        this.numeroMonstros = numeroMonstros;
    }

    public void ajustarParametros(List<Monster> monstros) {
        for (Monster monstro : monstros) {
            monstro.setForcaAtaque((int) (monstro.getForcaAtaque() * fatorDano));
            monstro.setDefesa((int) (monstro.getDefesa() * fatorResistencia));
        }
    }

    // Getters
    public String getNome() { return nome; }
    public float getFatorDano() { return fatorDano; }
    public float getFatorResistencia() { return fatorResistencia; }
    public int getNumeroMonstros() { return numeroMonstros; }
}
