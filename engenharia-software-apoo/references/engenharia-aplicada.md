# Aplicações complementares de engenharia de software

Esta referência estende o núcleo de APOO para artefatos próximos. Atividades, estados, arquitetura detalhada e testes não são apresentados como capítulos dos PDFs locais; trate-os como aplicação complementar. Produza apenas o que a tarefa pedir.

## Requisitos e regras de negócio

Diferencie:

- Requisito funcional: comportamento ou capacidade do sistema.
- Requisito não funcional: qualidade ou restrição verificável em contexto definido.
- Regra de negócio: política do domínio que condiciona comportamento ou informação.
- Critério de aceitação: observação que permite verificar um resultado.

Se úteis para rastreabilidade, adote identificadores estáveis `RF-01`, `RNF-01`, `RN-01`, `UC-01` e `CT-01`. IDs não substituem descrição clara. Não crie métricas de desempenho, disponibilidade, prazos ou leis como se tivessem sido fornecidos.

| Origem | Artefato | Evidência de coerência |
| --- | --- | --- |
| Enunciado / regra | Requisito | Capacidade ou restrição rastreável ao pedido |
| Requisito | Caso de uso | Objetivo e condições observáveis |
| Caso / regra | Classe | Responsabilidade e dados necessários |
| Passo / extensão | Sequência | Mensagem ou guarda que realiza o comportamento |
| Pós-condição / falha | Teste | Resultado esperado, inclusive ausência de efeitos indevidos |

## Atividades

Escolha atividade para fluxo de trabalho, decisões e paralelismo. Modele início, ações, decisões com guardas, junções e término. Use raias quando responsabilidades forem relevantes. Fork/join são diferentes de decisão/merge: um divide e sincroniza trabalho concorrente; o outro escolhe e reúne alternativas.

As guardas devem cobrir os caminhos previstos. Não faça ramos de falha caírem em ações de sucesso por uma junção mal posicionada. Adapte [modelo-atividade.puml](../assets/modelo-atividade.puml). Um fluxograma simplificado pode ajudar explicações, mas identifique-o como tal quando não usar toda a notação UML.

## Estados

Escolha máquina de estados para o ciclo de vida de uma entidade. Estados descrevem situações persistentes ou relevantes; transições têm evento, guarda e efeito quando necessários. Distinga estado de uma ação momentânea e não use uma seta para cada clique.

Confira transições permitidas, estados terminais, cancelamento e possibilidade de repetição. Alinhe os estados aos atributos e pós-condições dos outros artefatos. Adapte [modelo-estados.puml](../assets/modelo-estados.puml). Não confunda transição de negócio com destruição de um objeto.

## Componentes e implantação

Componentes representam unidades da solução e suas interfaces/dependências; implantação representa ambientes, nós e artefatos executáveis. Antes de desenhar, identifique a arquitetura solicitada. Não acrescente microsserviços, nuvem ou fila para tornar uma solução pequena mais sofisticada.

Para a separação boundary–controle–entidade da disciplina, consulte [diagramas-sequencia.md](diagramas-sequencia.md). Para outro estilo, justifique responsabilidades e dependências pelas necessidades do projeto.

## Testes derivados da especificação

Escreva precondições, entradas, ação e resultado esperado. Cubra sucesso, alternativas e exceções relevantes; exercite limites explicitados de multiplicidade ou regra. Verifique estado persistido e efeitos que não devem ocorrer em falha, não apenas a mensagem exibida.

Exemplo próprio: se a reserva não pode ser confirmada com equipamento indisponível, um teste deve esperar recusa e nenhuma reserva confirmada. Não invente uma política de cobrança ou prazo para preencher um caso de teste.

Quando útil, use Dado/Quando/Então com um comportamento por cenário. Não diga que testes foram executados se apenas os especificou.

## Documentos e fontes

Use o modelo institucional se a entrega for acadêmica. Markdown é uma base editável; DOCX/PDF requer geração real e conferência da paginação. Respeite o formato solicitado e identifique qualquer limitação concreta.

Confirme normas atuais somente quando a conformidade for necessária; o nome do PDF de template não comprova conformidade integral com ABNT. Para novos símbolos ou conceitos UML, consulte a [especificação oficial](https://www.omg.org/spec/UML/2.5.1). Para sintaxe complementar: [atividade](https://plantuml.com/activity-diagram-beta), [estados](https://plantuml.com/state-diagram), [componentes](https://plantuml.com/component-diagram) e [implantação](https://plantuml.com/deployment-diagram).
