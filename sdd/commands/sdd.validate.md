---
description: Runs the Harness, checking the implementation of a feature against its specifications, plan, and technical context, and records the result in validation.md (does not change code or specifications).
---

# sdd.validate (Developer)

Runs the **Harness**: compares the produced implementation with the artifacts that govern it
and records the result. **It only checks and records**: it does not change code or
specifications.

## Input

```
$ARGUMENTS
```

Use as input the feature folder or the `plan.md` mentioned in the message, after
`sdd.implement`.

## Gate (do not continue if it fails)

Stop and explain if the `feature.spec.md` is not `status: approved` or if the `plan.md` has
open issues for the PO.

## Context to read

- the `feature.spec.md` (`BR-XX` / `AC-XX`), the `module.spec.md` of the module
  (cross-cutting rules `CR-XX`), and the `plan.md`;
- in each repository in scope: `project-context.md`, `architecture-guidelines.md`, and
  `ai-guardrails.md`;
- the code and the tests produced by `sdd.implement`.

## Verification

1. **Automatic part (CI):** in each repository in scope, run the build, the unit tests, and
   the regression tests of the existing features; record the results.
2. **Feature specification:** for each `BR-XX` and `AC-XX`, check that it has code and at
   least one passing test that references it; record the test as evidence, or record an
   issue.
3. **Module specification:** check that the cross-cutting rules (`CR-XX`) are used as defined.
4. **Plan:** check that the changes stay within what the plan defined.
5. **Technical context:** check adherence to the guidelines and guardrails of each repository,
   with reuse of existing components and no duplication.

Status: **Approved** when no issue is found; otherwise **Rejected**.

## Output

**`modules/<module>/features/<ID>-<slug>/validation.md`**:

```markdown
# Harness — <feature title>
## Status
## References: task and PR (same feature identifier) · feature.spec.md · module.spec.md · plan.md · technical context
## Automatic validation (CI)
## Adherence to the feature specification
## Adherence to the module specification
## Adherence to the plan
## Adherence to the technical context (guidelines and guardrails)
## Divergences
## Decision
```

## Convergence

When the status is **Rejected**: an issue that comes from the spec goes to the specification
owner (PO), and the flow waits until the spec is updated; then `sdd.plan` and `sdd.implement` run again to fix
the plan and the code, and the Harness runs again, until convergence. At the end, show the
`validation.md` to the developer.

## Do not

Change code, tests, or specifications · approve with open issues · commit or merge (that is
the developer's decision).
