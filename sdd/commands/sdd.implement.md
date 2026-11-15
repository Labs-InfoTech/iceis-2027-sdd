---
description: Executes a plan.md, generating code and tests linked to the rules of the spec, and presents all changes for the developer to review (no commit or PR).
---

# sdd.implement (Developer)

Implements **strictly** what is in the `feature.spec.md` and in the `plan.md`.
**It does not create new business rules.**

## Input

```
$ARGUMENTS
```

Use as input the `plan.md` mentioned in the message (output of `sdd.plan`), after the
developer has reviewed it.

## Gate (do not continue if it fails)

Stop and explain if the input does not mention a `plan.md`, if the plan has open items in
**Open issues for the PO**, or if the source `feature.spec.md` is not `status: approved`.

## Context to read

**Mandatory to read and follow:**
- the `plan.md` and the `feature.spec.md` (business rules `BR-XX` / criteria `AC-XX`);
- in each repository in scope (back end and/or front end): `project-context.md`,
  `ai-guardrails.md` and, **above all, `architecture-guidelines.md`**: the code must follow it
  **strictly** (formatting, reuse, DTOs, permissions, logs, migrations, etc.). Do not invent;
  when in doubt, ask.

## Rules

- For each step of the plan, write the minimal code that meets it, following the technical
  context; nothing outside the scope, and no dependencies or abstractions that are not in
  the plan.
- Divergence, ambiguity, or a rule change needed → **send the issue to the specification owner
  (PO)** and stop.
- **Tests are mandatory** for everything that is written; each test references the
  `BR-XX`/`AC-XX` it checks (e.g., in its name or in a comment: `BR-01`). Without a test, the
  rule is not delivered.
- **Never** commit, push, or open a PR. **Always** present all changes for the developer to
  review; commit and PR are the developer's decision and action.

## Output

- **Code** according to the plan, in the repositories in scope.
- **Automated tests** covering each `BR-XX`/`AC-XX`, passing locally and in CI.
- **Summary of changes** for review: files created or changed, what was delivered, and the
  rule → test map. The developer reviews and decides on the commit and the PR.
- Next step: validation with `sdd.validate`.

## Do not

Run without a `plan.md` (or with open issues for the PO) · edit the feature.spec or the
module.spec · create business rules · deliver code without tests · go beyond the scope of the
plan · **commit or open a PR**.
