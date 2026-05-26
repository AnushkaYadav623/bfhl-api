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

## Deploy to Render

See **[RENDER_DEPLOY.md](./RENDER_DEPLOY.md)** for full steps (GitHub → Render → test → submit form).

Quick: push repo to GitHub → Render **New Blueprint** → select repo → deploy → use `https://YOUR-APP.onrender.com/bfhl`.

