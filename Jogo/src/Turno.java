import Enums.ResultadoAtaque;
import entities.Log;
import entities.Player;
import entities.heros.Hero;
import entities.monsters.Monster;

import java.util.List;
import java.util.Random;

public class Turno {
    private Log log = new Log();

    public void jogarTurno(Player player, Player opponent, ResultadoAtaque ra) {
        if(player instanceof Hero) {
            // Turno do jogador
            jogarTurnoHeroi(player, opponent, ra);

            // Verifica se o monstro foi derrotado após o ataque do herói
            verificaDerrotaDoMonstro(player, opponent);            
        } else if(player instanceof Monster) {
            // Turno do monstro
            jogarTurnoMonstro(player, opponent, ra);

            // Verifica se o herói foi derrotado após o ataque do monstro
            verificaDerrotaDoHeroi(player, opponent);
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
    }

    //
    public void jogarTurnoHeroi(Player heroi, Player monstro, ResultadoAtaque ra) {
        // A lógica do turno do herói
        System.out.println("\nTurno do herói: " + heroi.getNome());

        // Realiza o ataque normal
        log.adicionarLog("Resultado do ataque de " + heroi.getNome() + ": " + ra);

        if(ra != ResultadoAtaque.ERROU) {
            System.out.println("O monstro " + monstro.getNome() + " recebeu dano do herói " + heroi.getNome() + "\n");
            log.adicionarLog("O monstro " + monstro.getNome() + " recebeu dano do herói " + heroi.getNome());
        }
    }

    public void verificaDerrotaDoMonstro(Player player, Player opponent) {
        if (opponent.getHp() <= 0) {
            System.out.println("O monstro " + opponent.getNome() + " foi derrotado pelo herói " + player.getNome() + "!\n");
            log.adicionarLog("O monstro " + opponent.getNome() + " foi derrotado pelo herói " + player.getNome() + "!\n");
        }
    }

    //
    public void jogarTurnoMonstro(Player monstro, Player heroi, ResultadoAtaque ra) {
        // A lógica do turno do monstro
        System.out.println("\nTurno do monstro: " + monstro.getNome());

        // Realiza a ação do monstro
        log.adicionarLog("Resultado do ataque do monstro: " + ra);

        if(ra != ResultadoAtaque.ERROU) {
            System.out.println("O herói " + heroi.getNome() + " recebeu dano do monstro " + monstro.getNome() + "\n");
            log.adicionarLog("O herói " + heroi.getNome() + " recebeu dano do monstro " + monstro.getNome());
        }
    }

    public void verificaDerrotaDoHeroi(Player player, Player opponent) {
        if (opponent.getHp() <= 0) {
            System.out.println("O herói " + opponent.getNome() + " foi derrotado pelo monstro " + player.getNome() + "!\n");
            log.adicionarLog("O herói " + opponent.getNome() + " foi derrotado pelo monstro " + player.getNome() + "!");
        }
    }

    // Verifica se equipe ou boss está vivo
    public boolean heroisVivos(List<Hero> herois) {
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                return true;
            }
        }

        System.out.println("O leviatã derrotou todos os heróis! Fim do Jogo!");
        log.adicionarLog("O leviatã derrotou todos os heróis! Fim do Jogo!");

        return false;
    }

    public boolean bossVivo(Monster monstro) {
        if(monstro.getHp() > 0) {
            return true;
        }

        System.out.println("Parabéns, você derrotou o leviatã e venceu o jogo!");
        log.adicionarLog("Parabéns, você derrotou o leviatã e venceu o jogo!");

        return false;
    }
}
