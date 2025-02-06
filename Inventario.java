// A classe Inventario é responsável por gerenciar o inventário de itens de um jogador ou personagem no jogo.
class Inventario {
    
    // Atributo itens que é uma lista de objetos do tipo Item. Ele armazena todos os itens presentes no inventário.
    private List<Item> itens;

    // Construtor da classe Inventario. Inicializa a lista de itens.
    public Inventario() {
        itens = new ArrayList<>();  // Cria uma nova lista ArrayList para armazenar os itens.
    }

    // Método para adicionar um item ao inventário.
    public void adicionarItem(Item item) {
        itens.add(item);  // Adiciona o item passado como parâmetro à lista de itens.
        
        // Exibe no console que o item foi adicionado ao inventário, mostrando o nome do item.
        System.out.println(item.getNome() + " foi adicionado ao inventário.");
    }

    // Método que lista todos os itens no inventário.
    public void listarItens() {
        // Verifica se a lista de itens está vazia.
        if (itens.isEmpty()) {
            // Se o inventário estiver vazio, exibe uma mensagem informando.
            System.out.println("Inventário vazio.");
        } else {
            // Caso o inventário não esteja vazio, imprime uma mensagem indicando que a lista de itens será mostrada.
            System.out.println("Itens no inventário:");
            
            // Laço que percorre todos os itens da lista de inventário.
            for (Item item : itens) {
                // Para cada item, imprime o nome e o efeito desse item no inventário.
                System.out.println("- " + item.getNome() + ": " + item.getEfeito());
            }
        }
    }
}
