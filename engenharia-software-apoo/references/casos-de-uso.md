# Casos de uso: objetivos, diagrama e especificação

Base: `Aula1-APOO_I-N2.pdf`, páginas físicas 2–22 e 26; os dois PDFs de especificação. Para a estrutura institucional, use [modelo-caso-de-uso.md](../assets/modelo-caso-de-uso.md).

## Modelar a visão externa

Defina o sistema em análise. Um ator é um papel externo que interage com esse sistema: pessoa, organização ou serviço. O mesmo humano pode exercer vários papéis. Diferencie ator principal, que busca o objetivo, de suporte, que colabora. Interessado não precisa interagir diretamente e, portanto, não precisa aparecer como ator.

Banco de dados interno, tela e controlador não são atores desse sistema. Um serviço de dados independente pode ser ator se estiver realmente fora do escopo. Uma rotina agendada pode ter um disparador temporal; só modele um agendador como ator quando a fronteira do sistema justificar sua externalidade.

Nomeie casos com verbo no infinitivo e objeto: “Reservar Equipamento”. Nome de tela, clique ou atributo não define objetivo de usuário. Preserve casos auxiliares quando houver comportamento compartilhado que mereça especificação; não transforme cada passo do fluxo em uma elipse.

Desenhe atores fora do retângulo nomeado e casos de uso dentro. A associação ator–caso indica participação, não ordem de execução. Não conecte casos em sequência só porque um ocorre depois de outro.

## Relações e direções

| Relação | Semântica | Direção da seta |
| --- | --- | --- |
| `«include»` | Comportamento incluído exigido no ponto de inclusão do caso base | Base → incluído, tracejada e ponta aberta |
| `«extend»` | Comportamento adicional sob uma condição em um ponto de extensão; base tem sentido independente | Extensão → base, tracejada e ponta aberta |
| Generalização | Especialização herda participação ou comportamento do elemento geral | Especializado → geral, linha contínua e triângulo vazio |

Exemplo próprio: “Reservar Equipamento” inclui “Verificar Disponibilidade”; “Adicionar Seguro” estende “Reservar Equipamento” quando o usuário escolhe cobertura. Não use `include` para um comportamento que só ocorre em alguns caminhos sem delimitar esse caminho. Não extraia um caso `extend` para toda exceção textual.

Autenticação já concluída pode ser pré-condição; isso não obriga incluir “Autenticar Usuário” em todos os casos. Um caso de uso base não depende da extensão para cumprir seu objetivo.

## Escrever a especificação

Mantenha as oito seções do template da disciplina, incluindo o nível no prefácio:

1. Prefácio: nome, escopo, nível, ator principal e atores de suporte.
2. Interessados e interesses: resultado ou proteção esperado por cada interessado.
3. Pré-condições e garantias de sucesso: fatos que já devem valer e estado observável após êxito.
4. Cenário de sucesso principal: passos numerados, com ator ou sistema como sujeito.
5. Extensões: passo de origem, condição, tratamento e retorno ou encerramento.
6. Requisitos especiais: qualidade mensurável quando o enunciado fornecer critérios.
7. Variantes tecnológicas e de dados: meios alternativos de realizar a mesma intenção.
8. Informações gerais de controle: frequência e questões em aberto.

Referências vêm ao final, quando utilizadas. Garantias mínimas em falha e gatilho podem ser acrescentados se pedidos, sem eliminar os campos institucionais.

Pré-condição não é uma validação que pode falhar durante o fluxo. “Usuário autenticado” descreve o estado inicial; “Sistema verifica as credenciais” descreve comportamento. Uma pós-condição deve ser alcançada por passos explícitos, não apenas desejada.

Fluxos como `3a` e `3b` devem apontar ao passo 3 existente; `*a` indica condição transversal, se fizer sentido. Diga “retorna ao passo 3”, “encerra sem criar a reserva” ou outro destino preciso. Diferencie alternativa que ainda chega ao sucesso de exceção que o impede.

Se não há dado sobre tempo de resposta ou frequência, registre “não definido no enunciado” ou uma proposta identificada como tal. Não transporte os três segundos do exemplo de corrida para qualquer sistema.

## Consistência e revisão

- Todos os atores relevantes do diagrama aparecem na narrativa quando participam do cenário.
- Casos incluídos e extensões têm nomes e pontos de ocorrência consistentes com o texto.
- O sucesso anunciado exige ação que o produza.
- Cancelar uma solicitação ainda não criada não deve presumir que há registro ou recurso a liberar.
- Pagamento “válido” como pré-condição não significa que toda cobrança futura será autorizada.
- Avaliação depois de uma corrida concluída pode ser objetivo separado; cronologia, por si só, não caracteriza `extend`.

O exemplo de mobilidade fornecido serve para estudar a organização do texto. Para resolver ambiguidades de seu ciclo de vida, explique a decisão em vez de copiar seus fluxos literalmente.

## Fonte editável

Adapte [modelo-casos-de-uso.puml](../assets/modelo-casos-de-uso.puml). Use aliases estáveis e rótulos em português. Conferência de sintaxe: [documentação oficial de PlantUML](https://plantuml.com/use-case-diagram). Para a semântica UML, consulte [OMG UML 2.5.1](https://www.omg.org/spec/UML/2.5.1), em particular os capítulos de casos de uso.
