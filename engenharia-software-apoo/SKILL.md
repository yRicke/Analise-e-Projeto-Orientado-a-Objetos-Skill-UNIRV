---
name: engenharia-software-apoo
description: Resolver atividades de análise e projeto orientados a objetos, especificar casos de uso e criar ou revisar diagramas UML e artefatos de engenharia de software. Usar em exercícios, estudos de caso e projetos de APOO/ESW410, incluindo requisitos, modelos de domínio, classes e sequência.
---

# Engenharia de Software — APOO

Produza respostas e modelos em português do Brasil, adequados ao enunciado, apoiados nas convenções da disciplina ESW410 da UniRV. A skill contém sínteses próprias dos materiais, com localização das fontes; funciona sem os PDFs originais.

## Interpretar a tarefa

Identifique o resultado solicitado, o domínio, a perspectiva (análise ou projeto), a ferramenta e o formato exigidos. Preserve numeração, terminologia e restrições do enunciado. Se a solicitação for uma correção, examine o artefato existente antes de propor outro.

Use o enunciado e as instruções atuais do professor para decidir o escopo. Consulte as referências relevantes abaixo. Não transforme exemplos das aulas em requisitos de um domínio diferente. Declare suposições que afetem atores, multiplicidades, regras, estados ou pós-condições; mantenha dúvidas sem evidência como questões em aberto. Pergunte apenas quando a lacuna impedir uma solução útil; continue as partes independentes.

Leia texto e diagramas dos anexos. PDF sem texto extraível exige leitura visual ou OCR conferido. Não infira uma seta ou cardinalidade apenas pela ordem do texto extraído.

## Escolher as referências

| Pedido | Referência a ler | Recurso reutilizável |
| --- | --- | --- |
| Questões teóricas, pilares de OO e justificativas | [fundamentos-oo.md](references/fundamentos-oo.md) | Exemplos adaptados ao domínio do enunciado |
| Especificação ou diagrama de casos de uso | [casos-de-uso.md](references/casos-de-uso.md) | [Modelo textual](assets/modelo-caso-de-uso.md) e [PlantUML](assets/modelo-casos-de-uso.puml) |
| Modelo conceitual, classes, objetos e relacionamentos | [modelagem-classes.md](references/modelagem-classes.md) | [PlantUML de projeto](assets/modelo-classes.puml) |
| Sequência e realização de um cenário | [diagramas-sequencia.md](references/diagramas-sequencia.md) | [PlantUML](assets/modelo-sequencia.puml) |
| Requisitos, testes, atividade, estados ou arquitetura | [engenharia-aplicada.md](references/engenharia-aplicada.md) | [Atividade](assets/modelo-atividade.puml) e [Estados](assets/modelo-estados.puml) |
| Entrega acadêmica, Astah, fonte ou divergência nas aulas | [fontes-e-convencoes.md](references/fontes-e-convencoes.md) | Mapa das páginas e convenções institucionais |

Os recursos são exemplos próprios e pontos de partida. Substitua seus nomes, condições e regras; não os apresente como resposta pronta para qualquer tarefa.

## Responder atividades

- Questão objetiva: indique a alternativa e justifique pelo conceito decisivo. Analise distratores quando solicitado ou necessário para esclarecer uma ambiguidade.
- Questão discursiva: responda diretamente, relacione o conceito ao cenário e explique a consequência da escolha. Respeite o limite de tamanho pedido.
- Modelagem: entregue o modelo completo do escopo pedido, as hipóteses relevantes e uma justificativa breve das decisões que não sejam óbvias.
- Revisão: indique o problema concreto, seu efeito e a correção; mantenha decisões válidas do trabalho original.
- Trabalho com múltiplos artefatos: use os mesmos termos e regras entre requisitos, casos de uso, classes, sequência e critérios de aceitação.

Evite acrescentar tecnologias, padrões, classes técnicas ou funcionalidades que o enunciado não exige. Se ampliar a solução ajudar, identifique a ampliação como proposta.

## Produzir diagramas

Prefira PlantUML para casos de uso e demais diagramas que precisem de notação UML fiel. Mermaid pode servir para classes, sequência, estados ou fluxos quando o usuário preferir ou o ambiente renderizar melhor. Um fluxograma com caixas não substitui silenciosamente o diagrama UML de casos de uso.

Entregue fonte editável e, quando a tarefa pedir uma imagem e houver ferramenta disponível, a imagem renderizada. Valide a sintaxe e examine a renderização: direção das setas, rótulos, multiplicidades, legibilidade e limites do sistema. Se não puder renderizar, informe a limitação; não declare que a imagem foi verificada.

Quando o enunciado exigir Astah, trate `.asta`, imagem exportada e documento textual como entregáveis distintos. Use o Astah ou uma integração disponível para gerar o arquivo nativo. Não renomeie `.puml`, XML ou imagem para `.asta`, nem prometa importação sem verificar o suporte da versão usada. Se não puder operar a ferramenta, entregue a fonte e instruções de reconstrução, identificando o arquivo nativo pendente.

## Conferir antes de entregar

Confira o objetivo do ator, os limites do sistema, as condições de sucesso e de falha. Leia cada associação de classes nos dois sentidos. Verifique que mensagens da sequência pertencem ao receptor e correspondem às operações do modelo de projeto, quando ambos forem solicitados. Confirme que os fluxos alternativos voltam a um passo existente ou terminam explicitamente.

Diferencie convenção didática, hipótese e regra do domínio. Para conceitos além dos materiais, use conhecimento de engenharia e fontes primárias quando necessário, sem atribuí-los ao professor. Não invente referências, dados de autoria ou conformidade integral com ABNT. Cite arquivo e página física quando usar os originais; se usar apenas esta síntese, diga isso.

Entregue somente os artefatos solicitados e uma explicação proporcional ao trabalho. A aplicação desta skill não autoriza publicar conteúdo, alterar repositórios ou enviar trabalhos a terceiros por conta própria.
