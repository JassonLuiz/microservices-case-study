# Microservices Case Study

## Contexto
Sistema de e-commerce em microsserviços, projeto de portfólio.
Arquitetura, ADRs e roadmap estão em `docs/`. Leia `docs/adr/` antes
de qualquer decisão estrutural.

## Stack
- Java 21, Spring Boot 4.1, Maven (multi-módulo)
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
- Nunca incluir o campo password em toString(), log ou resposta de API —
  nem via geração automática de IDE/Lombok.

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

## Particularidades do Spring Boot 4.1
Descobertas na Fase 1 (user-service) que evitam redescoberta em sessões futuras:

- **Test slices reorganizados em módulos por feature.** `@DataJpaTest` e
  `@AutoConfigureTestDatabase` saíram de `spring-boot-test-autoconfigure`
  e foram para módulos dedicados (`org.springframework.boot.data.jpa.test.autoconfigure`,
  `org.springframework.boot.jdbc.test.autoconfigure`). Além disso,
  `@DataJpaTest` **não** importa `FlywayAutoConfiguration` automaticamente
  nessa versão — teste de repository com migration real fica mais simples
  usando `@SpringBootTest(webEnvironment = NONE)` + `@Transactional` (sobe
  o contexto de aplicação completo, com Flyway) do que remontar a lista de
  auto-configurations da slice manualmente.
- **Flyway virou módulo separado.** Ter só `flyway-core` (+ `flyway-database-postgresql`)
  no classpath não ativa mais o auto-configure. É preciso a dependência
  `org.springframework.boot:spring-boot-starter-flyway`, que traz o módulo
  `spring-boot-flyway` com `FlywayAutoConfiguration`.
- **Testcontainers 2.0** (gerenciado pelo BOM do Boot 4.1) renomeou os
  artefatos com prefixo `testcontainers-` (ex.: `org.testcontainers:testcontainers-postgresql`,
  `org.testcontainers:testcontainers-junit-jupiter`), diferente das
  coordenadas antigas (`org.testcontainers:postgresql`, `org.testcontainers:junit-jupiter`)
  usadas em exemplos e tutoriais anteriores ao Boot 4.