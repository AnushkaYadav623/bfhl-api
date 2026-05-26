# Deploy BFHL API on Render

Your API details are already set in `src/main/resources/application.properties`.

| Field | Value |
|-------|--------|
| user_id | `anushka_yadav_18022006` |
| email | anushkayadav230422@acropolis.in |
| roll_number | 0827IT231027 |

**Endpoint:** `POST https://YOUR-SERVICE.onrender.com/bfhl`

---

## Step 1 — Push code to GitHub

Open PowerShell:

```powershell
cd C:\Users\yadav\bfhl-api
git init
git add .
git commit -m "BFHL Spring Boot API for Render"
git branch -M main
```

Create a new repo on GitHub (e.g. `bfhl-api`), then:

```powershell
git remote add origin https://github.com/YOUR_GITHUB_USERNAME/bfhl-api.git
git push -u origin main
```

---

## Step 2 — Create Web Service on Render

### Option A — Blueprint (easiest)

1. Go to [dashboard.render.com/blueprints](https://dashboard.render.com/blueprints)
2. **New Blueprint Instance**
3. Connect GitHub and select your `bfhl-api` repo
4. Render reads `render.yaml` and creates the service
5. Click **Apply**

### Option B — Manual

1. [dashboard.render.com](https://dashboard.render.com) → **New +** → **Web Service**
2. Connect the GitHub repo
3. Settings:
   - **Name:** `bfhl-api`
   - **Runtime:** Docker
   - **Dockerfile path:** `./Dockerfile`
   - **Instance type:** Free
   - **Health check path:** `/`
4. **Create Web Service**

First deploy takes ~5–10 minutes (Maven build in Docker).

---

## Step 3 — Test after deploy

Replace `YOUR-SERVICE` with your Render URL (e.g. `bfhl-api.onrender.com`):

```powershell
curl -X POST https://YOUR-SERVICE.onrender.com/bfhl `
  -H "Content-Type: application/json" `
  -d "{\"data\":[\"a\",\"1\",\"334\",\"4\",\"R\",\"$\"]}"
```

Expected: `"user_id":"anushka_yadav_18022006"`, `"sum":"339"`, `"concat_string":"Ra"`.

---

## Step 4 — Submit the form

Submit this URL (must end with `/bfhl`):

`https://YOUR-SERVICE.onrender.com/bfhl`

Form: https://forms.office.com/Pages/ResponsePage.aspx?id=2Dun3U_E-EKuNQToPV5QN-bx0Kms87ROjwsfjbLUdF1UOVQ5M0xQTzExQ1k0QThMOFpQSzZBMVpESy4u

---

## Notes

- **Free tier:** Service sleeps after ~15 min idle; first request may take 30–60 seconds to wake.
- **Logs:** Render dashboard → your service → **Logs**
- **Redeploy:** Push to `main` on GitHub; Render auto-deploys if enabled
