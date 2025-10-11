# Deploying Trip-MateV2 on Render Using Docker

This guide provides step-by-step instructions for deploying Trip-MateV2 on Render.com using Docker.

## Prerequisites

1. A Render.com account
2. A PostgreSQL database (e.g., Neon.tech)
3. Google and GitHub OAuth2 credentials
4. Your code pushed to a GitHub repository

## Step 1: Prepare Your Database

1. Create a PostgreSQL database
2. Get your connection string
3. Ensure it includes `?sslmode=require` if using Neon

## Step 2: Set Up OAuth2 Credentials

### Google OAuth2

1. Configure redirect URI: `https://your-app-name.onrender.com/login/oauth2/code/google`
2. Get Client ID and Client Secret

### GitHub OAuth2

1. Configure callback URL: `https://your-app-name.onrender.com/login/oauth2/code/github`
2. Get Client ID and Client Secret

## Step 3: Deploy on Render

### Option 1: Deploy from Dashboard (Recommended)

1. Log in to [Render Dashboard](https://dashboard.render.com/)
2. Click "New" → "Web Service"
3. Connect your GitHub repository
4. Configure:
   - **Name**: trip-mate-v2
   - **Environment**: Docker
   - **Docker Command**: (leave empty - uses Dockerfile.render)
   - **Branch**: master (or your main branch)
5. Add Environment Variables:

   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host/your-db-name?sslmode=require
   SPRING_DATASOURCE_USERNAME=your_db_user
   SPRING_DATASOURCE_PASSWORD=your_db_password
   GOOGLE_CLIENT_ID=your_google_client_id
   GOOGLE_CLIENT_SECRET=your_google_client_secret
   GITHUB_CLIENT_ID=your_github_client_id
   GITHUB_CLIENT_SECRET=your_github_client_secret
   JWT_SECRET=your_secure_jwt_secret
   LANDING_PAGE_URL=https://your-app-name.onrender.com/login/oauth2/code/google
   SPRING_PROFILES_ACTIVE=prod
   ```

6. Click "Create Web Service"

### Option 2: Deploy using Render CLI

1. Install Render CLI: `npm install -g @render/cli`
2. Login: `render login`
3. Deploy: `render deploy --yaml render.yaml`

## Step 4: Verify Deployment

1. Wait for build to complete (check logs in dashboard)
2. Test the application at your Render URL
3. Test health endpoint: `https://your-app-name.onrender.com/actuator/health`

## Step 5: Post-Deployment

1. Configure a custom domain (optional)
2. Set up auto-deploys from your repository
3. Configure monitoring and alerts

## Troubleshooting

### Build Fails

- Check Render logs
- Ensure buildspec.yml is correct
- Verify Dockerfile.render

### Application Won't Start

- Check environment variables
- Verify database connection
- Ensure OAuth2 credentials are correct

### OAuth2 Errors

- Check redirect URIs match your Render URL
- Update LANDING_PAGE_URL to match Render URL

## Monitoring and Management

- View logs in Render dashboard
- Check metrics for CPU and memory usage
- Set up alerts for service outages

---

---

For more details, see the [Render documentation](https://render.com/docs) or [Trip-MateV2 documentation](./docs/RENDER_DEPLOYMENT.md)

