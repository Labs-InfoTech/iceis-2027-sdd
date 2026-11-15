---
description: Technically plans an approved feature.spec, producing a single plan for the feature that covers every repository in scope.
---

# sdd.plan (Developer)

Turns the "what" of the `feature.spec.md` into the "how" (technical decisions of the
developer).
**It does not change business rules.**

## Input

```
$ARGUMENTS
```

Use as input the `feature.spec.md` mentioned in the message
(`modules/<module>/features/<ID>-<slug>/feature.spec.md`) and, when the feature has one, its
UI prototype (`ui-prototype/` in the same folder, or the prototype link).

## Gate (do not continue if it fails)

Stop and explain if the input does not mention a `feature.spec.md`, if the file does not
exist, or if it is not `status: approved`.

## Context to read

**Mandatory to read and follow:**
- the `feature.spec.md` (business rules `BR-XX` / acceptance criteria `AC-XX`): the "what" to
  meet;
- the `module.spec.md` of the module: cross-cutting rules (`CR-XX`) and context shared
  among features;
- in each repository in scope (back end and/or front end): `project-context.md` (architecture
  and structure), `ai-guardrails.md` (limits that cannot be crossed) and, **above all,
  `architecture-guidelines.md`**: the plan must follow it **strictly** (formatting, reuse,
  DTOs, permissions, migrations, etc.).

**Support (consult when relevant):**
- `constitution.md`: governance and roles of the flow.

## Rules

- The developer decides the "how" (files, reuse or creation of controllers and services,
  DTOs, schemas, migrations), following the technical context of each repository.
- Identify the affected components, the existing components to reuse, the required changes,
  and the tests to produce, including the regression tests of the affected existing features.
- Meet each rule or criterion with the first valid option: (1) an existing component of the
  project; (2) the standard library or a dependency already installed; (3) minimal new code
  that follows the technical context. Prefer reuse to duplication.
- If the feature has a UI prototype, check it against the spec. A divergence → **send the
  issue to the specification owner (PO)** and stop: the PO decides whether the prototype or
  the spec changes.
- Ambiguous spec, or a rule change needed → **send the issue to the specification
  owner (PO)** and stop (do not assume).
- **A single plan per feature**, covering the changes in all repositories in scope, saved
  next to the spec.
- Each decision is linked to `BR-XX`/`AC-XX` and to the test that will check it (the basis
  for the tests of `sdd.implement`).

## Output

**`modules/<module>/features/<ID>-<slug>/plan.md`**:

```markdown
# Plan — <title>  (spec vX.Y.Z · scope: back end / front end)
## UI prototype check (if any): divergences found and the PO's decision
## Decisions per repository: data model/migrations · files to create/change · names · errors/security
## Traceability: table BR/AC → where it is met → test that checks it
## Steps: 1... 2...
## Open issues for the PO (if any)
```

At the end, present the plan to the developer for review: `sdd.implement` runs only after
this review.

## Do not

Run without an approved spec · edit the feature.spec or the module.spec · create business
rules · write code (that is `sdd.implement`).
