// Definindo a classe Habilidade, que representa uma habilidade que pode ser usada em um jogo.
public class Habilidade {
    
    // Atributos da classe Habilidade
    private String nome;  // Nome da habilidade (ex: "Ataque Poderoso")
    private String tipo;  // Tipo da habilidade, pode ser "Dano", "Cura" ou "Defesa"
    private int custo;    // Custo da habilidade (não é utilizado no método atual, mas pode ser para energia ou recursos)
    private String efeito; // Efeito da habilidade, uma descrição do que ela faz (não é utilizado no método atual, mas poderia ser)

    // Construtor da classe Habilidade. Ele inicializa a habilidade com os valores passados como parâmetros.
    public Habilidade(String nome, String tipo, int custo, String efeito) {
        this.nome = nome;      // Inicializa o nome da habilidade
        this.tipo = tipo;      // Inicializa o tipo da habilidade
        this.custo = custo;    // Inicializa o custo da habilidade
        this.efeito = efeito;  // Inicializa o efeito da habilidade
    }

    // Método que executa a habilidade, afetando um jogador (usuario) e outro jogador (alvo)
    public void executarHabilidade(Player usuario, Player alvo) {
        
        // Se o tipo da habilidade for "Dano"
        if (tipo.equals("Dano")) {
            // Gera um dano aleatório entre 10 e 29 (o valor do dano é entre 10 e 29, inclusive)
            int dano = (int) (Math.random() * 20) + 10;
            
            // O alvo recebe o dano
            alvo.receberDano(dano);
            
            // Imprime no console a ação realizada (quem usou a habilidade, o nome da habilidade e o dano causado)
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

    // Métodos getters (acessadores) para obter os valores dos atributos da classe Habilidade
    public String getNome() { 
        return nome;  // Retorna o nome da habilidade
    }
    
    public String getTipo() { 
        return tipo;  // Retorna o tipo da habilidade
    }
    
    public String getEfeito() { 
        return efeito;  // Retorna o efeito da habilidade (não utilizado na execução, mas pode ser útil em outro contexto)
    }
}
