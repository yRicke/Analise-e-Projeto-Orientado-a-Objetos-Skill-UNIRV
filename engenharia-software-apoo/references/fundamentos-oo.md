# Fundamentos de orientação a objetos

Base: `APOO_N1.pdf`, páginas físicas 4–18; slides N1 indicados em [fontes-e-convencoes.md](fontes-e-convencoes.md).

## Análise e projeto

Análise identifica conceitos e relações do problema: o que precisa ser atendido. Projeto define a solução: responsabilidades, operações, contratos e colaborações. Uma questão sobre modelo de domínio pede conceitos de negócio; não adicione automaticamente controladores, repositórios, telas ou métodos.

Classe descreve estrutura e comportamento compartilhados. Objeto é uma instância com identidade e valores concretos. Atributos representam estado; operações declaram comportamentos, implementados por métodos. Uma mensagem pede ao receptor uma operação, sem exigir que o emissor conheça sua implementação.

## Decisões pelos pilares

| Conceito | Pergunta para justificar a decisão |
| --- | --- |
| Abstração | Quais características importam para este problema? |
| Encapsulamento | Qual operação protege as regras e impede estado inválido? |
| Herança | A especialização é substituível pelo tipo geral no domínio? |
| Polimorfismo | O mesmo contrato admite comportamentos diferentes? |

Não modele todos os dados do mundo real. Uma pessoa pode ser Paciente em um sistema e Funcionário em outro; os atributos relevantes mudam. Em projeto, proteger saldo e estoque significa controlar alterações pelas operações que preservam suas invariantes, e não apenas criar getters e setters.

## Herança, composição e contratos

Herança exige relação de especialização: a frase “X é um Y” é uma primeira verificação, mas também examine substituição e comportamento. “X possui Y” não justifica herança. A posse de uma parte também não prova composição: examine propriedade e ciclo de vida no modelo.

Classe abstrata pode reunir estado e comportamento compartilhado e não é instanciada diretamente. Interface declara um contrato para seus realizadores, inclusive classes sem ancestral de negócio comum. Na comparação didática, interfaces são apresentadas sem estado de instância e sem implementação. Ao falar de uma linguagem concreta, confirme seus recursos: métodos padrão, implementações e herança múltipla variam conforme a linguagem e versão.

Não generalize que toda linguagem tem apenas uma superclasse ou que toda interface só admite métodos abstratos. Se o exercício adotar o modelo simplificado da aula, apresente-o nesse contexto.

## Sobrescrita e sobrecarga

- Sobrescrita: implementação específica de uma operação herdada ou de um contrato; a chamada polimórfica seleciona a implementação apropriada ao objeto.
- Sobrecarga: mesmo nome com assinaturas diferentes no contexto considerado. A forma de resolução depende da linguagem; nos exemplos estáticos da aula, ocorre em compilação.

Nome igual não basta para provar sobrescrita, e mudar apenas o tipo de retorno não garante sobrecarga válida. Em pseudocódigo ou UML, explicite parâmetros e retornos; em código, respeite a linguagem escolhida.

## Responsabilidades

SRP significa uma razão coesa para mudar; não significa que toda classe tenha apenas um método. Nos exemplos da disciplina, separar cálculo de negócio, persistência e apresentação evita concentrar mudanças independentes numa classe.

Ao revisar uma classe, relacione o problema a uma mudança concreta: alterar o formato de um relatório não deveria obrigar a mudar a regra de cálculo. Não imponha uma classe para cada verbo sem benefício.

## Armadilhas nas respostas

1. Trocar classe por objeto: um objeto precisa de valores e identidade de instância; não é outro tipo.
2. Tratar atributos públicos como encapsulamento só porque estão dentro de uma classe.
3. Escolher composição por haver exclusão em cascata no banco, sem regra de domínio que a sustente.
4. Criar ancestral artificial para reunir classes que só compartilham um contrato.
5. Copiar multiplicidade de um exemplo sem ler o enunciado.

Use exemplos pequenos do cenário pedido. Se o material apresentar uma simplificação, preserve sua finalidade didática e indique o limite quando ele influenciar a resposta.
