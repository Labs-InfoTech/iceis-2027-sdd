# Replication package — agent notes

This package simulates three repositories of a multi-repository system:

- `sdd/` — the specification repository (constitution, Skills, module and feature specs).
- `backend/` — the back-end code repository, with its technical context.
- `frontend/` — the front-end code repository, with its technical context.

Paths in the Skills that start with `modules/` are relative to `sdd/`. The technical context of
each code repository is in its root: `project-context.md`, `architecture-guidelines.md`, and
`ai-guardrails.md`. Governance rules are in `sdd/constitution.md`.
