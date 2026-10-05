# Segurança e dados

## Configuração local

A conexão MySQL usa as variáveis de ambiente `DB_URL`, `DB_USER` e `DB_PASSWORD`. O programa exige valores não vazios e não carrega arquivos `.env` automaticamente. Configure essas variáveis no ambiente de execução ou na configuração local da IDE.

Use um usuário dedicado ao banco de desenvolvimento, com permissões mínimas. Não use a conta administrativa do MySQL no aplicativo.

## O que não deve ser versionado

- Senhas, tokens, chaves privadas e arquivos `.env` com valores reais.
- Dumps de banco com dados de pessoas ou contas reais.
- Configurações pessoais da IDE que contenham credenciais.

Use somente dados fictícios neste simulador.

## Se uma credencial for publicada

Revogue ou troque a credencial no serviço de origem. Apagar a linha em um novo commit não remove o valor do histórico. Avalie também branches, cópias e histórico antes de divulgar novamente o projeto.

## Limites desta revisão

Em 5 de outubro de 2026, a configuração fixa de banco foi substituída por variáveis de ambiente. A revisão pesquisou padrões de segredos nos arquivos de texto acessíveis; ela não garante ausência de todos os dados sensíveis e não verifica validade de credenciais em serviços externos.
