import java.util.ArrayList;
import java.util.List;

public class Item {
    private String nome;
    private String tipo;
    private String efeito;

    public Item(String nome, String tipo, String efeito) {
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getEfeito() { return efeito; }

    public void usarItem(Player usuario) {
        if (tipo.equals("Consumível")) {
            if (efeito.equals("cura")) {
                usuario.setHp(usuario.getHp() + 30);
                System.out.println(usuario.getNome() + " usou uma poção de cura e restaurou 30 HP!");
            }
        }
    }
}

