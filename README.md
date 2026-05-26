# BFHL REST API

Spring Boot API for the Bajaj Finserv / BFHL assignment.

## Your details (configured)

| Field | Value |
|-------|-------|
| Name | Anushka Yadav |
| User ID | `anushka_yadav_18022006` |
| Email | anushkayadav230422@acropolis.in |
| Roll number | 0827IT231027 |
| DOB | 18/02/2006 |

## Endpoint

- **POST** `/bfhl`
- Body: `{ "data": ["a", "1", "334"] }`

## Run locally

```bash
cd C:\Users\yadav\bfhl-api
mvn spring-boot:run
```

Test:

```bash
curl -X POST http://localhost:8080/bfhl -H "Content-Type: application/json" -d "{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}"
```

## Tests

```bash
mvn test
```

## Deploy to Render (recommended)

See **[RENDER_DEPLOY.md](./RENDER_DEPLOY.md)** for full steps (GitHub → Render → test → submit form).

Quick: push repo to GitHub → Render **New Blueprint** → select repo → deploy → use `https://YOUR-APP.onrender.com/bfhl`.

## Deploy to Vercel (alternative)

Vercel runs the serverless function in `api/bfhl.ts` (same logic as Spring Boot). Your Java code in `src/` is kept for the assignment (DTOs, tests, service layer).

```bash
cd C:\Users\yadav\bfhl-api
npm install
npx vercel login
npx vercel --prod
```

Submit: `https://YOUR-PROJECT.vercel.app/bfhl`

## Deploy to Railway

1. Push this folder to a **new GitHub repository**.
2. Sign in at [railway.app](https://railway.app) → **New Project** → **Deploy from GitHub repo**.
3. Select the repo; Railway detects the `Dockerfile`.
4. After deploy, open **Settings → Networking → Generate Domain**.
5. Submit: `https://YOUR-DOMAIN.up.railway.app/bfhl` in the [submission form](https://forms.office.com/Pages/ResponsePage.aspx?id=2Dun3U_E-EKuNQToPV5QN-bx0Kms87ROjwsfjbLUdF1UOVQ5M0xQTzExQ1k0QThMOFpQSzZBMVpESy4u).

## Deploy to Render (alternative)

1. Push to GitHub.
2. [render.com](https://render.com) → **New Web Service** → connect repo.
3. Environment: **Docker**, or Native with build `mvn -DskipTests package` and start `java -jar target/bfhl-api-1.0.0.jar`.
4. Set `PORT` (Render sets it automatically).
