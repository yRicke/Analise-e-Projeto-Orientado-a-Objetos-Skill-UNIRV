# Engenharia de Software — APOO / ESW410

Skill em português para resolver atividades, especificar casos de uso e produzir ou revisar diagramas de análise e projeto orientados a objetos. Elaborada a partir de oito materiais da disciplina ESW410 da UniRV, com sínteses próprias e referências às páginas de origem.

## O que a skill cobre

- Questões teóricas sobre orientação a objetos e decisões de modelagem.
- Casos de uso: atores, fronteira, `include`, `extend` e especificação no formato das aulas.
- Modelo de domínio e diagrama de classes de projeto, com multiplicidades e responsabilidades.
- Sequência: organização boundary–controle–entidade, mensagens e fragmentos.
- Aplicações complementares: requisitos, rastreabilidade, critérios de aceitação, testes, atividades e estados.

Os materiais originais permanecem locais. O repositório contém instruções, sínteses e exemplos próprios; não distribui PDFs, slides ou gabaritos. Não há afiliação institucional ou promessa de conformidade integral com ABNT. As capacidades complementares estão diferenciadas dos conteúdos efetivamente encontrados nas aulas.

## Instalar no Codex

Copie a pasta inteira [`engenharia-software-apoo`](engenharia-software-apoo), incluindo `SKILL.md`, `agents`, `references` e `assets`, para a pasta de skills do Codex:

- Windows: `%USERPROFILE%\.codex\skills\engenharia-software-apoo`
- macOS/Linux: `~/.codex/skills/engenharia-software-apoo`
- Se `CODEX_HOME` estiver configurado, use a subpasta `skills` desse diretório.

Uma forma de instalar no Windows, em uma pasta onde ainda não exista este clone:

```powershell
git clone https://github.com/yRicke/Analise-e-Projeto-Orientado-a-Objetos-Skill-UNIRV.git
$destinoSkill = Join-Path $env:USERPROFILE '.codex\skills\engenharia-software-apoo'
Copy-Item -LiteralPath '.\Analise-e-Projeto-Orientado-a-Objetos-Skill-UNIRV\engenharia-software-apoo' -Destination $destinoSkill -Recurse
```

Se já houver uma instalação, compare as versões antes de substituí-la. Abra um novo chat para conferir se a skill aparece entre as disponíveis.

## Exemplos de uso

```text
Use $engenharia-software-apoo para responder às questões deste PDF,
preservando a numeração e justificando as respostas.
```

```text
Use $engenharia-software-apoo para criar o modelo conceitual de uma
biblioteca. Não inclua operações e justifique as multiplicidades.
```

```text
Use $engenharia-software-apoo para especificar Reservar Equipamento
no formato da disciplina e gerar o diagrama de casos de uso em PlantUML.
```

```text
Use $engenharia-software-apoo para revisar a coerência entre este caso
de uso, o diagrama de classes de projeto e o diagrama de sequência.
```

A skill pode ser selecionada automaticamente em tarefas compatíveis. PlantUML, Mermaid e Astah são opções de saída; a skill não instala nem depende obrigatoriamente dessas ferramentas. Uma imagem exige renderização real. Um arquivo `.asta` exige suporte nativo do Astah; a fonte PlantUML não é um arquivo Astah.

## Organização

O ponto de entrada é [`SKILL.md`](engenharia-software-apoo/SKILL.md). As referências são carregadas conforme o tipo de tarefa; os modelos em `assets` são editáveis. O [mapa das fontes](engenharia-software-apoo/references/fontes-e-convencoes.md) documenta origem, páginas, limites e diferenças entre convenções didáticas e aplicações complementares.

Para validar a estrutura, use `quick_validate.py` da skill `skill-creator` do ambiente Codex, passando o caminho de `engenharia-software-apoo`. Validação estrutural não equivale a execução de uma atividade nem certifica a correção de todo modelo futuro.
