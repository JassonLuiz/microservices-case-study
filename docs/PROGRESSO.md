# Progresso

Uma seção por dia, ordem cronológica inversa (mais recente no topo).

## 2026-09-19

- **Feito:** monorepo Maven criado (pom pai + 5 módulos), `docker-compose.yml` com infra local (3 Postgres, MongoDB, Redis, Kafka, Zipkin) e healthchecks, ADR-001 (Database per Service). Migração de Spring Boot 3.5.16 para 4.1.1 / Spring Cloud 2025.1.2 (ADR-007). Workflow `ci.yml` criado e mergeado em `develop` (PR #1). Branches `develop`/`homolog`/`main` protegidas (PR + CI obrigatórios, sem push direto). Fase 1, fatia 1/3 no `user-service`: entidade `User`, repository, migration Flyway, profiles dev/hml/prod, teste de repository com Testcontainers — `mvn clean install` verde. PR #2 mergeado em `develop`, passou por ultrareview (1 finding nível nit — `unique = true` documental sem efeito com `ddl-auto: validate` — corrigido). PR #3 (PROGRESSO.md) e PR #4 (convenções do CLAUDE.md: pacote real e exemplo de comando) mergeados em `develop`.
- **Incompleto:** fatias 2/3 (service + controller) e 3/3 (Security + JWT) da Fase 1 ainda não começaram.
- **Débito técnico:** teste de repository do `user-service` usa `@SpringBootTest` de contexto completo em vez de `@DataJpaTest`, porque a slice do Boot 4.1 não traz `FlywayAutoConfiguration`. Aceito por agora; quando Security entrar na fatia 3, todo teste de repository nos 4 serviços vai subir contexto completo com filtros de segurança — isso pesa no CI e merece revisão.
- **Próximo passo:** fatia 2/3 do `user-service` (service + controller).
