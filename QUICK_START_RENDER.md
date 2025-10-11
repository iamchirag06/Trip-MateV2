# 🚀 Quick Start: Deploy to Render

This is a quick reference for deploying Trip-MateV2 to Render.com.

## Prerequisites (5 minutes)

1. **Neon Database** → [neon.tech](https://neon.tech) (Free)
   - Create project
   - Copy connection string (use **pooler** endpoint)

2. **Google OAuth2** → [console.cloud.google.com](https://console.cloud.google.com/)
   - Create OAuth2 credentials
   - Add redirect URI: `https://YOUR-APP.onrender.com/login/oauth2/code/google`

3. **GitHub OAuth2** → [github.com/settings/developers](https://github.com/settings/developers)
   - Create OAuth App
   - Add callback URL: `https://YOUR-APP.onrender.com/login/oauth2/code/github`

## Deploy Steps (2 minutes)

### 1. Create New Web Service

Go to [Render Dashboard](https://dashboard.render.com/) → New + → Web Service

### 2. Connect Repository

- Connect your GitHub account
- Select `iamchirag06/Trip-MateV2` repository

### 3. Configure Service

```
Name: trip-mate-v2
Environment: Java
Region: (choose closest)
Branch: master

Build Command: ./mvnw clean package -DskipTests
Start Command: java -Xmx400m -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
```

### 4. Set Environment Variables

Click "Advanced" → Add Environment Variables:

```bash
# Database (from Neon)
SPRING_DATASOURCE_URL=jdbc:postgresql://ep-xxx-pooler.neon.tech/neondb?sslmode=require
SPRING_DATASOURCE_USERNAME=neondb_owner
SPRING_DATASOURCE_PASSWORD=your_password_here

# Google OAuth2
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret

# GitHub OAuth2
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret

# JWT (generate a strong secret)
JWT_SECRET=your_jwt_secret_minimum_256_bits_random_string

# Landing Page
LANDING_PAGE_URL=https://YOUR-APP.onrender.com/login/oauth2/code/google

# Profile
SPRING_PROFILES_ACTIVE=prod
```

### 5. Deploy!

Click "Create Web Service"

Render will:
1. Clone your repository
2. Run build command
3. Start your application
4. Provide a URL like `https://YOUR-APP.onrender.com`

## Verify Deployment (1 minute)

### Check Health

```bash
curl https://YOUR-APP.onrender.com/actuator/health
```

Expected: `{"status":"UP"}`

### View Logs

Dashboard → Your Service → Logs

Look for: `Started TripMateV2Application`

### Test Login

Visit: `https://YOUR-APP.onrender.com`

Try Google/GitHub login

## Common Issues

### Build Fails?
- Check logs for errors
- Verify Java 17 is being used
- Ensure Maven wrapper is executable

### Application Won't Start?
- Verify all environment variables are set
- Check database connection string
- Review application logs

### OAuth2 Not Working?
- Update redirect URIs in Google/GitHub
- Match `LANDING_PAGE_URL` to actual Render URL
- Restart service after updating

## Auto-Deploy

Enable auto-deploy to deploy on every push:

Settings → Auto-Deploy → Enable for `master` branch

## Monitoring

- **Logs**: Real-time in dashboard
- **Metrics**: CPU, memory, network
- **Health Checks**: Automatic monitoring
- **Alerts**: Email on service issues

## Upgrade from Free Tier

Free tier includes:
- 512MB RAM
- Shared CPU
- Auto-sleep after inactivity

To upgrade:
Settings → Instance Type → Select paid plan

## Need Help?

- 📖 [Full Deployment Guide](./docs/RENDER_DEPLOYMENT.md)
- 📊 [Testing Report](./BACKEND_TESTING_REPORT.md)
- 🐛 [GitHub Issues](https://github.com/iamchirag06/Trip-MateV2/issues)
- 💬 [Render Community](https://community.render.com/)

---

**Total Setup Time**: ~8 minutes  
**Difficulty**: Easy  
**Cost**: Free tier available
