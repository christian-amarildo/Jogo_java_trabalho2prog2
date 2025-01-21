import java.util.List;

public class Monster extends Player {
    private String tipo;
    private int dificuldade;

    public Monster(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String tipo, int dificuldade) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        this.tipo = tipo;
        this.dificuldade = dificuldade;
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

    public void inteligenciaArtificial(List<Player> herois) {
        // Simples IA: ataca o herói com menor HP
        Player alvo = herois.stream()
                .min((h1, h2) -> Integer.compare(h1.getHp(), h2.getHp()))
                .orElse(null);

        if (alvo != null) {
            realizarAtaque(alvo);
        }
    }

    // Getters
    public String getTipo() { return tipo; }
    public int getDificuldade() { return dificuldade; }
}
