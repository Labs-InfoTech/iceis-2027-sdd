# Replication package — SDD framework for legacy multi-repository systems

This package accompanies the paper and allows the reader to run the Skills of the framework
(Section 4) on the example feature of the case study (Section 5): the daily registration of the
reference prices of diesel.

The original system is in production and its code and artifacts cannot be published. For this
reason, the package combines the framework (translated and anonymized), the specification of the
example feature, and **synthetic code bases** that play the role of the legacy back end and
front end.

## Structure

```
README.md
CLAUDE.md                          notes for the AI agent (layout of the three repositories)
.claude/commands/                  links to the Skills, so that Claude Code finds them
sdd/                               specification repository
├── constitution.md
├── commands/                      Skills: sdd.specify, sdd.plan, sdd.implement, sdd.validate
└── modules/diesel-oil-subsidy/
    ├── module.spec.md             excerpt of the module specification
    └── features/001-diesel-price-registration/
        ├── feature.spec.md        approved specification (input)
        ├── ui-prototype/          UI prototype (input of planning)
        ├── plan.md                produced by sdd.plan
        └── validation.md          produced by sdd.validate
backend/                           synthetic back end (Java, Spring Boot) and its technical context
frontend/                          synthetic front end (Angular) and its technical context
reproduction/
├── decisions.md                   answers given to the questions raised by the Skills
└── reference-run/                 outputs of a reference run, for comparison
```

## Paper and package

| Paper (Figure 2) | Package |
| --- | --- |
| `constitution.md` | `sdd/constitution.md` |
| Skills: specify, plan, implement, validate | `sdd/commands/sdd.specify.md`, `sdd.plan.md`, `sdd.implement.md`, `sdd.validate.md` |
| `module.spec.md` | `sdd/modules/diesel-oil-subsidy/module.spec.md` |
| `feature.spec.md` | `.../features/001-diesel-price-registration/feature.spec.md` |
| `plan.md` | `.../features/001-diesel-price-registration/plan.md` |
| `validation.md` | `.../features/001-diesel-price-registration/validation.md` |
| `project-context.md`, `architecture-guidelines.md`, `ai-guardrails.md` | root of `backend/` and of `frontend/` |
| Code and tests (legacy) | `backend/src/`, `frontend/src/` (synthetic) |

## Origin of each artifact

| Artifact | Origin |
| --- | --- |
| `constitution.md` | Original, translated and anonymized; aligned with the five-step workflow of the paper |
| `sdd.specify`, `sdd.plan`, `sdd.implement` | Original commands of the case, translated and anonymized; aligned with Algorithm 1 (single plan per feature, prototype check, order of preference for reuse) |
| `sdd.validate` | Reconstructed from Section 4.6 and Algorithm 2; it was not available as a command in the case |
| `module.spec.md` | Excerpt of the original: vision, actors, and CR-01 in full; the other sections are omitted so as not to expose the original system |
| `feature.spec.md` (001) | Original version used in the case (v1.2.0), translated and anonymized |
| `ui-prototype/` | Screens of the original prototype, without identifying elements |
| `backend/`, `frontend/` | Synthetic: reduced, fictitious code bases written for this package, with their own technical context |
| `reproduction/reference-run/` | Outputs of a run of the Skills on the synthetic code bases |

## How to reproduce

### Prerequisites

- [Claude Code](https://code.claude.com/docs/en/overview)
- Java 17 and Maven (back end)
- Node.js 18 or later, npm, and Chrome (front end tests)

### Steps

Open the root of the package in Claude Code. The Skills are available as slash commands.

1. **Planning** — run:
   ```
   /sdd.plan sdd/modules/diesel-oil-subsidy/features/001-diesel-price-registration/feature.spec.md
   ```
   The Skill checks the prototype against the specification, raises the points that need a
   decision, and writes `plan.md` in the feature folder. Answer the questions with
   `reproduction/decisions.md` and review the plan.
2. **Implementation** — run:
   ```
   /sdd.implement sdd/modules/diesel-oil-subsidy/features/001-diesel-price-registration/plan.md
   ```
   The Skill writes the code and the tests in `backend/` and `frontend/`, each test referencing
   the rule or criterion it checks, and presents the changes. It does not commit.
3. **Validation** — run:
   ```
   /sdd.validate sdd/modules/diesel-oil-subsidy/features/001-diesel-price-registration
   ```
   The Skill runs the build and the tests and writes `validation.md` with the status
   **Approved** or **Rejected**.

### Expected result

The run succeeds when `validation.md` ends with the status **Approved**. The generated plan and
code may differ from the reference run, because the agent is not deterministic; the comparison
is made with `reproduction/reference-run/`.

### Scope

The reproduction covers steps 3 and 4 of the workflow, starting from the approved feature
specification. Step 2 (`sdd.specify`) needs an issue tracker connected through an MCP server and
is not part of the reproduction.

## Notes

- **Anonymization:** names of people, organizations, systems, internal addresses, and identifiers
  were removed. Feature identifiers were renumbered (001 to 018); only feature 001 is included.
- **Terms kept in Portuguese** (RODe, NFe, UF, ICMS, LOA) are explained in `module.spec.md`.
- **Flow control:** `sdd.specify` asks for a "Flow control" section, which the specification of
  feature 001 does not have because it was written before that rule.
- **Not included:** the original source code, the other feature specifications, the full module
  specification, and the commit history.
