// A classe Item representa um item no jogo, com atributos como nome, tipo e efeito.
public class Item {
    
    // Atributos da classe Item, que armazenam o nome, o tipo e o efeito do item.
    private String nome;   // Nome do item (ex: "Poção de Cura", "Espada Mágica", etc.)
    private String tipo;   // Tipo do item (ex: "Consumível", "Equipamento", etc.)
    private String efeito; // Efeito do item (ex: "cura", "dano", etc.)

    // Construtor da classe Item. Inicializa os atributos nome, tipo e efeito.
    public Item(String nome, String tipo, String efeito) {
        this.nome = nome;      // Inicializa o atributo nome com o valor passado no parâmetro.
        this.tipo = tipo;      // Inicializa o atributo tipo com o valor passado no parâmetro.
        this.efeito = efeito;  // Inicializa o atributo efeito com o valor passado no parâmetro.
    }

    // Método getter para retornar o nome do item.
    public String getNome() { 
        return nome;  // Retorna o nome do item.
    }
    
    // Método getter para retornar o tipo do item.
    public String getTipo() { 
        return tipo;  // Retorna o tipo do item (ex: "Consumível", "Equipamento").
    }

    // Método getter para retornar o efeito do item.
    public String getEfeito() { 
        return efeito;  // Retorna o efeito do item (ex: "cura", "dano").
    }

    // Método que permite usar o item, impactando o jogador que o usou.
    // Recebe o jogador (usuario) como parâmetro e realiza a ação do item sobre ele.
    public void usarItem(Player usuario) {
        // Verifica se o tipo do item é "Consumível". Apenas itens consumíveis têm efeito ao serem usados.
        if (tipo.equals("Consumível")) {
            // Verifica se o efeito do item é "cura".
            if (efeito.equals("cura")) {
                // Se o efeito for "cura", aumenta o HP do jogador em 30.
                usuario.setHp(usuario.getHp() + 30);
                // Exibe no console uma mensagem indicando que o jogador usou a poção de cura.
                System.out.println(usuario.getNome() + " usou uma poção de cura e restaurou 30 HP!");
            }
        }
    }
}
