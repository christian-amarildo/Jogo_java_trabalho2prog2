package entities;

public class Habilidade {

    private String nome;
    private String tipo;
    private int custo;
    private String efeito;

    public Habilidade(String nome, String tipo, int custo, String efeito) {
        this.nome = nome;      // Inicializa o nome da habilidade
        this.tipo = tipo;      // Inicializa o tipo da habilidade
        this.custo = custo;    // Inicializa o custo da habilidade
        this.efeito = efeito;  // Inicializa o efeito da habilidade
    }

    public void executarHabilidade(Player usuario, Player alvo) {

        if (tipo.equals("Dano")) {
            int dano = (int) (Math.random() * 20) + 10;

            alvo.receberDano(dano);

            System.out.println(usuario.getNome() + " usou " + nome + " causando " + dano + " de dano a " + alvo.getNome());
        }

        // Se o tipo da habilidade for "Cura"
        else if (tipo.equals("Cura")) {
            // Gera uma cura aleatória entre 10 e 39 (o valor da cura é entre 10 e 39, inclusive)
            int cura = (int) (Math.random() * 30) + 10;
            
            // O usuário recebe a cura, ou seja, seu HP é aumentado pela quantidade de cura
            usuario.setHp(usuario.getHp() + cura);
            
            // Imprime no console a ação realizada (quem usou a habilidade, o nome da habilidade e a quantidade de HP curado)
            System.out.println(usuario.getNome() + " usou " + nome + " e curou " + cura + " HP.");
        }
    }


    public String getNome() { 
        return nome;
    }
    
    public String getTipo() { 
        return tipo;
    }
    
    public String getEfeito() { 
        return efeito;
    }
}
