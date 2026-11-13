# Anotações de Java

Notas consolidadas de estudo, organizadas seguindo os níveis da [Trilha Java](Trilha%20Java.md).

## Nível 0 — Fundamentos

### Pensamento computacional

Pensamento computacional é uma forma de analisar problemas e descrever soluções que podem ser executadas passo a passo.

| Pilar | Pergunta para aplicar |
| --- | --- |
| Decomposição | Em quais partes menores posso dividir o problema? |
| Reconhecimento de padrões | O que se repete entre os casos? |
| Abstração | Quais informações importam para tomar uma decisão? |
| Algoritmo | Qual sequência de passos resolve o problema? |

#### Formas de decompor um problema

- **Divisão funcional.** Separar o problema pelas ações necessárias, como receber, classificar e encaminhar um chamado.
- **Dividir e conquistar.** Dividir o problema em subproblemas do mesmo tipo, resolver cada um e combinar os resultados quando necessário.
- **Histórias de usuário.** Organizar necessidades em partes menores que entreguem valor a quem usa o sistema.
- **Modularização.** Organizar o sistema em módulos com responsabilidades bem definidas.

Para praticar no papel, escreva o problema em uma frase, tente separá-lo em três partes e subdivida as que ainda estiverem vagas.

#### Exemplo 1 · Triagem de chamados de suporte

**Problema.** As solicitações chegam misturadas, o que dificulta a triagem e atrasa o atendimento.

- **Decomposição.** Separar recebimento, classificação, priorização, atendimento e acompanhamento.
- **Reconhecimento de padrões.** Analisar chamados anteriores e agrupar casos recorrentes em categorias como acesso, pagamento, erro técnico e outros.
- **Abstração.** Registrar descrição, categoria, prioridade, status e responsável. Exibir no painel da equipe os status aberto, em andamento, cancelado e concluído.
- **Algoritmo.**
  1. Registrar o chamado e classificá-lo.
  2. Definir a prioridade.
  3. Oferecer uma solução pelo chatbot se houver resposta para o caso.
  4. Encaminhar para a equipe responsável se o chatbot não resolver ou se o usuário pedir atendimento humano.
  5. Atualizar o status a cada mudança até o encerramento.

**Resultado esperado.** Reduzir o tempo de resposta e facilitar o acompanhamento de cada chamado.

#### Exemplo 2 · Agendamento de entrevistas

**Problema.** Toda semana, a equipe de RH gasta tempo organizando entrevistas, enviando e-mails de confirmação e atualizando a agenda.

- **Decomposição.** Separar a escolha de horários, o envio dos convites, o registro das confirmações e a atualização da agenda.
- **Reconhecimento de padrões.** Identificar as etapas que se repetem a cada semana e os tipos de entrevista que seguem o mesmo fluxo.
- **Abstração.** Representar cada entrevista por candidato, entrevistador, horário e status da confirmação.
- **Algoritmo.**
  1. Reunir os horários disponíveis.
  2. Agendar as entrevistas e enviar os convites.
  3. Registrar as respostas e atualizar a agenda conforme as confirmações.

**Resultado esperado.** Reduzir o trabalho manual de organização e deixar a agenda atualizada.

### Entendendo JDK, JVM e JRE

**JDK** (*Java Development Kit* — Kit de Desenvolvimento Java): contém o conjunto de ferramentas para desenvolvimento/criação de um programa em Java. Exemplos: `javac`, `jar`, `javap`, `jshell`.

**JRE** (*Java Runtime Environment*): JVM + bibliotecas padrão — o mínimo pra rodar um programa Java já compilado, sem as ferramentas de dev.

**JVM** (*Java Virtual Machine*): a camada de intermediação entre a máquina local e o programa, responsável pela alta portabilidade do Java ("compile once, run anywhere"). É a única das três que não é um arquivo parado no disco — só existe como processo, enquanto um programa está rodando.

As três juntas formam a **Plataforma Java**, em camadas aninhadas: `JDK ⊃ JRE ⊃ JVM`.

> **Curiosidade prática — Minecraft**: o Minecraft Java apresenta engasgos e é mais pesado que o Minecraft Bedrock justamente por causa dessa intermediação da JVM (verificação de bytecode, interpretação inicial antes do JIT "esquentar", pausas de coleta de lixo). O Minecraft chegou a ter 3 códigos-fonte distintos (Java, Pocket, Console), o que tornou inviável manter sincronia de features entre os três. A solução foi unificar Pocket + Console (ambos em C++, sem VM) numa única base, batizada de **Bedrock** — unificação que veio com o "Better Together Update" (2017) e deu ao Bedrock o crossplay entre plataformas. A Java Edition segue à parte, restrita a onde uma JVM roda (Windows/Linux/macOS).

### Da compilação à execução

```text
Main.java → javac → Main.class (bytecode) → JVM → execução
```

1. Escreva o código-fonte em `Main.java`.
2. Execute `javac Main.java` para compilar o código e gerar `Main.class`.
3. Execute `java Main` no mesmo diretório. O comando inicia a JVM, que carrega a classe e executa o programa.

Se o programa escrever no console, a saída aparece no terminal.

### Etimologia do nome "Java"

James Gosling — conhecido como o "pai" da linguagem Java — batizou a linguagem originalmente de **Oak** (carvalho), inspirado numa árvore vista da janela do escritório. O nome teve que mudar: "Oak" já era marca registrada de outra empresa (Oak Technologies).

O novo nome, **Java**, é uma referência ao café: vem da **ilha de Java**, na Indonésia, historicamente conhecida pela produção de café — não uma homenagem literal ao hábito pessoal da equipe de tomar café durante o desenvolvimento (essa é a versão popular simplificada; a mais aceita é a referência à ilha/ao café em si). Fonte: [History of Java — Tpoint Tech](https://www.tpointtech.com/history-of-java).


*James Gosling — pai do Java* https://pt.wikipedia.org/wiki/James_Gosling
