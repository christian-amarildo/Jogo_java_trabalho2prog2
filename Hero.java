// A classe Hero é uma subclasse de Player, representando um herói no jogo.
public class Hero extends Player {
    
    // Atributos específicos da classe Hero
    private String classe;  // Classe do herói (ex: "Guerreiro", "Mago", etc.)
    private Habilidade habilidadeEspecial;  // Habilidade especial que o herói pode usar, uma instância da classe Habilidade

    // Construtor da classe Hero, que recebe os parâmetros necessários para inicializar a classe Hero
    public Hero(String nome, int hp, int forcaAtaque, int defesa, int destreza, int velocidade, String classe, Habilidade habilidadeEspecial) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);  // Chama o construtor da classe pai (Player) para inicializar os atributos herdados
        this.classe = classe;  // Inicializa o atributo classe do herói com o valor passado
        this.habilidadeEspecial = habilidadeEspecial;  // Inicializa a habilidade especial do herói
    }

    // Método que realiza o ataque do herói contra outro jogador (alvo)
    @Override  // Anotação que indica que esse método está sobrescrevendo um método da classe pai (Player)
    public ResultadoAtaque realizarAtaque(Player alvo) {
        
        // Gera uma chance aleatória de acerto do ataque (valor entre 0 e 100)
        double chanceAcerto = Math.random() * 100;
        
        // Se a chance de acerto for menor ou igual à destreza do herói, o ataque acerta
        if (chanceAcerto <= this.destreza) {
            return ResultadoAtaque.ACERTOU;  // Retorna que o ataque acertou
        } 
        // Se a chance de acerto for maior que 95, o ataque é um "Critical Hit" (Golpe Crítico)
        else if (chanceAcerto > 95) {
            return ResultadoAtaque.CRITICAL_HIT;  // Retorna que o ataque foi um Golpe Crítico
        }
        
        // Se a chance de acerto não atender a nenhuma das condições acima, o ataque erra
        return ResultadoAtaque.ERROU;  // Retorna que o ataque errou
    }

    // Método que permite o herói usar sua habilidade especial em outro jogador (alvo)
    public void usarHabilidadeEspecial(Player alvo) {
        this.habilidadeEspecial.executarHabilidade(this, alvo);  // Executa a habilidade especial, passando o herói como usuário e o alvo
    }

    // Métodos getters (acessadores) para obter os valores dos atributos da classe Hero
    public String getClasse() { 
        return classe;  // Retorna a classe do herói (ex: "Guerreiro")
    }
    
    public Habilidade getHabilidadeEspecial() { 
        return habilidadeEspecial;  // Retorna a habilidade especial do herói
    }
}
