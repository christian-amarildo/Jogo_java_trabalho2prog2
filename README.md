# trabalho_jogo_java

# 🐉 DungeonQuest: Text-Based RPG Adventure

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=openjdk&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-brightgreen)
![Version](https://img.shields.io/badge/Version-1.0.0-orange)

**Um RPG tático em terminal com sistema de classes, combate estratégico e progressão de dificuldade**

---

## 🌟 Recursos Principais
- **4 Classes Únicas**: Guerreiro, Mago, Arqueiro e Furtivo com habilidades especiais
- **Sistema de Combate Dinâmico**: Críticos, esquivas, defesa e cálculo de dano contextual
- **IA Adaptativa**: Monstros com comportamentos distintos baseados na dificuldade
- **Economia Complexa**: Loja com poções, upgrades e moeda compartilhada
- **Chefes Desafiadores**: Inimigos únicos como o Leviatã com mecânicas especiais
- **Sistema de Logs**: Registro detalhado de todas as ações em batalha
- **Customização ANSI**: Cores e formatação para melhor experiência no terminal

---

## 🐄 Estrutura do Projeto

```plaintext
DungeonQuest/
│
├── src/                   # Código-fonte principal
│   ├── entities/         # Entidades do jogo
│   │   ├── heros/       # Heróis jogáveis (Classes e Habilidades)
│   │   ├── monsters/    # Inimigos e chefes
│   │   ├── Log.java     # Sistema de registro de eventos
│   │   └── Player.java  # Classe base para personagens
│   │
│   ├── Enums/            # Enumeradores
│   │   └── ResultadoAtaque.java  # Estados de ataque
│   │
│   ├── utils/            # Utilitários
│   │   ├── Cores.java    # Códigos ANSI para cores
│   │   └── Turno.java    # Lógica de turnos
│   │
│   ├── Game.java         # Classe principal (Controle do jogo)
│   └── InterfaceUsuario.java # Sistema de interação
│
├── docs/                 # Documentação técnica
│   ├── Design.md        # Arquitetura do sistema
│   ├── CombatRules.pdf  # Regras detalhadas de combate
│   └── UML_Diagram.pdf  # Diagrama de classes
│
├── assets/              # Recursos visuais
│   ├── ascii_art/      # Arte textual para cenários
│   └── icons/          # Ícones para menus
│
├── lib/                 # Dependências externas
├── LICENSE              # Licença MIT
└── README.md            # Este arquivo
```

## 🚀 Começando

### 🔧 Pré-requisitos
- Java JDK 17+ [(Download)](https://jdk.java.net/)
- Terminal moderno (Suporte a cores ANSI)
- 2GB RAM disponível
- Git (Opcional para instalação)

### 🛠️ Instalação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/DungeonQuest.git

# Acesse o diretório
cd DungeonQuest

# Compile o projeto (Windows/Linux/macOS)
javac src/*.java -d out/

# Execute o jogo
java -cp out/ Game
```

## 🎮 Como Jogar

### Fluxo Principal

#### Escolha de Dificuldade

```plaintext
[1] Fácil   - Monstros mais fracos (Recomendado para iniciantes)
[2] Médio   - Balanceado (Bom para jogadores experientes)
[3] Difícil - Inimigos com bônus de 50% (Desafio extremo)
```

### Gerenciamento de Grupo
- Verifique atributos dos heróis
- Compre itens na loja
- Gerencie equipamentos

### Combate Estratégico

```plaintext
Turno Herói:
[1] Atacar       - Dano básico (Força - Defesa inimiga)
[2] Habilidade   - Usar habilidade especial (Custo: MP)
[3] Item         - Usar poção/consumível
[4] Defender     - Reduz dano recebido no próximo turno
```

### Progressão
- Derrote monstros para ganhar ouro
- Enfrente o Leviatã quando estiver preparado
- Use o ouro para upgrades permanentes

---

## ⚔ Sistema de Combate

### Mecânicas Avançadas

#### Cálculo de Dano

```java
// Exemplo de cálculo crítico
int danoCritico = (forcaAtaque * 2) - defesaInimiga;
```

#### Tabela de Chances

| Dificuldade | Chance Crítico | Defesa Inimiga |
|-------------|--------------|---------------|
| Fácil       | 10%          | +0%           |
| Médio       | 15%          | +20%          |
| Difícil     | 20%          | +50%          |

#### Efeitos de Status

```plaintext
[Queimadura]   - 5% HP/turno por 3 turnos
[Sangramento]  - 3% HP/turno acumulativo
[Buff Ataque]  +30% dano físico
```

---

## 📚 Documentação Técnica

### Diagrama de Classes do Jogo

```mermaid
classDiagram
    class Game {
        - herois: ArrayList~Hero~
        - monstros: ArrayList~Monster~
        - log: Log
        - turno: Turno
        - ui: InterfaceUsuario
        - dificuldade: Dificuldade
        - inventario: Inventario
        + escolherDificuldade() : void
        + gerarHerois() : void
        + inicializarMonstros() : void
        + iniciarCombate() : void
        - removerHeroisMortos() : void
        - removerMonstrosMortos() : void
        - todosOsMonstrosForamDerrotados() : boolean
        - todosOsHeroisForamDerrotados() : boolean
        - exibirStatus() : void
        + interagir() : void
        + enfrentarLeviata() : void
        + irDungeon() : void
        + regenerarVidaMonstros() : void
        + irLoja() : void
        + main( args : String[] ) : void
    }

    class InterfaceUsuario {
        - scanner: Scanner
        + mostrarStatusJogadores(jogadores : List~Player~) : void
        + mostrarLogs(log : Log) : void
        + exibirResultado(resultado : String) : void
        + obterEscolha() : int
    }

    class Inventario {
        - itens: ArrayList~Item~
        + adicionarItem(item : Item) : void
        + listarItens() : void
    }

    class Turno {
        - jogador: Hero
        - monstro: Monstro
        - log: Log
        + iniciarTurno() : void
        + jogarTurnoHeroi(heroi : Hero) : void
        + jogarTurnoMonstro(monstro : Monster) : void
    }

    class Habilidade {
        - nome: String
        - tipo: String
        - custo: int
        - efeito: String
        + executarHabilidade(usuario : Player, alvo : Player) : void
    }

    class Log {
        - logs: ArrayList~String~
        + adicionarLog(log : String) : void
        + exibirEventos() : void
    }

    class Player {
        # nome: String
        # hp: int
        # forcaAtaque: int
        # defesa: int
        # destreza: int
        # velocidade: int
        # log: Log
        + realizarAtaque(alvo : Player) : ResultadoAtaque
        + receberDano(dano : int) : void
        + inteligenciaArtificial(inimigos: List~Monster~, aliados : List~Hero~) : void
        - getMaiorVelocidade(players : List~Monster~) : int
    }

    class Guerreiro {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class Hero {
        - classe: String
        - habilidadeEspecial: Habilidade
        - dinheiro: int
        - emBatalha : boolean = false
        + curarTodosOsHerois(herois : List~Hero~, quantidade: int) : void
        + aumentarForcaAtaqueTodosOsHerois(herois : List~Hero~, aumento : int) : void
        + aumentarDefesaTodosOsHerois(herois : List~Hero~, aumento : int) : void
        + aumentarForcaAtaque(aumento : int) : void
        + aumentarDefesa(aumento : int) : void
        + curar(quantidade : int) : void
        + verificarHPEmBatalha() : void
    }

    class Item {
        - nome: String
        - tipo: String
        - efeito: String
        - bonusAtaque: int
        - bonusDefesa: int
        - dinheiro : int
        + adicionarDinheiro(valor : int) : void
    }

    class Monster {
        - tipo: String
        - dificuldade: int
        - dinheiroDropado: int
        + realizarAtaque(alvo : Player) : ResultadoAtaque
        + dropDinheiro() : void
    }

    class Slime {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class ResultadoAtaque {
        <<enumeration>>
        ERROU
        ACERTOU
        CRITICAL_HIT
    }

    class Dificuldade {
        - nome: String
        - fatorDano: float
        - fatorResistencia: float
        - numeroMonstros: int
        - nivelDificuldade: int
        + ajustarParametros(monstros : List~Monster~) : void
    }

    class Arqueiro {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class Furtivo {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class Mago {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class Esqueleto {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class Leviatan {
        + realizarAtaque(alvo : Player) : ResultadoAtaque
    }

    class utils.Cores {
        + RESET : String
        + VERDE : String
        + VERMELHO : String
        + AZUL : String
        + AMARELO : String
        + MAGENTA : String
        + CYAN : String
    }

    Game --> InterfaceUsuario : 1
    Game --> Inventario : 1
    Game --> Turno : 1
    Game --> Dificuldade : 1
    Game --> Log : 1
    Game --> Hero : n
    Game --> Monster : n
    InterfaceUsuario --> Log : 1
    Inventario --> Item : n
    Turno --> Hero : 1
    Turno --> Monster : 1
    Turno --> Log : 1
    Habilidade --> Player : 1
    Player --> Log : 1
    Hero --> Player : Extends
    Monster --> Player : Extends
    Guerreiro --> Hero : Extends
    Arqueiro --> Hero : Extends
    Furtivo --> Hero : Extends
    Mago --> Hero : Extends
    Slime --> Monster : Extends
    Esqueleto --> Monster : Extends
    Leviatan --> Monster : Extends
    ResultadoAtaque --> Player : 1
    ResultadoAtaque --> Monster : 1
    utils.Cores --> Game : Usa
```

---

## 🎓 Licença
Distribuído sob licença MIT. Veja [LICENSE](LICENSE) para detalhes.

Desenvolvido com ☕ por [Seu Nome]
