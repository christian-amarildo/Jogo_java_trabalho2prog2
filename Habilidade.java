import java.util.List;

public class Habilidade {
    private String nome;
    private String tipo; // "Dano", "Cura", "Defesa"
    private int custo;
    private String efeito;

    public Habilidade(String nome, String tipo, int custo, String efeito) {
        this.nome = nome;
        this.tipo = tipo;
        this.custo = custo;
        this.efeito = efeito;
    }

    public void executarHabilidade(Player usuario, Player alvo) {
        if (tipo.equals("Dano")) {
            int dano = (int) (Math.random() * 20) + 10;
            alvo.receberDano(dano);
            System.out.println(usuario.getNome() + " usou " + nome + " causando " + dano + " de dano a " + alvo.getNome());
        } else if (tipo.equals("Cura")) {
            int cura = (int) (Math.random() * 30) + 10;
            usuario.setHp(usuario.getHp() + cura);
            System.out.println(usuario.getNome() + " usou " + nome + " e curou " + cura + " HP.");
        }
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getEfeito() { return efeito; }
}
