# Trilha de estudos em Java

**Do primeiro programa ao diagnóstico de aplicações na JVM**

| Aspecto | Definição |
| --- | --- |
| **Escopo** | Linguagem, biblioteca padrão, ferramentas e práticas de engenharia para backend |
| **Versão de referência** | Java 25 LTS |
| **Alvo atual dos exercícios** | Java 25, definido no `pom.xml` |
| **Revisão do plano** | 5 de novembro de 2026 |

Ao concluir a trilha, você terá praticado a escrita de programas Java, a modelagem de regras de negócio, a integração com arquivos, HTTP e bancos relacionais, e a investigação de problemas de concorrência e desempenho. O percurso adapta o mapa do roadmap.sh à ordem de estudo do repositório e usa a documentação oficial para definir versões e contratos das APIs. [Roadmap Java](https://roadmap.sh/java) · [Mapa completo do roadmap.sh](https://roadmap.sh/pdfs/roadmaps/java.pdf) · [Dev.java](https://dev.java/learn/)

## Sumário

1. [Como usar o plano](#como-usar-o-plano)
2. [Mapa de progressão](#mapa-de-progressão)
3. [Fundamentos](#fundamentos)
4. [Intermediário](#intermediário)
5. [Avançado](#avançado)
6. [Continuidade em backend](#continuidade-em-backend)
7. [Referências de consulta](#referências-de-consulta)

## Como usar o plano

1. **Escolha o ponto de partida.** Se está começando, siga a ordem das etapas. Se já programa em Java, tente a entrega da etapa e use o resultado para localizar lacunas no checklist.
2. **Estude em ciclos curtos.** Leia a referência do tópico, preveja o resultado de um exemplo e confirme no terminal. Em cada exercício, tente por 20 a 40 minutos antes de pedir a implementação.
3. **Registre evidências.** Marque cada item quando conseguir demonstrá-lo em código ou explicar o comportamento observado. Guarde o comando de execução, um caso de fronteira e o resultado obtido. Use os modelos locais de nota e exercício em `_templates/`.
4. **Feche a etapa pela entrega.** Execute-a a partir do terminal, verifique os casos esperados, explique uma decisão e faça uma pequena mudança de requisito. Use o critério indicado em cada etapa para decidir se pode avançar.

**Compatibilidade.** Use o JDK 25 para as leituras, os exercícios e os laboratórios. Recursos em *preview* ficam nas extensões e exigem configuração explícita. [Projeto de exercícios](../estudos-cli/README.md) · [Suporte LTS](https://www.oracle.com/europe/java/technologies/java-se-support-roadmap.html) · [Alvo de compilação no Maven](https://maven.apache.org/guides/mini/guide-java-target.html)

## Mapa de progressão

| Nível | Etapas | O que você deve conseguir demonstrar |
| --- | --- | --- |
| Fundamentos | 01 a 03 | Compilar e depurar um programa, modelar regras e justificar a escolha das estruturas de dados. |
| Intermediário | 04 a 06 | Reproduzir o build, testar contratos e integrar dados com tratamento de falhas. |
| Avançado | 07 a 09 | Controlar execução concorrente, distribuir uma aplicação e investigar um problema com medições. |

Os checklists abaixo são o percurso principal. As extensões avançadas podem ser escolhidas conforme o tipo de problema que você quer estudar. As entregas 02 a 09 podem evoluir no mesmo **catálogo de produtos sintéticos**, do modelo em memória ao diagnóstico sob carga.

## Fundamentos

### Etapa 01 · Fundamentos da linguagem

- [ ] Selecionar o JDK no terminal, distinguir JDK e JVM, compilar com `javac`, executar com `java` e experimentar expressões com JShell.
- [ ] Usar tipos primitivos e referências, variáveis locais, escopo, `final`, `var`, operadores e conversões. Prever divisão inteira, estouro e perda de precisão.
- [ ] Controlar o fluxo com condições, `switch`, laços e saídas antecipadas. Verificar limites e a condição de parada.
- [ ] Separar entrada, cálculo e saída em métodos. Explicar parâmetros, retorno e passagem por valor.
- [ ] Manipular `String` e arrays. Comparar conteúdo e identidade, tratar entrada vazia e percorrer índices válidos.
- [ ] Distinguir erro de compilação, exceção e erro de lógica. Ler a stack trace e inspecionar variáveis no depurador.

**Entrega** · Relatório de inteiros no terminal com quantidade, soma, mínimo, máximo e média. Receba os números por argumentos e use `long` para a soma.

**Critério de avanço** · Verificar entrada vazia, argumento inválido e soma acima do limite de `int`. Adicionar a contagem de negativos e verificar o tratamento do zero.

### Etapa 02 · Modelagem e contratos

- [ ] Organizar classes em pacotes e controlar acesso a campos, métodos e construtores. Diferenciar membros de instância e de classe.
- [ ] Usar construtores e métodos para garantir invariantes. Modelar ausência, identidade e estado mutável de forma explícita.
- [ ] Aplicar interfaces, polimorfismo e composição. Explicar herança, sobrescrita, sobrecarga e o momento em que a chamada é resolvida.
- [ ] Implementar os contratos de `equals` e `hashCode`. Explicar imutabilidade e quando fazer cópias defensivas.
- [ ] Escolher entre classe, `enum`, `record` e hierarquia selada. Usar *pattern matching* em `instanceof` e `switch` para tratar variantes do domínio.

**Entrega** · Modelo de catálogo com produtos, categorias e regras de preço. Represente preços em centavos inteiros até estudar aritmética decimal na etapa 05.

**Critério de avanço** · Demonstrar que as regras impedem estados inválidos e explicar qual igualdade o domínio adota. [Linguagem e orientação a objetos no Dev.java](https://dev.java/learn/language/)

### Etapa 03 · Coleções, generics e algoritmos

- [ ] Escolher entre array, `List`, `Set`, `Map` e `Deque` de acordo com ordem, duplicatas e operações dominantes. Comparar implementações usuais.
- [ ] Percorrer coleções com laços e iteradores, controlar mutabilidade e distinguir uma visão de uma cópia. Avaliar o impacto de usar chaves mutáveis.
- [ ] Ordenar com `Comparable` e `Comparator`. Implementar busca linear e explicar o pré-requisito de uma busca binária.
- [ ] Estimar o custo de tempo e espaço com Big O. Justificar a estrutura escolhida para buscar, inserir ou ordenar dados.
- [ ] Escrever tipos e métodos genéricos. Aplicar limites e wildcards, explicar invariância, *type erasure* e os riscos de *raw types*.

**Entrega** · Catálogo em memória com busca por identificador e listagem ordenada.

**Critério de avanço** · Verificar duplicatas e empates na ordenação e justificar o custo das buscas com a estrutura escolhida. [Tutoriais do Dev.java](https://dev.java/learn/)

## Intermediário

### Etapa 04 · Exceções, build e testes

- [ ] Distinguir exceções verificadas e não verificadas. Escolher onde validar, propagar ou traduzir uma falha e preservar sua causa.
- [ ] Fechar recursos com `try-with-resources`. Investigar exceções suprimidas quando o processamento e o fechamento falham.
- [ ] Usar o Maven Wrapper para compilar, testar e empacotar. Ler o POM, as fases do build e a árvore de dependências. Identificar escopos e dependências transitivas.
- [ ] Escrever testes de contrato com JUnit, incluindo casos parametrizados, fronteiras e falhas. Usar um *fake* ou Mockito quando for preciso controlar uma dependência.
- [ ] Distinguir testes unitários de testes de integração e selecionar quais verificações exigem um arquivo, servidor ou banco real.

**Entrega** · Build reproduzível do catálogo e suíte de testes para suas regras.

**Critério de avanço** · Executar a suíte pelo Wrapper e demonstrar que ela acusa uma regressão real. Use a prova **D1** do plano de backend para revisar o núcleo de Java. [Ciclo de vida do Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) · [Guia do JUnit](https://docs.junit.org/current/user-guide/)

### Etapa 05 · Java funcional e biblioteca padrão

- [ ] Criar lambdas e referências a métodos com interfaces funcionais. Compor predicados e funções simples.
- [ ] Transformar e agregar dados com Streams, incluindo `map`, `filter`, `flatMap`, redução e coletores. Explicar avaliação preguiçosa e efeitos colaterais.
- [ ] Representar ausência em resultados com `Optional`. Escolher entre valor padrão, transformação e propagação da ausência.
- [ ] Usar `java.time` para data, instante, fuso e duração. Tornar os testes sensíveis ao tempo reproduzíveis com `Clock`.
- [ ] Calcular valores decimais com `BigDecimal` e definir a política de arredondamento. Usar formatação, locale e expressões regulares quando exigidos pelo formato dos dados.

**Entrega** · Relatório do catálogo com totais por categoria e por período.

**Critério de avanço** · Verificar precisão decimal, limites de datas e categorias vazias. Explicar a transformação dos dados a partir da entrada. [API do Java 25](https://docs.oracle.com/en/java/javase/25/docs/api/index.html)

### Etapa 06 · Arquivos, HTTP e JDBC

- [ ] Ler e gravar arquivos com `Path`, `Files`, leitores e streams de bytes. Definir a codificação e processar entradas grandes sem carregar o arquivo inteiro na memória.
- [ ] Consumir HTTP com `HttpClient`. Interpretar métodos, status e cabeçalhos, configurar timeouts e distinguir falhas de transporte de respostas HTTP de erro.
- [ ] Praticar SQL com chaves, restrições, junções e transações antes de integrar a persistência. Usar `PreparedStatement` para vincular valores e mapear um `ResultSet`.
- [ ] Controlar `commit` e `rollback` e fechar conexões e demais recursos. Explicar o papel de `DataSource` e do pool de conexões.
- [ ] Testar a integração em ambiente isolado. Usar logs com SLF4J e um provedor de logging para registrar falhas com contexto e sem expor credenciais ou dados sensíveis.

**Entrega** · Importação de produtos sintéticos, persistência relacional e consulta a um serviço HTTP de teste.

**Critério de avanço** · Demonstrar que uma importação inválida reverte a transação e que timeout e resposta HTTP de erro produzem diagnósticos distintos. [JDBC](https://docs.oracle.com/en/java/javase/25/docs/api/java.sql/module-summary.html) · [Cliente HTTP](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/module-summary.html) · [Semântica HTTP](https://www.rfc-editor.org/rfc/rfc9110.html) · [SLF4J](https://www.slf4j.org/manual.html)

## Avançado

### Etapa 07 · Concorrência e modelo de memória

- [ ] Reproduzir uma condição de corrida e explicar visibilidade, atomicidade e relações *happens-before*. Distinguir `volatile`, sincronização, locks e classes atômicas.
- [ ] Compartilhar estado com segurança por meio de imutabilidade, confinamento ou coleções concorrentes. Identificar operações compostas que precisam de coordenação.
- [ ] Executar tarefas com `ExecutorService` e compor resultados com `CompletableFuture`. Lidar com interrupção, cancelamento, timeout e encerramento do executor.
- [ ] Comparar threads de plataforma e virtuais em uma carga de I/O. Limitar o acesso a uma dependência externa e medir o efeito sobre vazão e latência.
- [ ] Distinguir contenção, condição de corrida e deadlock. Verificar como falhas parciais afetam o resultado de um lote concorrente.

**Entrega** · Consulta concorrente de disponibilidade de produtos com tempo limite e limite de chamadas simultâneas.

**Critério de avanço** · Demonstrar, com um serviço de teste, que o limite é respeitado, o cancelamento é tratado e uma falha parcial é observável. [Modelo de memória](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html) · [Threads virtuais](https://docs.oracle.com/en/java/javase/25/core/virtual-threads.html)

### Etapa 08 · Empacotamento e modularidade

- [ ] Empacotar classes e recursos em JAR e distinguir classpath de module path. Investigar conflitos e dependências ausentes com `jdeps` e ferramentas do build.
- [ ] Definir módulos com `module-info.java`. Explicar `requires`, `exports` e `opens`, incluindo seu efeito sobre o acesso reflexivo.
- [ ] Usar anotações e reflexão para inspecionar tipos. Distinguir processamento durante a compilação de leitura em tempo de execução.
- [ ] Descobrir implementações com `ServiceLoader`. Identificar a fronteira entre contrato público e detalhes de implementação.
- [ ] Gerar uma imagem de execução com `jlink` para um laboratório modular e documentar o comando de execução.

**Entrega** · Distribuição do catálogo com um módulo de importação e uma implementação substituível.

**Critério de avanço** · Executar o artefato fora da IDE e explicar quais dependências e pacotes são visíveis ao consumidor. [Organização de aplicações no Dev.java](https://dev.java/learn/) · [Manual do jlink](https://docs.oracle.com/en/java/javase/25/docs/specs/man/jlink.html)

### Etapa 09 · JVM, diagnóstico e desempenho

- [ ] Inspecionar bytecode com `javap` e descrever carregamento, ligação e inicialização de classes. Explicar o papel do interpretador e do compilador JIT.
- [ ] Distinguir heap, pilhas de threads e metaspace. Investigar alocação, retenção de objetos e coleta de lixo antes de escolher parâmetros da JVM.
- [ ] Coletar evidências com `jcmd`, thread dumps, logs de GC e Java Flight Recorder (JFR). Relacionar os dados a uma hipótese sobre CPU, memória, I/O ou contenção.
- [ ] Definir uma linha de base com volume de dados, vazão, latência e memória. Comparar duas execuções controladas após mudar uma variável.
- [ ] Usar JMH quando for preciso isolar uma operação. Controlar aquecimento e repetições e reconhecer os limites de um microbenchmark.

**Entrega** · Relatório de diagnóstico de um problema reproduzível no catálogo.

**Critério de conclusão** · Entregar o roteiro de reprodução, as medições antes e depois, a hipótese testada e os limites da conclusão. [Especificação da JVM](https://docs.oracle.com/javase/specs/jvms/se25/html/index.html) · [Manual do jcmd](https://docs.oracle.com/en/java/javase/25/docs/specs/man/jcmd.html) · [JFR](https://docs.oracle.com/en/java/javase/25/docs/api/jdk.jfr/module-summary.html) · [JMH](https://github.com/openjdk/jmh)

### Extensões avançadas

| Interesse | Laboratório opcional |
| --- | --- |
| Concorrência | Propagar contexto de leitura com `ScopedValue` e investigar cancelamento de subtarefas com `StructuredTaskScope`, em *preview* no Java 25. |
| Redes e segurança | Inspecionar uma conexão TLS e estudar certificados, assinaturas e armazenamento de chaves com as APIs do JDK. |
| Interoperabilidade | Usar a Foreign Function & Memory API para chamar uma função nativa e controlar o ciclo de vida da memória com uma `Arena`. |

[ScopedValue](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/ScopedValue.html) · [StructuredTaskScope](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/StructuredTaskScope.html) · [Segurança no Dev.java](https://dev.java/learn/) · [Foreign Function & Memory API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/foreign/package-summary.html)

## Continuidade em backend

Com o núcleo da linguagem e as integrações das etapas 01 a 06, você já pode iniciar o bloco de backend. As etapas 07 a 09 aprofundam Java e podem avançar em paralelo à medida que surgirem problemas de execução, distribuição e desempenho.

1. **SQL e HTTP** · Consolidar consultas, transações e semântica HTTP na prova **D2**.
2. **Spring Core** · Praticar injeção de dependências, configuração e ciclo de vida dos beans na prova **D3**.
3. **Spring Boot e persistência** · Evoluir o catálogo para uma API com Spring MVC, validação e testes de integração. Estudar JPA, Hibernate e Spring Data JPA a partir da experiência anterior com SQL e JDBC.
4. **Operação** · Evoluir a aplicação como monólito modular. Adicionar segurança, logs e métricas antes de investigar otimizações ou avaliar a divisão em serviços.

[Spring Core](https://docs.spring.io/spring-framework/reference/core/beans.html) · [Spring Boot](https://docs.spring.io/spring-boot/reference/) · [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)

## Referências de consulta

| Fonte | Quando consultar |
| --- | --- |
| [roadmap.sh · Java](https://roadmap.sh/java) | Conferir a cobertura do ecossistema e explorar ferramentas conforme o projeto. |
| [Dev.java · Tutoriais](https://dev.java/learn/) | Aprender conceitos com exemplos e localizar guias sobre linguagem, APIs e ferramentas. |
| [Java Language Specification 25](https://docs.oracle.com/javase/specs/jls/se25/html/index.html) | Verificar regras de tipos, expressões, avaliação e concorrência. |
| [API do Java 25](https://docs.oracle.com/en/java/javase/25/docs/api/index.html) | Conferir o contrato de uma classe ou método, a versão de introdução e o status de *preview*. |
| [Mudanças da linguagem por versão](https://docs.oracle.com/en/java/javase/25/language/java-language-changes-release.html) | Identificar em qual versão cada recurso da linguagem entrou e quais recursos estão em *preview* no Java 25. |
