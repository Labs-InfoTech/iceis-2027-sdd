# Architecture guidelines — Front end

Every new implementation must follow the patterns already present in the code.

## General principles

- Follow the existing structure, names, and conventions; adapt to them instead of creating new ones.
- Prefer minimal and objective solutions; avoid unnecessary abstractions and libraries.
- **Reuse before creating:** check `core/` for an existing model, service, or constant.
- Deliver only what is used: no dead code or unused imports.

## Naming

| Artifact | Convention | Example |
| --- | --- | --- |
| File | kebab-case with type suffix | `vessel.service.ts`, `vessel.model.ts` |
| Class | PascalCase with type suffix | `VesselService`, `VesselsComponent` |
| Model | Interface without suffix | `Vessel` |
| Selector | `app-` prefix, kebab-case | `app-vessels` |
| Route | Plural, kebab-case | `/class-entities` |

## Services (`core/services/`)

- `@Injectable({ providedIn: 'root' })`, `HttpClient` injected in the constructor.
- One service per back-end resource, with the base URL built from `environment.apiUrl`.
- Methods return `Observable` and follow the CRUD of the back end: `findAll`, `findById`,
  `create`, `update`, `delete`.

## Models (`core/models/`)

- Interfaces that mirror the DTOs of the back end; `id` is optional.

## Pages (`pages/`)

- Standalone components with the template in a separate `.html` file (`templateUrl`) and
  `FormsModule` (`ngModel`).
- One page per resource, with the form above the table.
- Built-in control flow (`@for`, `@if`).
- After saving or deleting, reload the list from the API.

## Shared constants

- Brazilian states come from `STATES` in `core/constants/states.ts`; do not repeat the list.

## Tests

- Each service has a `.spec.ts` using `provideHttpClient` and `provideHttpClientTesting`.
- Each test of a new feature references the business rule or acceptance criterion it checks
  (`BR-XX` / `AC-XX`) in its description.

## Existing components to reuse

| Component | Use |
| --- | --- |
| `STATES` | List of the 27 UF codes, for selects and tabs per state |
| Page pattern (form + table) | Layout of new list pages |
| Service pattern | Access to a new back-end resource |
