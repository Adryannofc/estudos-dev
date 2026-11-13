# estudos-cli: design

Status: **proposta, aguardando aprovação**. Nada foi construído no repositório. O teste descartável do TamboUI ficou fora dele.

## Intenção

Um sistema de terminal que **exibe** o que estudo, não só guarda. Quem abrir vê uma interface de tela cheia, com abas e animações, e pode executar os exercícios dentro dela. Cada funcionalidade é uma classe, e as funcionalidades convivem no mesmo programa.

## Objetivos

- Interface de terminal de verdade, no estilo `btop`/`lazygit`: só texto, molduras com caracteres de caixa, cores da paleta ANSI, 100% teclado.
- Abas navegáveis. Para começar: **Início** (boas-vindas com texto digitado e animação), **Exercícios** (lista agrupada por tópico, com execução) e **Sobre**.
- Animações no estilo terminal: spinner, barra de progresso, texto digitado letra a letra.
- Cada exercício é uma classe pequena, independente das demais.
- Servir de vitrine: README com captura da tela e instruções de execução em uma linha.

## Fora do escopo

- Mouse, janelas flutuantes, temas fora da paleta ANSI.
- Carregamento dinâmico de plugins. Começa com registro manual.
- Portar todos os exercícios antigos de uma vez.

## O que seria um fracasso, mesmo funcionando

- Parecer aplicativo gráfico, e não terminal.
- Um exercício com erro derrubar o programa inteiro.
- O projeto virar o assunto principal e atrasar o plano de backend (Spring, JDBC etc.). A interface é meio, não fim.

## Restrições

- Java 25 LTS, Maven com `mvnw` versionado, projeto em `Java/estudos-cli` (decisão do autor: a vitrine fica no topo; `Java/projetos/` guarda projetos Maven independentes, como `exercicios-junit`).
- Repositório público: sem credenciais nem dados reais.
- **TamboUI fixado em `0.7.0`**. O autor avisa que a API ainda é instável, e a documentação online descreve a `0.8.0-SNAPSHOT`, então ela não vale para a 0.7.0. Consulte a API real do jar.
- Idioma: português nas notas, nos commits e na interface; identificadores de código em inglês (convenção do `AGENTS.md`).

## Abordagens consideradas

| | Abordagem | Custo | Resultado |
|---|---|---|---|
| A | Lanterna | Janelas montadas à mão | Estável, visual de "janelas", mais datado |
| **B** | **TamboUI (escolhida)** | API nova e instável | Visual de terminal moderno, abas e tique de animação prontos |
| C | Picocli + JLine + ANSI à mão | Você escreve o motor de redesenho | Ensina terminal a fundo, mas gasta o tempo do plano de backend |

Escolhida a B, com o núcleo separado da interface para poder trocar a biblioteca sem reescrever os exercícios.

## Teste descartável (já feito)

Feito em pasta temporária, fora do repositório, com TamboUI `0.7.0` (já no Maven Central, sem precisar de snapshot).

**Confirmado:**
- Compila e roda no JDK 25.
- `Tabs` + `TabsState`, `Block` (bordas arredondadas), `Paragraph`, `Gauge`, `Layout` e `TickEvent` existem e funcionam.
- Animação por `TuiConfig.builder().tickRate(...)` e redesenho ao devolver `true` no tique.
- Dá para testar a tela **sem terminal**: `Buffer.empty(...)` + `Frame.forTesting(...)` e ler as células. Isso permite testes automáticos da interface.

**Confirmado num terminal real:** no painel Terminal do app (PowerShell 7) a tela completa desenha certa, com abas, molduras e a mensagem de boas-vindas.

**Falha confirmada no terminal do IntelliJ (JediTerm):** a tela fica em branco. A sonda mostrou `TERM=dumb`, `TERMINAL_EMULATOR=JetBrains-JediTerm` e o JLine devolvendo um `DumbTerminal` de tamanho 0x0, então o TamboUI não tem área para desenhar. O código está certo; o terminal embutido do IntelliJ não entrega um terminal de verdade ao Java. A tela cheia deve ser executada num terminal real (Windows Terminal, PowerShell fora do IDE ou o painel do app).

**Ainda não verificado:**
- Teclas (`←/→`, `q`) e modo bruto, observados na prática.
- Aparência real das cores e do spinner.
- Entrada de texto dentro da tela (`TextInput`) e o que fazer com exercícios que leem do teclado.
- Se a opção "Emulate terminal" da configuração de execução do IntelliJ resolve para quem roda pela IDE.

**Armadilha de ambiente:** `JAVA_HOME` está no JDK 21 e o `java` do PATH é o 25. O `mvnw` usa o `JAVA_HOME`, então `release 25` falha ("release version 25 not supported"). Isso vale também para `exercicios-junit`. Aponte `JAVA_HOME` para o JDK 25 antes de construir.

## Requisitos de execução

- **Terminal real.** Ao iniciar, o programa detecta terminal "dumb" ou de tamanho 0x0 (caso do terminal do IntelliJ) e **imprime uma mensagem clara e sai**, em vez de mostrar uma tela em branco. Isso faz parte do design, porque a vitrine não pode falhar em silêncio.
- **`--enable-native-access=ALL-UNNAMED`.** No Java 25 o JLine emite um aviso de método restrito que suja a tela cheia. O comando de execução do projeto (README e configuração do Maven) deve passar essa opção.
- **`JAVA_HOME` no JDK 25** para o `mvnw` (ver a armadilha acima).

## Desenho

Um módulo Maven, dois pacotes. A regra é que o núcleo **não importa** `dev.tamboui`.

```
estudos-cli/
└── src/main/java/dev/estudos/cli/
    ├── nucleo/      # Funcionalidade, Registro, Console (entrada/saída abstrata)
    ├── exercicios/  # uma classe por exercício, agrupadas por tópico
    └── ui/          # tudo que usa TamboUI: abas, animações, execução
```

- **`Funcionalidade`**: contrato mínimo, `nome()`, `topico()` e `executar(Console)`.
- **`Console`**: abstrai entrada e saída (`escrever(String)`, `perguntar(String)`). Os exercícios antigos usam `Scanner` e `System.out` direto, o que não funciona numa tela cheia. A interface implementa o `Console` num painel com campo de texto.
- **`Registro`**: lista montada à mão. `ServiceLoader` fica para depois, se valer a pena.
- **Execução em segundo plano**: o exercício roda fora da thread de desenho, e `perguntar` bloqueia esperando o texto digitado na interface. Qualquer exceção do exercício é capturada e mostrada num painel, sem derrubar o programa.

## Decisões minhas (revise)

- Nome do projeto: `estudos-cli`.
- Registro manual em vez de descoberta automática.
- Os exercícios de arquivo único ficam como estão; os novos já nascem como classes do CLI.
- Documento em `Java/docs/`, que já tem as notas do módulo, em vez de criar `docs/superpowers/`.
- Testes de interface sem terminal, com `Frame.forTesting`; JUnit nos exercícios.

## Em aberto, fica para o construtor

- Como o `perguntar` bloqueante conversa com a thread de desenho do TamboUI (`runLater`, `runOnRenderThread`).
- Como agrupar e navegar a lista na aba Exercícios (por tópico, com busca?).
- Conteúdo da aba Sobre.
- Se a captura de tela do README será imagem ou GIF.

## Como trabalhar

Pelo `AGENTS.md`, quem escreve o código é você, e eu dou pistas e reviso o design e os erros. Eu só entrego solução completa se você pedir. Sugestão de primeiro passo: um projeto Maven vazio com o `Spike` copiado, até aparecer a tela no seu terminal.
