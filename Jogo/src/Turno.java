// SEPARAR A FUNÇÃO "JOGAR_TURNO()" PARA DUNGEON E LEVIATÃ
// IMPLEMENTAR IA DENTRO DA "JOGAR_TURNO()" DA DUNGEON
// JOGAR_TURNO_LEVIATAN: RECEBE 3 PARÂMETROS: "Player player, Player opponent, ResultadoAtaque ra"
// JOGAR_TURNO_DUNGEON: RECEBE 2 PARÂMETROS: "Player player, Player opponent"

// PROVÁVEL: IMPLEMENTAR ESSA FUNÇÃO DENTRO DESSA CLASSE
// exibirStatus(); --> EXIBE OS STATUS DOS PARTICIPANTES A CADA RODADA

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
    public void jogarturnoBatalhaDungeon(List<Hero> herois, List<Monster> monstros) {
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
    public void jogarTurnoBatalhaBoss(Player player, Player opponent, ResultadoAtaque ra) {
        if(player instanceof Hero) {
            // Turno do jogador
            jogarTurnoHeroiBatalhaBoss(player, opponent, ra);

            // Verifica se o monstro foi derrotado após o ataque do herói
            verificaDerrotaDoBoss(player, opponent);            
        } else if(player instanceof Monster) {
            // Turno do monstro
            jogarTurnoBoss(player, opponent, ra);

            // Verifica se o herói foi derrotado após o ataque do monstro
            verificaDerrotaDoHeroi(player, opponent);
        }

        // Exibe todos os logs da batalha
        log.exibirEventos();
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
