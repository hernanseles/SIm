<div align="center">

# SIm · Contas bancárias

**Prática de orientação a objetos e persistência em Java.**

![Java](https://img.shields.io/badge/Java-b45309?style=flat-square) ![MySQL · JDBC](https://img.shields.io/badge/MySQL%20%C2%B7%20JDBC-00758f?style=flat-square) ![Projeto educacional](https://img.shields.io/badge/Projeto%20educacional-0f766e?style=flat-square)

</div>

---

Aplicação de terminal para criar, listar e excluir contas, realizar depósitos, saques e transferências. O projeto separa menu, regras de negócio e acesso ao banco.

> Simulador educacional. Não use contas, dados pessoais ou dinheiro reais.

## Tecnologias

Java, JDBC e MySQL. O driver MySQL está em `lib/`; o repositório inclui configuração de projeto para IntelliJ IDEA.

## Preparar o ambiente

1. Instale um JDK e um servidor MySQL compatíveis com o driver incluído.
2. Crie um banco de desenvolvimento e uma tabela `contas` com os campos `id` (inteiro auto incremental e chave primária), `titular` (texto) e `saldo` (numérico).
3. Use um usuário específico para esse banco, com apenas as permissões necessárias.
4. Defina `DB_URL`, `DB_USER` e `DB_PASSWORD` no ambiente da execução, fora do Git.
5. Abra o projeto no IntelliJ, confira o JDK e a biblioteca JDBC e execute `src/Main.java`.

Exemplo de URL local: `jdbc:mysql://localhost:3306/seu_banco`. Não coloque senha na URL.

## Arquitetura

| Arquivo | Responsabilidade |
| --- | --- |
| `src/Main.java` | Entrada do programa. |
| `src/connection/Conexao.java` | Conexão configurada por variáveis de ambiente. |
| `src/poo/ContaBancaria.java` | Modelo de conta. |
| `src/poo/ContaDAO.java` | Operações SQL e transação de transferência. |
| `src/poo/ContaService.java` | Regras de negócio. |
| `src/poo/Menu.java` | Interação no terminal. |

## Limitações

O schema SQL não está versionado e o projeto não contém uma suíte automatizada de testes. A configuração de conexão exige variáveis de ambiente; o programa não carrega arquivos `.env` automaticamente. Antes de usar o simulador, valide o fluxo em um banco descartável.

## Segurança

Credenciais ficam no ambiente, nunca no código. Consulte [SECURITY.md](SECURITY.md) para orientações de prevenção e revisão de dados.
