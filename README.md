## Franchise Platform – Clean, Extensible Starter

This repo is a **modular monolith** implementation of your franchise platform:

- **Frontend**: React + JSX (Vite), clean routing and multi-step flow
- **Backend**: Spring Boot (Java 17+), modular packages, rule/AI-ready design

### Structure

- **backend**: Spring Boot app
  - `com.platform.common`: shared base classes (e.g. `BaseEntity`)
  - `com.platform.auth`: signup/login endpoints and DTOs
  - `com.platform.user`: `User` entity, roles, controllers
  - Future modules (empty for now, add as packages):
    - `com.platform.franchise`
    - `com.platform.location`
    - `com.platform.scoring`
    - `com.platform.matching`
    - `com.platform.subscription`
    - `com.platform.admin`
- **frontend**: React app
  - `src/components`: layout, shared UI
  - `src/pages`: `HomePage`, `SignupPage`, `RoleSelectionPage`, `DashboardPage`
  - `src/services`: API client (`apiClient.js`)

### Flows Implemented

- **Signup (no role yet)**:
  - Endpoint: `POST /api/auth/signup`
  - Frontend: `SignupPage` – collects name, phone, email, country, city
- **Role selection**:
  - Endpoint: `PUT /api/users/{userId}/roles`
  - Frontend: `RoleSelectionPage` – large cards for:
    - Shop owner
    - Individual
    - Landowner
    - Local brand wanting to franchise

### How to Extend Safely

- **New frontend UI/UX**:
  - Keep `services/apiClient.js` contracts the same
  - Replace everything in `src/components` and `src/pages` freely
- **New backend features**:
  - Add new packages under `com.platform.<module>`
  - Keep controllers thin, push logic into services
  - Add entities extending `BaseEntity` for UUID + timestamps

### Run Locally

Backend:

```bash
cd backend
mvn spring-boot:run
```

Frontend:

```bash
cd frontend
npm install
npm run dev
```

Then open the frontend dev URL shown in the terminal (defaults to `http://localhost:5173`).

