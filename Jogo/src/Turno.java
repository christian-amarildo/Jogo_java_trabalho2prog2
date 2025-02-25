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

//    System.out.println("Os heróis venceram a batalha! Parabéns!");
//        log.adicionarLog("Os heróis venceram a batalha! Parabéns!");

        return false;
    }

    // |--------------------BOSS---------------------|
    // PARTE QUE CUIDA DAS FUNÇÕES DA BATALHA DO CHEFE
    public void decidirturnoBatalhaBoss(List<Hero> herois, List<Monster> leviatan) {

        // Turno dos Heróis
        for (Hero heroi : herois) {
            if (heroi.getHp() > 0) {  // Verifica se herói ainda está vivo
                heroi.inteligenciaArtificial(leviatan, herois);

            }
        }
        
        // Turno do Chefe
        for(Monster monstro : leviatan) {
            if (monstro.getHp() > 0) {  // Verifica se o monstro ainda está vivo
                monstro.inteligenciaArtificial(leviatan, herois);
            }
        }

        log.exibirEventos();
    }

    public boolean bossVivo(Monster monstro) {
        if(monstro.getHp() > 0) {
            return true;
        }

        // Mensagem bonita com arte
        System.out.println("\n");
        System.out.println("**********************************************");
        System.out.println("               VITÓRIA COM SUCESSO!           ");
        System.out.println("      ****************************************");
        System.out.println("             __    __    __  __  __           ");
        System.out.println("            /  \\  /  \\  /  \\/  \\/  \\         ");
        System.out.println("           /    \\/    \\/  /  /\\/   \\ \\       ");
        System.out.println("           |      |    |  /  |  |   \\         ");
        System.out.println("           \\     /\\    | |   \\_/    |\\        ");
        System.out.println("            \\__/  \\__|    |____/|___/|        ");
        System.out.println("**********************************************");
        System.out.println("Parabéns, você derrotou o Leviatã e venceu o jogo!");
        System.out.println("**********************************************");

        log.adicionarLog("Parabéns, você derrotou o Leviatã e venceu o jogo!");

        System.exit(0);

        return false;
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

//        System.out.println("Todos os heróis foram derrotados! Fim do Jogo!");
        log.adicionarLog("Todos os heróis foram derrotados! Fim do Jogo!");

        return false;
    }
}
