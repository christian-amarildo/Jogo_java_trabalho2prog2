import java.util.List;

public class Hero extends Player {
    private String classe;
    private Habilidade habilidadeEspecial;

    public Hero(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.classe = classe;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        double chanceAcerto = Math.random() * 100;
        if (chanceAcerto <= this.destreza) {
            return ResultadoAtaque.ACERTOU;
        } else if (chanceAcerto > 95) {
            return ResultadoAtaque.CRITICAL_HIT;
        }
        return ResultadoAtaque.ERROU;
    }

    public void usarHabilidadeEspecial(Player alvo) {
        this.habilidadeEspecial.executarHabilidade(this, alvo);
    }

    // Getters
    public String getClasse() { return classe; }
    public Habilidade getHabilidadeEspecial() { return habilidadeEspecial; }
}
