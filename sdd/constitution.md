# Constitution — Spec-Driven Development (SDD)

> This document defines the "rules of the game" of SDD in the system. It is short, stable,
> and applies to **all modules** versioned in this repository. Changing the constitution
> requires agreement between the PO and the development team.

## Context

We adopt **Spec-Driven Development (SDD)** at the **spec-anchored** level (the specification
as the anchor):

- The **specification is the source of truth** for what the system must do.
- **Code is born from the spec**; if the behavior changes, the **spec is updated** before or
  together with the change.
- Every spec is **versioned and committed**, and kept up to date.
- Specs describe **only the "what" and the business rules** (no technical decisions).

## Core rules

1. **The spec is the source of truth.** The behavior of the system is what the spec says;
   any divergence between code and spec is a **defect**.
2. **Single owner of the spec.** Only the **PO** (specification owner) creates and changes
   specs. The developer never edits a spec.
3. **The spec is non-technical.** Specs contain only the "what" and the business rules; no
   technical decisions (endpoints, tables, class names, DTOs, schemas).
4. **Every feature enters the index.** When a feature is created, it is recorded in the
   feature index of the corresponding `module.spec.md`.
5. **Approval gate.** No `draft` spec becomes the basis for development without the PO's
   approval (`approved`).
6. **Behavior change = new spec version.** A rule change requires updating the spec (new
   `version`) and an adjustment task, never a silent change in the code.
7. **Traceability.** Task (issue tracker), branch, spec, and PR share the feature identifier
   `<ID>`. Tests reference the rules (`BR-XX` / `AC-XX`).
8. **Separation of artifacts.** Rules, plans, and validation records → specification
   repository (`sdd/`); technical context and code → each code repository; progress → issue
   tracker.

## Roles and responsibilities

| Actor | Responsible for |
| --- | --- |
| **PO** (specification owner) | Create, approve, and maintain the specs (single owner); define the business rules; decide divergences between the UI prototype and the spec |
| **Developer** | Maintain the technical context of each repository; plan, implement, and test; decide the "how"; review the changes and decide on commit and PR |
| **Skills / AI agents** | Draft the spec and update the index from the task; generate the plan, the code, and the tests; run the validation (Harness), always within the limits below |

**Core rule:** only the PO changes the spec. If, during planning or implementation, a need to
change behavior or a rule appears, it is **sent to the PO**, who updates the spec and opens an
adjustment task. The code never changes behavior without the corresponding spec.

## Agent limits

Skills and AI agents:

- never approve a spec, and never change a spec or a business rule;
- stop and send the issue to the PO when the spec is ambiguous, diverges from the UI
  prototype, or needs a rule change;
- follow the technical context of each repository (guidelines and guardrails), preferring
  reuse to new code;
- never commit, push, merge, or open a PR: these are the developer's decisions.

## Workflow

The workflow has five steps, supported by four Skills:
`sdd.specify` → `sdd.plan` → `sdd.implement` → `sdd.validate`.

0. **Technical context (Developer)** — once per repository, and again when the architecture
   changes: `project-context.md`, `architecture-guidelines.md`, and `ai-guardrails.md`.
1. **Module specification (PO)** — once per module, with the support of an AI agent: the
   `module.spec.md`, with the cross-cutting rules (`CR-XX`) and the feature index.
2. **Feature specification (PO)** — the PO runs `sdd.specify` with the feature name; the Skill
   creates the task in the issue tracker, generates the `feature.spec.md` (`status: draft`),
   and adds the feature to the index. The PO reviews and approves it (`status: approved`).
3. **Planning and implementation (Developer)** — `sdd.plan` generates the single `plan.md` of
   the feature, which the developer reviews; `sdd.implement` generates code and **tests**
   linked to the rules and acceptance criteria (`BR-XX` / `AC-XX`), and presents the changes
   to the developer.
4. **Validation and convergence (Developer)** — `sdd.validate` runs the Harness and records
   the result in `validation.md`. A divergence in the plan or in the code returns to step 3;
   a divergence in the spec returns to step 2. Validation runs again until convergence. The
   developer then decides on commit and PR, linked to the task.

## Where each piece of information lives

| Information | Where | Who maintains |
| --- | --- | --- |
| Business rules (the "what") | `feature.spec.md` / `module.spec.md` (`sdd/`) | PO |
| Technical context | `project-context.md`, `architecture-guidelines.md`, `ai-guardrails.md` (each code repository) | Developer |
| Technical decisions (the "how") | `plan.md` (`sdd/`) + code | Developer |
| Validation record | `validation.md` (`sdd/`) | Validation Skill (Harness) |
| Execution progress | Issue tracker (issue) | Developer |
| Validation of the rules | Automated tests (CI) | Automatic |

## Conventions

- **Identifier:** `<ID>`, shared by task, branch, spec, and PR; the feature folder is
  `<ID>-<slug>`.
- **Business rules:** `BR-XX` · **Acceptance criteria:** `AC-XX` · **Cross-cutting rules of
  the module:** `CR-XX` · **Open questions:** `OQ-XX`.
- **Spec status:** `draft` → `approved`.
- **Spec version:** incremented at each change of behavior or rule.
