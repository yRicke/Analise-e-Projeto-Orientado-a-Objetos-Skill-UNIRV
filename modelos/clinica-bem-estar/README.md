# Agendar consulta — Clínica Bem Estar

Modelo original do cenário fornecido pelo usuário, seguindo a skill `engenharia-software-apoo`, especialmente `references/diagramas-sequencia.md` e `references/fontes-e-convencoes.md`.

- `agendar-consulta.asta`: projeto nativo, gerado pela API do Astah UML 12.0.0.
- `agendar-consulta.png`: imagem exportada pelo próprio Astah e conferida visualmente.
- `GerarSequencia.java`: fonte de construção pela API instalada do Astah.

A recepcionista interage com a tela, que delega ao controle. O controle consulta o cadastro de pacientes, o corpo clínico e a agenda de consultas. O serviço de SMS recebe uma mensagem assíncrona.

O fragmento `opt [paciente == null]` registra somente pacientes ainda não cadastrados. A seleção de especialidade e médico ocorre após esse fragmento. A disponibilidade é verificada antes do `alt`: no ramo disponível, a consulta é registrada e confirmada; no ramo `else`, a tela informa o horário ocupado. O envio de SMS pertence ao ramo de sucesso, dentro de um `opt` condicionado à autorização do paciente.

A autorização de SMS é tratada como informação do paciente. O enunciado não descreve como ela é coletada; por isso, o modelo não acrescenta uma etapa de consentimento.

O projeto foi reaberto pela API, submetido à validação de modelos do Astah e inspecionado para conferir a associação das mensagens às guardas e o caráter assíncrono do SMS. Os resultados de cadastro e agendamento são indicados na própria chamada síncrona; as consultas por CPF, especialidade e disponibilidade apresentam retornos tracejados.

O diagrama que estava aberto no projeto sem nome não foi alterado: o controle de computador não conseguiu capturar a janela. Este arquivo é uma entrega separada e editável no Astah.
