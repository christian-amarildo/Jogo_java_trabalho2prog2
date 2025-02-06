// A classe Log é responsável por armazenar e exibir eventos registrados durante o combate ou qualquer outra parte do jogo.
public class Log {
    
    // Atributo eventos, que é uma lista de Strings. Ele vai armazenar as mensagens (eventos) registradas.
    private List<String> eventos;

    // Construtor da classe Log. Inicializa a lista de eventos como um ArrayList.
    public Log() {
        eventos = new ArrayList<>();  // Cria uma nova instância de ArrayList para armazenar os eventos.
    }

    // Método para adicionar um evento à lista de eventos.
    // O parâmetro evento é uma String que descreve o evento a ser registrado.
    public void adicionarEvento(String evento) {
        eventos.add(evento);  // Adiciona o evento passado como argumento à lista de eventos.
    }

    // Método para exibir todos os eventos registrados até o momento.
    public void exibirEventos() {
        // Verifica se a lista de eventos está vazia.
        if (eventos.isEmpty()) {
            // Se a lista estiver vazia, imprime uma mensagem informando que não há eventos registrados.
            System.out.println("Sem eventos registrados.");
        } else {
            // Caso a lista de eventos tenha itens, imprime um título para a lista de eventos.
            System.out.println("Eventos do combate:");
            // Usando forEach para iterar sobre cada evento e imprimir no console.
            // O método println é chamado para cada evento da lista.
            eventos.forEach(System.out::println);
        }
    }
}
