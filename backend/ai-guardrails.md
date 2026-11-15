# AI guardrails — Back end

Limits that AI agents must respect in this repository, whatever the specification asks. When a
task seems to require crossing one of them, stop and ask the developer.

## Never

- **Invent** endpoints, fields, tables, classes, methods, DTOs, or utilities. Use only what exists
  in the code; when in doubt, ask.
- **Add dependencies** to `pom.xml`.
- **Edit an existing migration.** Schema changes only through a new script.
- **Change** `spring.jpa.hibernate.ddl-auto` or the database configuration.
- **Add** security, authentication, pagination, caching, logging frameworks, or new layers
  without an explicit decision in the plan.
- **Change the behavior of existing resources** outside the scope of the plan.
- **Leave** dead code, commented-out code, or orphan `TODO`/`FIXME`.
- **Edit** the specifications (`feature.spec.md`, `module.spec.md`).
- **Commit, push, merge, or open a pull request**: these are the developer's decisions.

## Always

- Keep all existing tests passing (`mvn test`).
- Write tests for every rule implemented, referencing `BR-XX` / `AC-XX`.
- Present all changes to the developer, with the map from rules to tests.
