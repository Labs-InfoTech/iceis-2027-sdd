# Project context — Front end

> Synthetic code base of the replication package. It is a reduced, fictitious version of the
> front end of the case and does not reproduce the original system.

## Overview

Web client of the diesel oil subsidy module. It offers one page per resource of the back end
(class entities, vessels, and suppliers), each with a list and a form to create, edit, and
delete items.

## Technologies

- Angular 17 with standalone components
- TypeScript 5
- RxJS
- Jasmine and Karma for tests

## Architecture

Pages call services; services call the back-end API through `HttpClient`. Models are TypeScript
interfaces that mirror the DTOs of the back end. The API URL is set in
`src/environments/environment.ts`.

## Project structure

```
src/app/
├── app.component.*     navigation and router outlet
├── app.config.ts        providers (router, HttpClient)
├── app.routes.ts        one route per page
├── core/
│   ├── constants/       shared constants (STATES: the 27 UF codes)
│   ├── models/          <resource>.model.ts
│   └── services/        <resource>.service.ts and its .spec.ts
└── pages/
    └── <resource>/      <resource>.component.ts and .html (list and form)
```

## Pages

| Route | Component | Back-end path |
| --- | --- | --- |
| `/class-entities` | `ClassEntitiesComponent` | `/class-entities` |
| `/vessels` | `VesselsComponent` | `/vessels` |
| `/suppliers` | `SuppliersComponent` | `/suppliers` |

## Integrations

Only the back-end API of this package.

## Build and test

```
npm install
npm test
```
