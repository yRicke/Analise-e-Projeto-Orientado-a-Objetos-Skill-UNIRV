# Fontes e convenções da disciplina

Materiais analisados em 9 de outubro de 2026. Contexto identificado nos PDFs: ESW410 — Análise e Projeto Orientados a Objetos I, Engenharia de Software, UniRV; professor Pedro Henrique Mendes. São fontes de estudo, não autoria dos exemplos próprios desta skill nem endosso institucional.

## Localização por página física

“Página física” é a posição no PDF, iniciada em 1, inclusive capa. A paginação impressa nos slides e nas notas nem sempre coincide. Os cinco arquivos de slides não tinham camada de texto extraível e foram lidos visualmente.

| Arquivo original | Páginas | Conteúdo efetivamente examinado |
| --- | --- | --- |
| `APOO_N1.pdf` | 19 | 4: análise/projeto; 5–6: classe, objeto, atributos, métodos e mensagem; 6–8: encapsulamento/SRP; 8–10: multiplicidades e relações; 11–16: pilares, abstração, herança, classe abstrata, interface e polimorfismo; 17–18: erros; 18–19: síntese de biblioteca |
| `Aula1&2-APOO_I_N1.pdf` | 20 | 4: ementa; 8–14: paradigma e conceitos OO; 15–19: associação, agregação e composição |
| `Aula1-APOO_I-N1.pdf` | 20 | 3–5: multiplicidade; 6–8: SRP; 9–12: interfaces e classe abstrata; 13–15: sobrecarga/sobrescrita; 16–19: erros e exemplo integrado |
| `Aula1-APOO_I-N2.pdf` | 27 | 2–9: requisitos, UML, atores, casos, relações; 10–19: narrativa e seções; 20: erros; 21–22: mobilidade e resolução; 23–25: instalação/licença Astah; 26: entregáveis |
| `Aula2-APOO_I-N2.pdf` | 12 | 3: conceitual/projeto; 4–5: compartimentos, sintaxe e visibilidade; 6–8: multiplicidade, todo–parte e generalização; 9: dependência; 10: classe associativa; 11: interface/realização |
| `Aula3-APOO_I-N2.pdf` | 18 | 3–5: papel da sequência e camadas; 6–9: linhas de vida, ativação e mensagens; 10–15: loop, opt, alt, par, break e ref; 16–17: autenticação e resolução |
| `Caso_De_Uso_Solicitar_Corrida.pdf` | 4 | 2–3: prefácio, interessados, condições, cenário, extensões, requisitos especiais, variantes e controle; 4: seção de referências |
| `Template_Caso_de_Uso_ABNT.pdf` | 3 | 1: capa; 2: oito seções e cinco campos do prefácio; 3: referências |

## Convenções a preservar em atividades ESW410

- Modelo conceitual: conceitos, atributos e associações, sem operações.
- Diagrama de projeto: classes de software, visibilidade, tipos e operações.
- Sequência em camadas: boundary → controle → domínio; a boundary não acessa o domínio diretamente nesse recorte.
- Caso de uso: nome com verbo no infinitivo e objeto; atores externos; narrativa orientada a intenções e responsabilidades.
- Especificação: oito seções do template, com nível incluído no prefácio.
- A entrega descrita no slide físico 26 de N2 inclui projeto `.asta`, imagem exportada do diagrama e especificação em documento `.doc`. A exigência atual do enunciado prevalece, inclusive se pedir `.docx`, PDF ou outro formato.

O template tem organização acadêmica e capa institucional. A leitura do PDF não identifica todas as normas e dimensões de formatação; não afirmar conformidade integral com ABNT sem verificar os requisitos aplicáveis. Não copiar autoria do exemplo para o aluno.

## Simplificações e divergências que exigem atenção

- As aulas apresentam interface como contrato sem implementação e uma superclasse por classe. Isso não descreve todos os recursos de todas as linguagens; contextualize a resposta.
- N1 ensina agregação pelo ciclo de vida independente; N2 reconhece seu valor limitado e sugere associação simples. Use agregação quando houver propósito didático ou semântico, sem obrigá-la em toda posse.
- No slide físico 8 de `Aula2-APOO_I-N2.pdf`, a explicação orienta o triângulo ao tipo geral, mas a seta desenhada aparece invertida no exemplo. Esta skill usa o triângulo apontando à superclasse.
- O exemplo de mobilidade alterna nomes de cálculo e deixa aspectos de tarifa, cobrança e cancelamento em aberto. Não tratar esses detalhes como regras universais; explicitar o estado da solicitação antes de liberar motorista ou cancelar corrida.
- A composição é ensinada pela destruição da parte com o todo. Para modelagem técnica, considerar também propriedade exclusiva enquanto contida e possíveis remoções/transferências permitidas.
- O exemplo integrado de biblioteca é uma ilustração dos conceitos. A permanência de histórico de empréstimos deve ser decidida pelas regras do novo problema, sem copiar automaticamente a composição do exemplo.

## Referências primárias complementares

- [OMG — UML 2.5.1](https://www.omg.org/spec/UML/2.5.1): semântica de classificadores, relacionamentos, casos de uso, interações, atividades e estados.
- [PlantUML — casos de uso](https://plantuml.com/use-case-diagram), [classes](https://plantuml.com/class-diagram) e [sequência](https://plantuml.com/sequence-diagram): sintaxe das fontes editáveis.
- [PlantUML — atividade](https://plantuml.com/activity-diagram-beta) e [estados](https://plantuml.com/state-diagram): apoio às aplicações complementares.

As obras citadas na bibliografia dos slides não foram examinadas integralmente. Não apresentá-las como consultadas nem inventar citações ou páginas dessas obras.

## Distribuição

`APOO_N1.pdf` declara uso exclusivo dos alunos matriculados. Os PDFs, imagens de slides e extrações não integram esta skill publicada. Esta pasta distribui instruções, sínteses próprias e exemplos originais. A skill continua utilizável sem o caminho da pasta de trabalho ou acesso aos originais; para conferir um trecho literal, o usuário deve fornecer o material correspondente.
