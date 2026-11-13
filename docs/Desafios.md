
# Desafio: Automatizando tarefas 07/11/2026

Você está trabalhando como pessoa desenvolvedora júnior em uma equipe que está criando um sistema para auxiliar no controle de presença em eventos online. O time precisa de um algoritmo que processe a lista de participantes e envie um e-mail de agradecimento apenas para quem participou de toda a transmissão ao vivo.

> "Queremos automatizar o envio de agradecimentos. Mas só para quem assistiu à transmissão do início ao fim. Você consegue organizar esse processo em um algoritmo?"

Utilize os conhecimentos sobre **algoritmos** para construir uma solução em linguagem natural, com etapas claras e executáveis.

### Considere:

- Quais são os dados de entrada necessários?
- Como o algoritmo pode verificar a condição de presença completa?
- Que decisão precisa ser tomada com base nesses dados?
- Qual deve ser a ação final?
  
  
### Minha resolução
  
     Lista dos usuarios que assistiram a transmicao com horario de entrada e saida
     Comparar se o tempo de permanencia é igual ou maior ao tempo de transmicao
     Se o tempo de permancencia for igual ou maior ao tempo de transmicao, enviar e-mail.  
     Com o e-mail enviado, agradecer aos usuarios pela sua participacao
  
  
# Desafio: Calculadora de despesas 07/11/2026
  
Você está desenvolvendo um sistema simples de controle financeiro pessoal. Uma das funcionalidades é permitir que o usuário informe diversas despesas do mês (como mercado, transporte, lazer etc.), e ao final, o sistema deve apresentar o total gasto.

Sua tarefa é criar um algoritmo, em linguagem natural, que represente a seguinte lógica: o sistema deve somar automaticamente todos os valores informados pelo usuário, sem que ele precise dizer quantas despesas tem. O processo continua até que o valor digitado seja zero. Nesse momento, a soma total deve ser exibida.  
  
### Minha resolução  
  
     Enquanto o valor digitado for diferente de 0  
        Total = Total + Valor informado  
     Sua despesas total é Total.


# Desafio: Classificando desempenho acadêmico 07/11/2026

Você está trabalhando no desenvolvimento de um sistema educacional que precisa exibir mensagens personalizadas para estudantes com base em sua média final. A regra de negócio definida pela equipe pedagógica é a seguinte:

- Média menor que 5,0: mensagem “Você está reprovado.”
- Média entre 5,0 e 6,9: mensagem “Você está de recuperação.”
- Média 7,0 ou mais: mensagem “Parabéns! Você foi aprovado.”

Sua tarefa é criar um algoritmo em linguagem natural que represente esse processo de verificação e decisão de forma clara, usando estruturas condicionais.

### Minha resolução  
    nota = 7  
    se nota >= 7  
        “Parabéns! Você foi aprovado.”  
    se nota >= 5 e nota < 7  
        “Você está de recuperação.”  
    se nota < 5
        “Você está reprovado.”


# Desafio: Calculando pedidos 07/11/2026

Você está desenvolvendo uma funcionalidade para um aplicativo de lanchonete. O sistema precisa calcular o valor total de um pedido com base na quantidade de itens solicitados.

O cliente pode pedir:

- Hambúrguer: R$ 12,00
- Batata frita: R$ 7,00
- Refrigerante: R$ 5,00

Sua tarefa é criar um algoritmo em linguagem natural ou fluxograma que armazene os dados necessários em variáveis e faça o cálculo do valor total com base nas quantidades informadas.

### Minha resolução  


# Desafio: Verificação de desconto 07/11/2026

Você está desenvolvendo o sistema de bilheteria para um cinema. Os clientes podem ter direito a meia-entrada em duas situações:

>Se tiverem menos de 18 anos
ou
Se forem estudantes
>
Sua tarefa é criar um algoritmo em linguagem natural ou gráfica (usando fluxogramas, por exemplo) que avalie as informações do cliente e exiba uma mensagem indicando se ele tem ou não direito ao desconto.

### Minha resolução  


# Desafio: Calculando valor da entrega 07/11/2026

Você está desenvolvendo um sistema para uma empresa de delivery. O valor da taxa de entrega depende da distância até o cliente e se o pedido foi feito em um dia de chuva.

As regras são:

- Para entregas até 5 km, a taxa é R$ 5,00.
- Entre 5 e 10 km, a taxa é R$ 8,00.
- Acima de 10 km, a taxa é R$ 10,00.
- Se estiver chovendo, acrescenta R$ 2,00 à taxa padrão.

O desafio desta atividade é criar um algoritmo em linguagem natural que informe o valor final da entrega.

### Minha resolução  
  
  
# Desafio: conversor de moedas

Você está desenvolvendo um sistema de apoio para uma agência de viagens. Uma das funcionalidades mais solicitadas é um conversor de moedas. O usuário informa um valor em reais (R$) e o sistema precisa mostrar quanto isso representa em dólares (US$), usando uma taxa de câmbio definida pela empresa.

Sua tarefa é criar um algoritmo em linguagem natural que use uma função para fazer essa conversão. A função deve receber o valor em reais e a taxa de câmbio como entrada, e retornar o valor convertido.
  
### Minha resolução  
  
  
# Desafio: Simulador de pedidos em uma cafeteria

Você foi contratado por uma cafeteria que deseja automatizar o atendimento no balcão. O sistema deve permitir que o atendente registre os pedidos de cada cliente, calcule o valor total e aplique um desconto de 10% para clientes cadastrados.

O processo deve funcionar da seguinte forma:

- O atendente informa quantos itens o cliente vai pedir.
- Para cada item, o sistema solicita o nome e o preço.
- Ao final, o sistema pergunta se o cliente é cadastrado.
- Se for, aplica o desconto e exibe o valor com desconto.
- Caso contrário, exibe o valor cheio.
  
  
O desafio consiste em criar um algoritmo que represente essa lógica de forma completa.
