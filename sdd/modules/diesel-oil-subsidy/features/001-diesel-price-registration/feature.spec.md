---
id: "001"
module: diesel-oil-subsidy
feature: Diesel Price Registration
status: approved
version: 1.2.0
created: 2026-07-22
---

# 001 — Diesel Price Registration

## Context

The Analyst is responsible for keeping up to date, day by day, the diesel oil prices used in
the calculation of the reimbursement to fishers. The Analyst acts nationally: any Analyst sees
and fills in the prices of all UFs. For each day of the reference month, the Analyst enters
three values: the Dollar Price, the International Diesel Price, and the National Price. The
National Price without ICMS is not filled in by the Analyst: it is calculated by the system in
the analysis of the reimbursement calculation. The screen is organized in tabs per UF, with
the reference month working as a filter inside each tab, following the same visual standard as
the other lists of the module.

The prices of each month must be filled in by the 15th day of the following month, in calendar
days, with no adjustment for weekends or holidays. After the deadline without filling, the
system does not block the Analyst, but starts to show a progressive alert in the panels of the
Analyst and of the Ministry's analysis team, which triggers the necessary external
communication.

A price only counts, and becomes available for the beneficiary's acknowledgment and for the
calculation, at the moment it is sent, with no requirement of evidence, source, or double
validation. In return, once sent, the value becomes immutable: in the MVP, there is no flow
inside the system to correct a price entered with an error (when needed, the correction
happens outside the system).

## Business rules

- **BR-01** — The Analyst fills in the prices of the dollar and of the international diesel per
  UF and per day, acting nationally: every Analyst sees and fills in the prices of all UFs,
  with no scope per UF.
- **BR-02** — The filling screen is organized in tabs per UF (all UFs), with the reference month
  as a filter inside the tab; the system lists the pending months and, inside them, the days
  without prices; for each day, the Analyst fills in three columns: Dollar Price, International
  Diesel Price, and National Price. The National Price without ICMS is not filled in by the
  Analyst: it is calculated by the system in the analysis of the reimbursement calculation.
- **BR-03** — The deadline to fill in each reference month is the 15th day of the following
  month, in **calendar days**, with no anticipation or adjustment when it falls on a weekend or
  holiday.
- **BR-04** — After the deadline, the system does not block the filling, but reinforces a
  progressive alert in the panel of the Analyst and in the panel of the Ministry's analysis
  team, each also organized in tabs per UF, both with a view of all UFs; the alert of the
  Ministry triggers the necessary external communication.
- **BR-05** — The prices entered are immutable after being sent, to protect the reimbursements
  already calculated from them; in the MVP, there is no correction flow inside the system.
- **BR-06** — The price requires no evidence or source and becomes valid at the moment it is
  sent, without double validation or approval, becoming available immediately for the
  beneficiary's acknowledgment and for the reimbursement calculation.

## Acceptance criteria

- **AC-01** — Given a closed reference month, when the 15th day of the following month passes
  without filling, then the system does not block the filling, but shows a progressive alert in
  the panels of the Analyst and of the Ministry's analysis team.
- **AC-02** — Given a price already sent, when the Analyst tries to change it, then the system
  prevents the change: sent values are immutable.

## Version history

| Version | Date | Change |
| --- | --- | --- |
| 1.2.0 | 2026-08-10 | PO decision: the Analyst acts nationally; any Analyst sees and fills in the prices of all UFs (Context, BR-01, BR-02, BR-04; the tabs per UF remain as the organization of the screen, and the alert panels get a national view). Open question OQ-03 resolved and removed: there is no scope per UF nor regional analysts; the part about the authorization of the "Paid" write-off remains in feature 010. OQ-04 trimmed. |
| 1.1.0 | 2026-08-10 | Correction after the divergence found in planning between the UI prototype and the specification, decided by the PO: (1) the specification asked for the **National Price without ICMS**, a field that did not exist in the prototype because the National Price is already entered without ICMS; the Analyst no longer fills it in, and it is calculated by the system in the analysis of the reimbursement calculation (Context and BR-02: three columns; open question recorded in the analysis feature); (2) **BR-03**: the deadline of the 15th day of the following month is in **calendar days**; the anticipation to the previous Friday on weekends and the exception for holidays are removed. |
