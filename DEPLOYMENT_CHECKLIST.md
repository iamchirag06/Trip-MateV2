# Deployment Checklist

Use this checklist to ensure a successful deployment of Trip-MateV2.

## Pre-Deployment

### ✅ Environment Setup
- [ ] Java 17+ installed (for local development)
- [ ] Docker installed (for containerized deployment)
- [ ] PostgreSQL 12+ installed/available (or use Docker Compose)
- [ ] Git installed
- [ ] Maven 3.6+ installed (or use included Maven wrapper)

### ✅ OAuth2 Configuration
- [ ] Google OAuth2 credentials obtained
  - [ ] Client ID configured
  - [ ] Client Secret configured
  - [ ] Redirect URI added: `https://yourdomain.com/login/oauth2/code/google`
- [ ] GitHub OAuth2 credentials obtained
  - [ ] Client ID configured
  - [ ] Client Secret configured
  - [ ] Redirect URI added: `https://yourdomain.com/login/oauth2/code/github`

### ✅ Database Setup
- [ ] PostgreSQL database created
- [ ] Database user created with appropriate permissions
- [ ] Connection string prepared
- [ ] Database credentials secured

### ✅ Environment Variables
- [ ] Copy `.env.example` to `.env`
- [ ] Configure `SPRING_DATASOURCE_URL`
- [ ] Configure `SPRING_DATASOURCE_USERNAME`
- [ ] Configure `SPRING_DATASOURCE_PASSWORD`
- [ ] Configure `GOOGLE_CLIENT_ID`
- [ ] Configure `GOOGLE_CLIENT_SECRET`
- [ ] Configure `GITHUB_CLIENT_ID`
- [ ] Configure `GITHUB_CLIENT_SECRET`
- [ ] Configure `JWT_SECRET` (minimum 256 bits)
- [ ] Configure `LANDING_PAGE_URL`

## Deployment Method Selection

Choose one deployment method:

### Option 1: Docker Compose (Recommended for Quick Start)
- [ ] Docker and Docker Compose installed
- [ ] `.env` file configured
- [ ] Run `docker compose up -d`
- [ ] Verify application: `http://localhost:9090`
- [ ] Check logs: `docker compose logs -f`

### Option 2: Heroku
- [ ] Heroku CLI installed
- [ ] Heroku account created
- [ ] App created: `heroku create your-app-name`
- [ ] PostgreSQL add-on: `heroku addons:create heroku-postgresql`
- [ ] Environment variables set via `heroku config:set`
- [ ] Deploy: `git push heroku master`
- [ ] Open app: `heroku open`

### Option 3: AWS Elastic Beanstalk
- [ ] AWS CLI installed
- [ ] EB CLI installed: `pip install awsebcli`
- [ ] Initialized: `eb init -p docker trip-mate`
- [ ] Environment created: `eb create trip-mate-env`
- [ ] Environment variables configured: `eb setenv`
- [ ] Deployed: `eb deploy`

### Option 4: Google Cloud Run
- [ ] gcloud CLI installed
- [ ] GCP project created
- [ ] Deploy: `gcloud run deploy trip-mate --source .`
- [ ] Environment variables configured via console or CLI

### Option 5: Azure Container Instances
- [ ] Azure CLI installed
- [ ] Resource group created
- [ ] Container registry created
- [ ] Image built and pushed to ACR
- [ ] Container instance deployed

### Option 6: Local/VPS Deployment
- [ ] Server with Java 17+ and PostgreSQL
- [ ] Application built: `./mvnw clean package -DskipTests`
- [ ] JAR copied to server
- [ ] Environment variables configured
- [ ] Service configured (systemd/supervisor)
- [ ] Reverse proxy configured (nginx/Apache)

## Post-Deployment

### ✅ Verification
- [ ] Application starts without errors
- [ ] Health check endpoint accessible: `/actuator/health`
- [ ] Login page accessible: `/login`
- [ ] Google OAuth login works
- [ ] GitHub OAuth login works
- [ ] Database connection successful
- [ ] API endpoints accessible (test with `/api/test/public`)

### ✅ Security
- [ ] HTTPS configured (production only)
- [ ] Environment variables secured (not in code)
- [ ] Database credentials rotated if needed
- [ ] OAuth redirect URIs updated to production URLs
- [ ] JWT secret is strong and unique
- [ ] Application running as non-root user (Docker)

### ✅ Monitoring
- [ ] Application logs accessible
- [ ] Health monitoring configured
- [ ] Error tracking set up (optional: Sentry, etc.)
- [ ] Performance monitoring configured (optional)

### ✅ Backup & Recovery
- [ ] Database backup strategy in place
- [ ] Backup tested
- [ ] Recovery procedure documented

### ✅ CI/CD (Optional)
- [ ] GitHub repository configured
- [ ] GitHub Actions workflow enabled
- [ ] Docker Hub credentials added to GitHub Secrets
- [ ] Automated builds working
- [ ] Automated deployments configured

## Rollback Plan

In case of deployment failure:

- [ ] Previous version JAR/image saved
- [ ] Rollback procedure documented
- [ ] Database migration rollback plan
- [ ] Team notified of deployment window

## Documentation

- [ ] Deployment documented
- [ ] Environment variables documented
- [ ] Known issues documented
- [ ] Support contacts listed

## Final Checks

- [ ] Application version verified
- [ ] All tests passing (or known issues documented)
- [ ] No sensitive data in logs
- [ ] Performance acceptable
- [ ] All stakeholders notified

---

## Environment-Specific Notes

### Development
```bash
# Set profile
export SPRING_PROFILES_ACTIVE=dev
./mvnw spring-boot:run
```

### Production
```bash
# Set profile
export SPRING_PROFILES_ACTIVE=prod
java -jar Trip-MateV2-0.0.1-SNAPSHOT.jar
```

### Docker
```bash
# Set profile in docker-compose.yml
environment:
  - SPRING_PROFILES_ACTIVE=prod
```

---

**Remember**: Always test in a staging environment before deploying to production!
