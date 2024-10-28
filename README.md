# Jogo Cata-Frutas

Este projeto faz parte da disciplina **Programação Orientada a Objetos (POO)** da **Universidade Federal de Sergipe (UFS)**. O **Jogo Cata-Frutas** é um jogo competitivo de coleta de frutas, onde dois jogadores interagem em um terreno, coletam frutas e competem para acumular uma quantidade específica de frutas ouro. O projeto é desenvolvido em Java e está sendo implementado em fases.

## Integrantes
- **Davi Araújo do Nascimento**
- **Lucas Emanuel Siqueira Costa**
- **Wemerson da Silva Soares**
- **William Gabriel Yckson Araújo Braga**

## Funcionalidades

### Fase 2 - Funcionalidades Implementadas
- **Movimentação dos Jogadores**: Os jogadores podem se mover dentro de uma matriz bidimensional representando o terreno, respeitando as regras de vizinhança e obstáculos (árvores, pedras, etc.).
- **Interações Básicas com o Terreno**: Jogadores podem interagir com árvores, derrubando frutas, e mover-se por áreas de grama.
- **Mochila**: Cada jogador possui uma mochila onde as frutas coletadas são armazenadas.
- **Sistema de Rodadas**: Os turnos alternam entre os jogadores, garantindo uma jogabilidade justa.

### Fase 3 - Próximos Passos
- **Buffs e Nerfs das Frutas**: As frutas saudáveis devem fornecer vantagens (buffs) e as frutas "bichadas" penalidades (nerfs).
- **Coleta de Frutas**: Conclusão da lógica de coleta de frutas pelos jogadores.
- **Sistema de Empurrão**: Implementar a mecânica de empurrar adversários para derrubar frutas de suas mochilas.
- **Condições de Vitória**: Implementação de uma condição de vitória para determinar o jogador vencedor.

## Estrutura do Código
O código está organizado em três pacotes principais:

1. **cenário**: Contém as classes para os elementos do terreno (árvores, pedras, etc.).
2. **frutas**: Contém as classes para as frutas, cada uma com seus efeitos de buffs e nerfs.
3. **jogoCataFrutas**: Contém as classes principais, como `Jogo`, `Terreno`, `Jogador`, e `Mochila`.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação utilizada.
- **Eclipse IDE**: Ambiente de desenvolvimento integrado.
- **Draw.io**: Utilizado para o diagrama de classes.

## Como Jogar
1. Clone o repositório:
    ```bash
    git clone https://github.com/DaviAUJ/jogoCataFrutas
    ```
2. Importe o projeto em sua IDE Java preferida.
3. Compile e execute o projeto.
4. Siga as instruções no terminal para mover os jogadores, coletar frutas e competir pela vitória.

## Contribuições
Para contribuir com este projeto, faça um fork, crie um branch para suas alterações e envie um pull request.

## Licença
Este projeto não possui uma licença definida.

## Referências
- **[Java Language Specification: Switch Statement](https://docs.oracle.com/javase/specs/jls/se8/html/jls-14.html#jls-14.11)**
- **[Repositório GitHub do Jogo Cata-Frutas](https://github.com/DaviAUJ/jogoCataFrutas)**
- **[Jogo Cata Frutas - Relatório (FASE 2)](https://docs.google.com/document/d/1eM5peHkvaT7f6K6dXj9mAdj6X6pDrXhco0JL1q3umXg)**
- **[Jogo Cata Frutas - Relatório Final](https://docs.google.com/document/d/128p2hITN1uOtG7T2QvHjtFHdUIbn3T1e4R7r7hdGvnY)**

