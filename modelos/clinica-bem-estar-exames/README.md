# Atendimento de exames laboratoriais — Clínica Bem Estar

Diagrama de atividades original do cenário fornecido, construído pela API nativa do Astah UML 12.0.0. A pasta é independente do modelo de agendamento de consultas.

- `atendimento-exames.asta`: projeto nativo editável no Astah.
- `atendimento-exames.png`: imagem exportada pelo próprio Astah e conferida visualmente.
- `GerarAtividades.java`: fonte da construção pela API do Astah.

As cinco partições distribuem as responsabilidades entre paciente, recepcionista, técnico de laboratório, laboratório e médico. O pedido de exame e o laudo são nós de objeto tipados, com o estereótipo descritivo `documento`; suas setas representam a circulação dos documentos.

A cobertura pelo convênio e o pagamento particular convergem por um nó de merge. A desistência encerra toda a atividade em um final separado. A amostra inadequada retorna ao merge anterior à coleta, sem limite de repetições.

Após a aprovação da amostra, um fork inicia a análise laboratorial e o agendamento do retorno em paralelo. Um join com duas entradas exige a conclusão de ambos antes da emissão do laudo. Um evento de tempo representa a chegada do dia do retorno; a análise pelo médico recebe tanto o laudo quanto a presença do paciente.

O pedido já foi fornecido pelo médico antes do início deste processo. A ação do paciente na consulta representa seu comparecimento; a análise conjunta está na partição do médico. O estereótipo `documento` é uma indicação descritiva, não uma exigência da notação UML.

Apoio: skill `engenharia-software-apoo`, referência `references/engenharia-aplicada.md`. O modelo foi reaberto e validado pelo Astah, com conferência das responsabilidades, dos dois documentos e das entradas e saídas do fork e do join.
