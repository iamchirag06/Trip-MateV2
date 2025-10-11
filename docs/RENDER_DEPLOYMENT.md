# Deploying to Render.com

This guide explains how to deploy Trip-MateV2 to Render.com.

## Prerequisites

1. A Render.com account
2. A Neon PostgreSQL database (free tier available at neon.tech)
3. Google OAuth2 credentials
4. GitHub OAuth2 credentials

## Step-by-Step Deployment

### 1. Prepare Your Database (Neon)

1. Sign up at [neon.tech](https://neon.tech)
2. Create a new project
3. Get your connection string from the dashboard
   - Use the **pooler endpoint** for production (ends with `-pooler`)
   - Ensure it includes `?sslmode=require`

### 2. Set Up OAuth2 Credentials

#### Google OAuth2
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or use existing
3. Enable Google+ API
4. Create OAuth2 credentials
5. Add authorized redirect URI: `https://your-app-name.onrender.com/login/oauth2/code/google`

#### GitHub OAuth2
1. Go to [GitHub Developer Settings](https://github.com/settings/developers)
2. Create a new OAuth App
3. Set Homepage URL: `https://your-app-name.onrender.com`
4. Set Authorization callback URL: `https://your-app-name.onrender.com/login/oauth2/code/github`

### 3. Deploy to Render

#### Option A: Deploy from GitHub (Recommended)

1. **Connect Repository**
   - Go to [Render Dashboard](https://dashboard.render.com/)
   - Click "New +" → "Web Service"
   - Connect your GitHub repository

2. **Configure Build Settings**
   ```
   Name: trip-mate-v2
   Environment: Docker
   Region: Choose closest to you
   Branch: master
   Build Command: (leave empty - uses Dockerfile)
   ```

3. **Set Environment Variables**
   
   Go to "Environment" tab and add:
   
   ```
   SPRING_DATASOURCE_URL=jdbc:postgresql://ep-xxx-pooler.neon.tech/neondb?sslmode=require
   SPRING_DATASOURCE_USERNAME=neondb_owner
   SPRING_DATASOURCE_PASSWORD=your_neon_password
   
   GOOGLE_CLIENT_ID=your_google_client_id
   GOOGLE_CLIENT_SECRET=your_google_client_secret
   
   GITHUB_CLIENT_ID=your_github_client_id
   GITHUB_CLIENT_SECRET=your_github_client_secret
   
   JWT_SECRET=your_jwt_secret_minimum_256_bits
   
   LANDING_PAGE_URL=https://your-app-name.onrender.com/login/oauth2/code/google
   
   SPRING_PROFILES_ACTIVE=prod
   ```

4. **Deploy**
   - Click "Create Web Service"
   - Render will automatically build and deploy your application

#### Option B: Manual Deploy with Pre-built JAR

If Docker build fails on Render, you can use this approach:

1. **Build Locally**
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Create a Simple Dockerfile**
   Use `Dockerfile.render` which expects a pre-built JAR

3. **Use Render's Native Java Support**
   Instead of Docker, you can use Render's native Java support:
   
   ```
   Environment: Java
   Build Command: ./mvnw clean package -DskipTests
   Start Command: java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
   ```

### 4. Configure Auto-Deploy

Render can automatically deploy when you push to your repository:

1. Go to your service settings
2. Enable "Auto-Deploy" for your branch
3. Now every push to master will trigger a new deployment

### 5. Verify Deployment

1. **Check Build Logs**
   - Go to your service dashboard
   - Click on "Logs" tab
   - Verify the build completed successfully

2. **Test Health Endpoint**
   ```bash
   curl https://your-app-name.onrender.com/actuator/health
   ```
   
   Should return:
   ```json
   {"status":"UP"}
   ```

3. **Test Application**
   - Visit: `https://your-app-name.onrender.com`
   - Try OAuth2 login with Google/GitHub

## Troubleshooting

### Issue: Build Fails with "PKIX path building failed"

This is a known issue with Docker builds in some environments.

**Solution**: Use Option B (Native Java Support) instead of Docker.

### Issue: Application Won't Start

**Check**:
1. Environment variables are set correctly
2. Database connection string is valid and includes `sslmode=require`
3. OAuth2 redirect URIs match your Render URL
4. JWT secret is at least 256 bits

**View Logs**:
```
Render Dashboard → Your Service → Logs
```

### Issue: OAuth2 Redirect Fails

**Solution**:
1. Update OAuth2 redirect URIs in Google/GitHub to match Render URL
2. Update `LANDING_PAGE_URL` environment variable
3. Restart the service

### Issue: Database Connection Timeout

**Solution**:
1. Verify Neon database is active
2. Use the **pooler** endpoint from Neon
3. Ensure `sslmode=require` is in the connection URL
4. Check Neon console for connection limits

### Issue: High Memory Usage

**Solution**:
Render free tier has 512MB RAM. Configure Java heap:

Add to environment variables:
```
JAVA_OPTS=-Xmx400m -Xms200m
```

Update start command:
```
java $JAVA_OPTS -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
```

## Production Checklist

Before going live:

- [ ] All environment variables set
- [ ] Database connection tested
- [ ] OAuth2 credentials configured for production URLs
- [ ] JWT secret is strong (256+ bits)
- [ ] Health endpoint responding
- [ ] Application logs showing no errors
- [ ] HTTPS enabled (automatic on Render)
- [ ] Custom domain configured (optional)
- [ ] Monitoring set up in Render dashboard
- [ ] Backup strategy for database

## Monitoring

Render provides:
- Real-time logs
- Metrics dashboard
- Health check monitoring
- Automatic HTTPS
- DDoS protection

Access via Render Dashboard → Your Service → Metrics/Logs

## Scaling

To upgrade from free tier:
1. Go to your service settings
2. Choose a paid plan for better performance
3. Configure auto-scaling if needed

## Support

- **Render Issues**: [Render Community](https://community.render.com/)
- **Application Issues**: [GitHub Issues](https://github.com/iamchirag06/Trip-MateV2/issues)
- **Neon Database**: [Neon Support](https://neon.tech/docs/introduction/support)

---

**Happy deploying! 🚀**
