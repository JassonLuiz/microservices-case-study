# Microservices Case Study

## Contexto
Sistema de e-commerce em microsserviços, projeto de portfólio.
Arquitetura, ADRs e roadmap estão em `docs/`. Leia `docs/adr/` antes
de qualquer decisão estrutural.

## Stack
- Java 21, Spring Boot 3, Maven (multi-módulo)
- Spring Cloud Gateway, Spring Security + JWT
- Kafka, PostgreSQL, MongoDB, Redis
- Docker Compose para ambiente local
- Testes: JUnit 5, Mockito, Testcontainers

## Regras invioláveis
- Nenhum serviço acessa o banco de outro. Apenas API ou evento.
- Toda mudança de contrato (REST ou evento Kafka) exige atualizar
  o OpenAPI/schema do evento E um teste de contrato.
- Toda decisão arquitetural nova exige um ADR em `docs/adr/`.
- Código novo sem teste não é considerado pronto.
- Nunca commitar credencial. Configuração sensível vem de variável
  de ambiente.

## Convenções
- Commits: Conventional Commits (feat:, fix:, chore:, docs:, test:)
- Branch: feature/<servico>-<descricao-curta>
- Pacotes: br.com.<seunome>.<servico>
- Camadas por serviço: controller / service / repository / domain / config

## Comandos úteis
- Subir ambiente local: `docker compose up -d`
- Rodar testes de um serviço: `mvn -pl order-service test`
- Build completo: `mvn clean install`

## Como trabalhar comigo
- Antes de implementar algo grande, apresente o plano e espere aprovação.
- Trabalhe um serviço/fase por vez, conforme o roadmap em `docs/`.
- Ao terminar uma tarefa, rode os testes e mostre o resultado.