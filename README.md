﻿# Controle de Transações de Crédito em Conta com Cartão

## Visão Geral

Este projeto implementa um sistema de controle de transações de crédito em conta com cartão. 
Ele permite que cada portador de cartão (cliente) tenha uma conta com seus próprios dados e realize operações que geram transações associadas à sua conta. 
Cada transação possui um tipo (compra à vista, compra parcelada, saque ou pagamento), um valor e uma data de criação.

## Tecnologias

- Spring Boot 2.7: Framework Java para desenvolvimento de aplicativos web.
- MySQL: Sistema de gerenciamento de banco de dados relacional.
- Java 1.8: Linguagem de programação.

## Configuração

### Banco de Dados MySQL

Certifique-se de ter um servidor MySQL em execução e configure as seguintes informações no arquivo `application.properties` do Spring Boot:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/avpactomais
spring.datasource.username=root
spring.datasource.password=
```

Inclui um arquivo (data.sql) para iniciar os dados da operação.

## Fases do projeto
### Fase 1
Esta é primeira parte do desafio servindo de preparação para a segunda que ocorrerá no dia e
horário marcados e vamos desenvolver juntos (um dev da PACTO e você) uma solução prédefinida, que deve demorar no máximo 1h.
Para este momento, você precisa desenvolver o caso apresentado que deve estar pronto para o
dia e horário marcado da segunda parte.

#### DESCRIÇÃO DO CASO: Rotinas de transação de crédito
Você irá implementar um controle de transação de credito em conta com cartão obedecendo as
seguintes regras:

1. Cada portador de cartão (cliente) possui uma conta com seus dados;
2. A cada operação realizada pelo cliente uma transação é criada e associada à sua respectiva
conta;
3. Cada transação possui um tipo (compra a vista, compra parcelada, saque ou pagamento), um
valor e uma data de criação;
4. Transações de tipo compra e saque são registradas com valor negativo, enquanto transações
de pagamento são registradas com valor positivo.

Na tabela de Transacao, a coluna valor guarda o valor da transação e a coluna DataTransacao
guarda o momento em que ocorreu a transação.

#### ENDPOINTS
Desenvolva os endpoints abaixo considerando as regras de negócio mencionadas
anteriormente:
POST /conta (criação de uma conta)
Request Body:
{
"numero_conta": "12345678900"
}
GET /conta/:contaId (consulta de informações de uma conta)
Response Body:
{
"conta_id": 1,
"numero_documento": "12345678900"
}
POST /transacao (criação de uma transação)
Request Body:
{
"conta_id": 1,
"tipo_operacao_id": 4,
"valor": 123.45
}

#### Critérios de avaliação:
1. aplicação dos conceitos de orientação;
2. objetividade na lógica de programação;
3. clareza na definição de atributos e métodos;
Retorno: Posta resultado no github e enviar o link do repositório para nossa avaliação.

### Fase 2
Já temos a estrutura de transações criada, mas o time de produtos nos trouxe uma nova
necessidade.
A partir do caso desenvolvido na primeira etapa, limitar o crédito disponível para uma conta
com as seguintes regras:
A. O limite disponível de crédito deve ser abatido a cada transação feita e nunca pode ser
menor que 0 (zero). Ou seja, uma transação maior do que o limite disponível de crédito
deve ser recusada.
B. Transações de pagamento devem aumentar o limite disponível.
Exemplo 1:
1) Para uma conta com limite 100, ao fazer um saque no valor de 30 o limite deve passar
a ser de 70.
2) Ao tentar fazer uma nova transação de saque no valor de 80 devemos recusá-la, pois o
limite disponível agora é de apenas 70.
3) Assim que é emitida uma transação de pagamento no valor de 20, o limite disponível
aumenta para 90.

A coluna limite é o valor atual que a conta tem como limite de crédito disponível.
Além de implementar a regra de negócio, precisamos garantir que seja possível criar e
visualizar o limite de crédito disponível.
Implemente a feature seguindo as regras estabelecidas, mas sinta-se livre para decidir se
precisa de mais endpoints ou não e como acha melhor estruturar as informações.

#### Critérios de avaliação:
1. aplicação dos conceitos de orientação;
2. objetividade na lógica de programação;
3. clareza na definição de atributos e métodos;
