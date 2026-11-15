---
description: Creates the task in the issue tracker and drives the feature.spec from the feature name given by the specification owner (PO).
---

# sdd.specify (Specification owner)

Creates the **real task in the issue tracker** and structures the business description given
by the PO into a new `feature.spec.md`. The feature identifier **comes from the issue
tracker**; there is no local counter.
Describes **only the "what"**: no technical decisions.

## Input

```
$ARGUMENTS
```

It may contain the **feature name** (and, optionally, the business description and the module).
**If the feature name is missing, ask the PO: "What is the name of the feature to be
specified?"** and continue only with the answer. If the module cannot be determined from the
name or the description, ask for it too.

## Step 1 — Create the task in the issue tracker (MCP)

Create the issue through the issue tracker's MCP server (`create_issue`) with:

- **project:** `<PROJECT>`
- **summary:** the feature name given by the PO
- **Type:** `User Story`
- **State:** the refinement state of the project (e.g., `In refinement`)
- **Assignee:** `<specification owner>`

The issue is created in the **backlog** of the sprint board (`<BOARD>`). The board does not
add issues automatically, and adding them is done by hand in the tracker, as the PO wants:
an issue enters the board only when it is prioritized.

Then **tell the PO**: `Created <ID> — [Feature name]` (with the URL
`<TRACKER_URL>/issue/<ID>`) **and start the specification.**

## Gate (do not continue if it fails)

Stop and explain if: the issue tracker MCP server is not available (do not invent an ID or
use a local counter without a decision by the PO) · the issue creation fails · the module
cannot be determined · a folder `features/<ID>-*/` already exists for the returned ID.

## Context to read

`constitution.md` · the `module.spec.md` of the module (vision, cross-cutting rules, and
feature index) · an existing `feature.spec.md` as a format reference. Reuse the terms of the
module and follow its cross-cutting rules.

## Rules

- Structure the description as business rules `BR-XX` and, when there is a clear acceptance
  criterion, `AC-XX`; record doubts as open questions `OQ-XX`, without inventing rules.
- A new spec always starts with `status: draft` and `version: 0.1.0`; approval (`approved`)
  belongs to the PO.
- Folder slug in kebab-case, derived from the short title of the feature.
- Every spec has, before the version history, a **Flow control** section: a table
  `Initial status | New status | Transition action | Process stage`, one row per transition,
  in short sentences. **Process stage** is the tab of the analysis screen (Processes) where
  the item goes after the transition: "Awaiting analysis", "In progress", or "Completed";
  "—" when it does not go to Processes. A feature without its own status: "No status change
  in this feature."

## Output

- `modules/<module>/features/<ID>-<slug>/feature.spec.md`, with front matter (`id`, `module`,
  `feature`, `status`, `version`, `created`) and body: Context · Business rules · Acceptance
  criteria (if any) · Open questions (if any) · Flow control · Version history.
- A new row in the **Feature index** table of the `module.spec.md`.
- **Final step — sync the issue:** when the spec draft is structured, update the issue in
  the tracker (`update_issue`) with **summary** = consolidated feature name and
  **description** = the spec content in Markdown (Context · BR-XX · AC-XX), in the same
  format as the previous issues.
- At the end, show the PO the path of the new spec, the row added to the index, and the
  confirmation that the issue was synced.

## Do not

Decide technical aspects · approve your own spec · overwrite an existing spec with the same
ID · add the issue to a sprint or board (it stays in the backlog) · use a local ID counter.
