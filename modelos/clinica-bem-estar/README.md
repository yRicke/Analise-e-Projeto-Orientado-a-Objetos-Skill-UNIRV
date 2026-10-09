# Agendar consulta — Clínica Bem Estar

Modelo original do cenário fornecido pelo usuário, seguindo a skill `engenharia-software-apoo`, especialmente `references/diagramas-sequencia.md` e `references/fontes-e-convencoes.md`.

- `agendar-consulta.asta`: versão revisada do projeto nativo, gerada pela API do Astah UML 12.0.0.
- `agendar-consulta.png`: imagem da versão revisada, exportada pelo próprio Astah e conferida visualmente.
- `GerarSequencia.java`: fonte de construção pela API instalada do Astah.

A recepcionista interage com a tela, que delega ao controle. O controle consulta o cadastro de pacientes, o corpo clínico e a agenda de consultas. O serviço de SMS recebe uma mensagem assíncrona.

O fragmento `opt [paciente == null]` registra somente pacientes ainda não cadastrados. A seleção de especialidade e médico ocorre após esse fragmento. A disponibilidade é verificada antes do `alt`: no ramo disponível, a consulta é registrada e confirmada; no ramo `else`, a tela informa o horário ocupado. O envio de SMS pertence ao ramo de sucesso, dentro de um `opt` condicionado à autorização do paciente.

A autorização de SMS é tratada como informação do paciente. O enunciado não descreve como ela é coletada; por isso, o modelo não acrescenta uma etapa de consentimento.

O projeto foi reaberto pela API, submetido à validação de modelos do Astah e inspecionado para conferir a associação das mensagens às guardas e o caráter assíncrono do SMS. Na versão revisada, os resultados de cadastro e agendamento também apresentam retornos tracejados. As mensagens coordenadas partem das ativações existentes do controle, prolongando suas execuções durante a identificação, o cadastro, a listagem de médicos e o agendamento. A listagem explicita todos os médicos da especialidade, com seus nomes.

A correção segue a semântica de ativação e chamada síncrona dos slides 47–48 e o exemplo do slide 57 de `Aula3-APOO_I-N2.pdf` (páginas físicas 7–8 e 17). Os fragmentos são associados explicitamente às mensagens e aos participantes para manter o cadastro no `opt`, o registro no ramo disponível e o SMS no `opt` interno ao sucesso.

A pasta mantém somente a versão revisada do modelo, com o nome `agendar-consulta`.
