# Classes, modelo de domínio e relacionamentos

Base: `APOO_N1.pdf`, páginas físicas 5–19; `Aula1-APOO_I-N1.pdf`, 3–19; `Aula2-APOO_I-N2.pdf`, 3–11.

## Definir a perspectiva

| Perspectiva | Incluir | Evitar por padrão |
| --- | --- | --- |
| Conceitual / análise | Conceitos de negócio, atributos relevantes e associações | Operações e classes técnicas da solução |
| Projeto / DCP | Classes de software, tipos, visibilidade, operações, interfaces e colaborações | Detalhes sem ligação com responsabilidades necessárias |

A aula N2 define expressamente o modelo conceitual sem operações. Use esse recorte em atividades da disciplina, mesmo que exemplos introdutórios de OO tenham métodos. Quando “diagrama de classes” for ambíguo, infira pelo objetivo do exercício e indique a perspectiva escolhida.

Encontre candidatos nos conceitos e acontecimentos do domínio, e atribua responsabilidades pelos fluxos. Nem todo substantivo vira classe: nome e telefone normalmente são atributos; aluguel ou consulta podem ser entidades que registram um acontecimento. Evite classes sem propósito demonstrável.

## Notação

Nomes de classes no singular em PascalCase. Atributos e operações com nomes consistentes, normalmente camelCase. Tipos em projeto devem ser adequados ao domínio e à linguagem; identificadores como CPF e telefone não são números para cálculo. Para valores monetários em implementação, prefira representação decimal apropriada em vez de copiar automaticamente `double` dos slides.

```text
- quantidade : int
+ calcularSubtotal() : Decimal
+ alterarQuantidade(novaQuantidade : int) : void
```

Visibilidade: `+` pública, `-` privada, `#` protegida, `~` pacote. Classes abstratas e operações abstratas devem ser distinguíveis; interfaces levam `«interface»`. Um diagrama de objetos mostra instâncias `nomeObjeto : Classe` sublinhadas e valores concretos; não é um diagrama de classes com nomes trocados.

## Relações

| Relação | Quando escolher | Notação / direção |
| --- | --- | --- |
| Associação | Vínculo estrutural entre instâncias | Linha contínua; navegabilidade só quando relevante |
| Agregação compartilhada | Todo–parte com parte independente, se a distinção tiver utilidade | Losango vazio no todo |
| Composição | Parte pertencente a no máximo um todo composto por vez, com ciclo de vida vinculado enquanto contida | Losango cheio no todo |
| Generalização | Especialização substituível do tipo geral | Triângulo vazio apontando ao geral, linha contínua |
| Realização | Classe implementa contrato de interface | Triângulo vazio apontando à interface, linha tracejada |
| Dependência | Uso sem vínculo estrutural necessário, como parâmetro ou chamada pontual | Seta aberta tracejada de quem usa para o usado |

Uma parte pode ser removida ou transferida antes da destruição do todo se as regras permitirem; não reduza composição a uma propriedade física absoluta. Se a propriedade e o ciclo de vida não estiverem claros, use associação ou declare a hipótese. Agregação compartilhada tem semântica fraca; a aula N2 também admite preferir associação simples.

Não ponha multiplicidade em generalização, realização ou dependência. Não represente a mesma relação simultaneamente por atributo de referência e associação sem necessidade de detalhamento.

## Multiplicidade: ler nos dois sentidos

`A "1" -- "0..*" B` significa: uma instância de A pode estar ligada a zero ou muitas instâncias de B; uma instância de B está ligada a exatamente uma instância de A. A marca perto de B quantifica B para um A.

Use `1`, `0..1`, `0..*` / `*`, `1..*` ou intervalo fornecido. Registre optionalidade e limites pelas regras de negócio. Um máximo de vagas ou empréstimos não deve ser inventado. Restrições condicionais podem exigir uma nota: uma reserva em elaboração pode não ter itens, enquanto uma confirmada deve ter pelo menos um.

Losango define propriedade; cardinalidade define quantidade. Escolher composição não decide automaticamente entre `0..*` e `1..*`.

## Classe associativa ou entidade de ocorrência

Use classe associativa quando os atributos pertencem ao vínculo: uma matrícula relaciona aluno e turma com data e situação. Desenhe a classe ligada à associação por linha tracejada sem setas nem uma nova multiplicidade nessa ligação.

Se o mesmo par precisa de vários registros distintos ao longo do tempo, avalie uma classe comum de ocorrência com duas associações. Na convenção tratada na aula, repetir consultas entre o mesmo paciente e médico motiva modelar Consulta como entidade própria: cada Consulta liga exatamente um paciente e um médico; cada paciente/médico pode ter muitas consultas. Confirme limites temporais ou qualificadores se o exercício os exigir.

## Conferência prática

Verifique cada atributo contra o contexto e cada operação contra a responsabilidade de seu receptor. Leia cada associação em duas frases. Justifique herança por substituição e composição por regra de propriedade/ciclo de vida. Se houver sequência, cheque que suas chamadas têm correspondência no DCP; em um modelo apenas conceitual, não acrescente operações só para forçar essa correspondência.

Adapte [modelo-classes.puml](../assets/modelo-classes.puml) apenas para projeto. Para análise, retire tipos técnicos, visibilidades e operações conforme o recorte solicitado. Sintaxe: [PlantUML — classes](https://plantuml.com/class-diagram). Semântica: [OMG UML 2.5.1](https://www.omg.org/spec/UML/2.5.1).
