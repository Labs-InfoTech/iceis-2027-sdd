# AI guardrails — Front end

Limits that AI agents must respect in this repository, whatever the specification asks. When a
task seems to require crossing one of them, stop and ask the developer.

## Never

- **Invent** routes, API paths, fields, models, or services that do not exist in the code or in
  the plan; when in doubt, ask.
- **Add dependencies** to `package.json` (UI libraries, state management, masks, etc.).
- **Call an API path** that the back end does not offer or that the plan does not create.
- **Add** authentication, interceptors, global state, or new layers without an explicit decision
  in the plan.
- **Change the behavior of existing pages** outside the scope of the plan.
- **Leave** dead code, commented-out code, `console.log`, or orphan `TODO`/`FIXME`.
- **Edit** the specifications (`feature.spec.md`, `module.spec.md`).
- **Commit, push, merge, or open a pull request**: these are the developer's decisions.

## Always

- Keep all existing tests passing (`npm test`).
- Write tests for every rule implemented, referencing `BR-XX` / `AC-XX`.
- Present all changes to the developer, with the map from rules to tests.
