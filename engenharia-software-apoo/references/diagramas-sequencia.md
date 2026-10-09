# Diagramas de sequência

Base: `Aula3-APOO_I-N2.pdf`, páginas físicas 3–17. Sequência realiza um cenário do caso de uso; classes representam estrutura estática. Não tente desenhar todas as situações de todos os casos em uma única interação.

## Participantes e responsabilidades

Leia o fluxo principal e as extensões relevantes. Defina as linhas de vida, identifique quem recebe cada mensagem e determine seus parâmetros e resultados.

Para exercícios ESW410 que adotem a organização das aulas, use:

- Ator: papel externo que inicia a interação.
- Boundary / interface de usuário: recebe intenção e apresenta resultado.
- Controle: coordena a realização do caso.
- Entidade / domínio: conserva dados e aplica regras de negócio.

Nessa convenção, a interface de usuário comunica-se com o controle, que coordena o domínio. Isso é uma escolha arquitetural da aula, não uma restrição universal da UML. Não confunda boundary com uma interface de contrato OO. Se o usuário definir outra arquitetura ou pedir sequência em nível de sistema, respeite-a.

Identifique instâncias, por exemplo `controle : ControleReserva`, em vez de usar nomes genéricos que ocultem responsabilidades. Integrações externas podem participar; atores não devem chamar diretamente operações internas do domínio no recorte em camadas.

## Mensagens e tempo

O tempo segue de cima para baixo e o eixo horizontal distingue participantes. A sequência indica ordem; não implica duração exata sem anotações adicionais.

| Mensagem | Efeito | Notação UML |
| --- | --- | --- |
| Síncrona | Emissor aguarda conclusão | Linha contínua, ponta preenchida |
| Assíncrona | Emissor continua sem aguardar | Linha contínua, ponta aberta |
| Retorno | Resultado de chamada | Linha tracejada, ponta aberta |
| Autochamada | Participante solicita comportamento próprio | Seta volta à mesma linha de vida |

Use barras de ativação quando ajudarem a representar execução; balanceie entradas e saídas nos ramos. Criação e destruição devem ter significado no cenário. Não destrua uma linha de vida apenas para representar logout ou cancelamento de negócio.

Uma mensagem `validarDisponibilidade(periodo)` deve chegar ao participante responsável por essa validação. Retorno como `disponivel : boolean` não é uma nova operação de negócio. Não mostre acesso direto a senha ou saldo interno como substituto de uma operação responsável.

## Fragmentos combinados

| Fragmento | Usar para | Verificação |
| --- | --- | --- |
| `alt` | Caminhos alternativos | Guardas explicam cada ramo; use `else` quando aplicável |
| `opt` | Comportamento condicional único | Condição explícita; não há ramo alternativo obrigatório |
| `loop` | Repetição | Indique condição ou conjunto percorrido |
| `par` | Interações concorrentes | Há independência real entre os operandos |
| `break` | Tratamento que substitui o restante da interação envolvente | A guarda e o encerramento fazem sentido no cenário |
| `ref` | Reutilização de outra interação | O diagrama referenciado existe ou é identificado como pendente |

Guardas normalmente entre colchetes: `[disponivel]`, `[else]`. Não use `par` só para economizar altura. Não chame uma sequência de sucesso dentro de um ramo de falha. Um `ref` não representa automaticamente `include` de caso de uso; os níveis de modelagem são diferentes.

## Exceções e rastreabilidade

Traduza extensões textuais em guardas e mensagens correspondentes. Em indisponibilidade, mostre quem detecta, quem informa e o que deixa de ser criado ou alterado. Se houver reserva de recurso antes de uma falha posterior, represente sua liberação quando a regra exigir.

Mapeie passos do caso para mensagens ou trechos da interação. Quando DCP e sequência forem solicitados juntos, compare nomes e assinaturas das operações e justificativas de navegação. Não acrescente um repositório ou serviço sem necessidade do escopo.

## Entrega

Adapte [modelo-sequencia.puml](../assets/modelo-sequencia.puml). Renderize e confira guardas, ordem, balanceamento de ativações e retorno ao usuário. Para Mermaid, traduza mensagens e fragmentos compatíveis explicitamente; não assuma equivalência de todos os símbolos. Fonte da sintaxe: [PlantUML — sequência](https://plantuml.com/sequence-diagram). Semântica: [OMG UML 2.5.1](https://www.omg.org/spec/UML/2.5.1), capítulo de interações.
