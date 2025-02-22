import Enums.ResultadoAtaque;
import entities.Log;
import entities.Player;
import entities.heros.Hero;
import entities.monsters.Monster;
import utils.Cores;

import java.util.List;
import java.util.Random;

public class Turno {
    private Log log = new Log();

    // |--------------------DUNGEON----------------------|
    // PARTE DAS FUNÇÕES QUE CUIDA DAS BATALHAS DA DUNGEON
    public void decidirTurnoBatalhaDungeon(List<Hero> herois, List<Monster> monstros) {
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {  // Verifica se herói ainda está vivo
                heroi.inteligenciaArtificial(monstros, herois);
            }
        }
        
        for(Monster monstro : monstros) {
            if (monstro.getHp() > 0) {  // Verifica se herói ainda está vivo
                monstro.inteligenciaArtificial(monstros, herois);
            }
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
    }

    public boolean monstrosVivos(List<Monster> monstros) {
        for (Monster monstro : monstros) {
            if (monstro.getHp() > 0) {
                return true;
            }
        }

        System.out.println("Os heróis venceram a batalha! Parabéns!");
        log.adicionarLog("Os heróis venceram a batalha! Parabéns!");

        return false;
    }

    // |--------------------BOSS---------------------|
    // PARTE QUE CUIDA DAS FUNÇÕES DA BATALHA DO CHEFE
    public void decidirturnoBatalhaBoss(List<Hero> herois, Monster leviatan) {
        // Turno dos Heróis
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {  // Verifica se herói ainda está vivo
                ResultadoAtaque resultadoLeviatan = leviatan.realizarAtaque(heroi);
                jogarTurnoHeroiBatalhaBoss(heroi, leviatan, resultadoLeviatan);
                verificaDerrotaDoBoss(heroi, leviatan);
                log.exibirEventos();
            }
        }
        
        // Turno do Chefe
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {  // Verifica se herói ainda está vivo
                ResultadoAtaque resultadoHeroi = heroi.realizarAtaque(leviatan);
                jogarTurnoBoss(leviatan, heroi, resultadoHeroi);
                verificaDerrotaDoHeroi(leviatan, heroi);
                log.exibirEventos();
            }
        }
    }

    
    public void verificaDerrotaDoBoss(Player player, Player opponent) {
        if (opponent.getHp() <= 0) {
            System.out.println("O monstro " + opponent.getNome() + " foi derrotado pelo herói " + player.getNome() + "!\n");
            log.adicionarLog("O monstro " + opponent.getNome() + " foi derrotado pelo herói " + player.getNome() + "!\n");
        }
    }

    public void jogarTurnoBoss(Player monstro, Player heroi, ResultadoAtaque ra) {
        // A lógica do turno do monstro
        System.out.println("\nTurno do monstro: " + monstro.getNome());

        // Realiza a ação do monstro
        log.adicionarLog("Resultado do ataque do monstro: " + ra);

        if(ra != ResultadoAtaque.ERROU) {
            System.out.println("O herói " + heroi.getNome() + " recebeu dano do monstro " + monstro.getNome() + "\n");
            log.adicionarLog("O herói " + heroi.getNome() + " recebeu dano do monstro " + monstro.getNome());
        }
    }

    public boolean bossVivo(Monster monstro) {
        if(monstro.getHp() > 0) {
            return true;
        }

        System.out.println("Parabéns, você derrotou o leviatã e venceu o jogo!");
        log.adicionarLog("Parabéns, você derrotou o leviatã e venceu o jogo!");

        return false;
    }

    public void jogarTurnoHeroiBatalhaBoss(Player heroi, Player monstro, ResultadoAtaque ra) {
        // A lógica do turno do herói
        System.out.println("\nTurno do herói: " + heroi.getNome());

        // Realiza o ataque normal
        log.adicionarLog("Resultado do ataque de " + heroi.getNome() + ": " + ra);

        if(ra != ResultadoAtaque.ERROU) {
            System.out.println("O monstro " + monstro.getNome() + " recebeu dano do herói " + heroi.getNome() + "\n");
            log.adicionarLog("O monstro " + monstro.getNome() + " recebeu dano do herói " + heroi.getNome());
        }
    }

    // FUNÇÕES DE HERÓI UNIVERSAIS
    public void verificaDerrotaDoHeroi(Player player, Player opponent) {
        if (opponent.getHp() <= 0) {
            System.out.println("O herói " + opponent.getNome() + " foi derrotado pelo monstro " + player.getNome() + "!\n");
            log.adicionarLog("O herói " + opponent.getNome() + " foi derrotado pelo monstro " + player.getNome() + "!");
        }
    }

    public boolean heroisVivos(List<Hero> herois, boolean batalhaBoss) {
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {
                return true;
            }
        }

        if(batalhaBoss) {
            System.out.println("O leviatã derrotou todos os heróis! Fim do Jogo!");
            log.adicionarLog("O leviatã derrotou todos os heróis! Fim do Jogo!");

            return false;
        }

        System.out.println("Todos os heróis foram derrotados! Fim do Jogo!");
        log.adicionarLog("Todos os heróis foram derrotados! Fim do Jogo!");

        return false;
    }
}
