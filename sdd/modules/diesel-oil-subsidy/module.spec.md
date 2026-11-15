---
module: diesel-oil-subsidy
title: Diesel Oil Subsidy Module
status: approved
---

# Diesel Oil Subsidy Module

> **Excerpt.** This is an excerpt of the module specification, prepared for the replication
> package. The sections that are not needed to follow feature 001 keep their title and a short
> description of their content, but the content itself is omitted, so as not to expose the
> design of a system in production.

## Terms kept in Portuguese

| Term | Meaning |
| --- | --- |
| RODe | Electronic Diesel Oil Requisition (*Requisição de Óleo Diesel Eletrônica*), the document that allows the subsidized purchase |
| NFe | Brazilian electronic invoice (*Nota Fiscal eletrônica*) |
| UF | Federative unit, that is, each Brazilian state |
| ICMS | State tax on the circulation of goods |
| LOA | Annual budget law (*Lei Orçamentária Anual*): the budget available for the program |

## Vision

The module supports the Economic Subsidy Program for the Price of Diesel Oil (*Programa de
Subvenção Econômica ao Preço do Óleo Diesel*), requested by the federal ministry responsible for
fisheries (hereafter, the Ministry). The program was created by Law nº 9.445/1997 and is
regulated by Decree nº 7.077/2010. Its goal is to bring the price of national diesel close to
the international price, which makes Brazilian fish more competitive and raises the income of
fishers.

The program has three types of participants: individual beneficiaries (owners, shipowners, or
lessees of registered fishing vessels), class entities, and fuel suppliers. It is based on two
processes: qualification (*habilitação*), in which participants apply to join the program in a
yearly window before the fiscal year, and reimbursement (*ressarcimento*), in which
beneficiaries prove that they used the subsidized diesel on fishing trips and the government
pays the aid.

The module must implement these processes for the three types of participants, reuse the
registries, access profiles, and visual standard of the platform already in production at the
Ministry (hereafter, the platform), and link beneficiaries to their vessels and class entities.
It must also take in the backlog of pending cases, provide reports for managers, and be ready
for future integrations. The module is new, but the platform is not: its requirements come from
two sources, the legal framework of the program and the behavior of the running platform, and
it must be added without breaking the existing modules, respecting the data model and
conventions of the platform.

## Actors and profiles

- **Analyst:** enters, for each UF and each day, the dollar price, the international diesel
  price, and the national price (feature 001). Uses an access profile that already exists on
  the platform.
- **Program participants:** individual beneficiaries (owners, shipowners, or lessees of
  registered fishing vessels), class entities, and fuel suppliers.

### Access and identity

*Omitted in this package.* In the original: how internal and external users log in and sign documents.

### Permission matrix

*Omitted in this package.* In the original: the actions allowed to each profile.

## States and macro-flow

*Omitted in this package.* In the original.

## Integrations

*Omitted in this package.* In the original: the external services used by the module .

## Cross-cutting rules

### CR-01: Reimbursement formula and season cap (LOA)

The reimbursement of each invoice follows these steps:

- `price paid/L = amount actually paid on the invoice ÷ liters`

- `international/L = international diesel price (US$/L) × dollar price of the day`

- `difference/L = price paid/L − international/L`

- `reimbursement/L = 0` if the difference is `≤ 0`, otherwise `min(difference/L, season cap)`

- `amount reimbursed for the invoice = reimbursement/L × liters`

The amount actually paid is the gross value of the products minus the discounts. It is already
net of ICMS, which is single-phase and comes exempted on the invoice by virtue of the RODe.

The cap per liter is the same for all UFs and belongs to the season (Subsidy period). Its
calculation is `cap/L = available LOA of the season ÷ volume of the season`. The volume starts
as a forecast typed in the registration of the Period. Once the qualification is complete, it
becomes the sum of the quotas of the qualified vessels, with a recalculation of the cap without
retroactive effect (006, BR-03). The amount reimbursed for an invoice can never exceed
`season cap × liters of the invoice`.

### Other cross-cutting rules

*Omitted in this package.* Titles only:

- CR-02: Vessel quota and balance conservation
- CR-03: Global deadlines
- CR-04: Exclusive bond of the vessel
- CR-05: Legacy system
- CR-06: Notifications, audit, and dashboard
- CR-07: Messages of the integrations

## Open questions of the module

*Omitted in this package.* In the original: questions left for the future qualification features.

## Feature index

| ID | Feature | Status | Spec |
| --- | --- | --- | --- |
| 001 | Diesel Price Registration | approved | [feature.spec.md](features/001-diesel-price-registration/feature.spec.md) |
| 002 | Class Entity Registration | approved | not included in this package |
| 003 | Beneficiary Registration and Qualification | approved | not included in this package |
| 004 | Fuel Supplier Registration | approved | not included in this package |
| 005 | Vessel and Quota Registration | approved | not included in this package |
| 006 | LOA and Cap Parameters | approved | not included in this package |
| 007 | RODe Issuance and Signature by the Individual Beneficiary | approved | not included in this package |
| 008 | Reimbursement Request: Invoice Link, Acknowledgment, and Submission | approved | not included in this package |
| 009 | Payment Authorization | approved | not included in this package |
| 010 | "Paid" Write-off | approved | not included in this package |
| 011 | Notifications, Audit, and Dashboard | approved | not included in this package |
| 012 | RODe Issuance and Signature by the Class Entity | approved | not included in this package |
| 013 | Reference Prices and Reimbursement Calculation Basis | draft | not included in this package |
| 014 | Price Presentation Update | draft | not included in this package |
| 015 | Reimbursement Analysis | approved | not included in this package |
| 016 | Appeal of the Denial: Correction and Reanalysis | approved | not included in this package |
| 017 | Superintendence (SFPA) Query by UF | approved | not included in this package |

Feature IDs come from the issue tracker. In this replication package they were renumbered from
001 to 017, and only the specification of feature 001 is included.
