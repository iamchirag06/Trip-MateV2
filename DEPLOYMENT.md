# 🚀 Deployment Guide - Trip-MateV2

This guide provides comprehensive instructions for deploying the Trip-MateV2 application across various environments.

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Environment Variables](#environment-variables)
- [Local Development](#local-development)
- [Docker Deployment](#docker-deployment)
- [Cloud Deployment](#cloud-deployment)
  - [Heroku](#heroku-deployment)
  - [AWS](#aws-deployment)
  - [Google Cloud Platform](#google-cloud-platform-deployment)
  - [Azure](#azure-deployment)
- [CI/CD Pipeline](#cicd-pipeline)
- [Database Setup](#database-setup)
- [OAuth2 Configuration](#oauth2-configuration)
- [Troubleshooting](#troubleshooting)

## Prerequisites

Before deploying, ensure you have:

- ✅ Java JDK 17 or higher
- ✅ Maven 3.6 or higher (or use the included Maven wrapper)
- ✅ Docker and Docker Compose (for containerized deployment)
- ✅ [Neon Database](https://neon.tech) account (recommended for production)
- ✅ Git
- ✅ OAuth2 credentials from Google and GitHub

## Environment Variables

Copy the `.env.example` file to `.env` and configure the following variables:

```bash
# Copy the example file
cp .env.example .env
```

### Required Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | Database connection URL (Neon DB recommended) | `jdbc:postgresql://ep-xxx.neon.tech/neondb?sslmode=require` |
| `SPRING_DATASOURCE_USERNAME` | Database username | `neondb_owner` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `your_secure_password` |
| `GOOGLE_CLIENT_ID` | Google OAuth2 Client ID | `123456789-xxx.apps.googleusercontent.com` |
| `GOOGLE_CLIENT_SECRET` | Google OAuth2 Client Secret | `GOCSPX-xxxxxxxxxxxx` |
| `GITHUB_CLIENT_ID` | GitHub OAuth2 Client ID | `Iv1.xxxxxxxxxxxx` |
| `GITHUB_CLIENT_SECRET` | GitHub OAuth2 Client Secret | `xxxxxxxxxxxxxxxx` |
| `JWT_SECRET` | JWT signing secret (min 256 bits) | `your_jwt_secret_key` |
| `LANDING_PAGE_URL` | OAuth redirect URL | `https://yourdomain.com/login/oauth2/code/google` |

## Local Development

### Option 1: Run with Maven

```bash
# Install dependencies and run
./mvnw clean install
./mvnw spring-boot:run
```

The application will be available at `http://localhost:9090`

### Option 2: Run with JAR

```bash
# Build the JAR
./mvnw clean package

# Run the JAR
java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
```

## Docker Deployment

### Build and Run with Docker Compose

The easiest way to deploy with a local database for development:

```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop services
docker-compose down

# Stop and remove volumes (WARNING: destroys data)
docker-compose down -v
```

**Note:** For production deployments, use Neon Database instead of the local database container. See the [Database Setup](#database-setup) section below.

### Build Docker Image Manually

```bash
# Build the image
docker build -t trip-mate:latest .

# Run the container (with Neon DB connection)
docker run -d \
  -p 9090:9090 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://ep-xxx.neon.tech/neondb?sslmode=require \
  -e SPRING_DATASOURCE_USERNAME=neondb_owner \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  -e GOOGLE_CLIENT_ID=your_google_client_id \
  -e GOOGLE_CLIENT_SECRET=your_google_secret \
  -e GITHUB_CLIENT_ID=your_github_client_id \
  -e GITHUB_CLIENT_SECRET=your_github_secret \
  -e JWT_SECRET=your_jwt_secret \
  --name trip-mate \
  trip-mate:latest
```

### Docker Image Optimization

The Dockerfile uses multi-stage builds to minimize image size:
- **Build stage**: Uses Maven to compile the application
- **Runtime stage**: Uses a minimal JRE image with only the JAR file
- **Security**: Runs as non-root user
- **Health checks**: Built-in health monitoring

## Cloud Deployment

### Heroku Deployment

1. **Install Heroku CLI**
   ```bash
   curl https://cli-assets.heroku.com/install.sh | sh
   ```

2. **Login to Heroku**
   ```bash
   heroku login
   ```

3. **Create Heroku App**
   ```bash
   heroku create your-app-name
   ```

4. **Configure Database**
   ```bash
   # Option 1: Use Neon Database (Recommended)
   # Get your Neon connection string from https://neon.tech
   heroku config:set SPRING_DATASOURCE_URL="jdbc:postgresql://ep-xxx.neon.tech/neondb?sslmode=require"
   heroku config:set SPRING_DATASOURCE_USERNAME=neondb_owner
   heroku config:set SPRING_DATASOURCE_PASSWORD=your_neon_password
   
   # Option 2: Use Heroku Postgres Add-on
   # heroku addons:create heroku-postgres:essential-0
   ```

5. **Set Environment Variables**
   ```bash
   heroku config:set GOOGLE_CLIENT_ID=your_client_id
   heroku config:set GOOGLE_CLIENT_SECRET=your_client_secret
   heroku config:set GITHUB_CLIENT_ID=your_github_client_id
   heroku config:set GITHUB_CLIENT_SECRET=your_github_secret
   heroku config:set JWT_SECRET=your_jwt_secret
   heroku config:set LANDING_PAGE_URL=https://your-app-name.herokuapp.com/login/oauth2/code/google
   ```

6. **Deploy**
   ```bash
   git push heroku master
   ```

7. **Scale the App**
   ```bash
   heroku ps:scale web=1
   ```

8. **View Logs**
   ```bash
   heroku logs --tail
   ```

### AWS Deployment

#### Option 1: AWS Elastic Beanstalk

1. **Install EB CLI**
   ```bash
   pip install awsebcli
   ```

2. **Initialize EB**
   ```bash
   eb init -p docker trip-mate
   ```

3. **Create Environment**
   ```bash
   eb create trip-mate-env
   ```

4. **Set Environment Variables**
   ```bash
   eb setenv \
     GOOGLE_CLIENT_ID=your_client_id \
     GOOGLE_CLIENT_SECRET=your_secret \
     GITHUB_CLIENT_ID=your_github_id \
     GITHUB_CLIENT_SECRET=your_github_secret \
     JWT_SECRET=your_jwt_secret
   ```

5. **Deploy**
   ```bash
   eb deploy
   ```

#### Option 2: AWS ECS (Elastic Container Service)

1. Build and push to ECR:
   ```bash
   aws ecr create-repository --repository-name trip-mate
   docker build -t trip-mate .
   docker tag trip-mate:latest <account-id>.dkr.ecr.<region>.amazonaws.com/trip-mate:latest
   docker push <account-id>.dkr.ecr.<region>.amazonaws.com/trip-mate:latest
   ```

2. Create ECS task definition and service using AWS Console or CLI

### Google Cloud Platform Deployment

1. **Install gcloud CLI**
   ```bash
   curl https://sdk.cloud.google.com | bash
   ```

2. **Initialize gcloud**
   ```bash
   gcloud init
   ```

3. **Build and Deploy to Cloud Run**
   ```bash
   gcloud run deploy trip-mate \
     --source . \
     --platform managed \
     --region us-central1 \
     --allow-unauthenticated \
     --set-env-vars "GOOGLE_CLIENT_ID=your_id,GOOGLE_CLIENT_SECRET=your_secret,GITHUB_CLIENT_ID=your_github_id,GITHUB_CLIENT_SECRET=your_github_secret,JWT_SECRET=your_jwt_secret"
   ```

### Azure Deployment

1. **Install Azure CLI**
   ```bash
   curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash
   ```

2. **Login to Azure**
   ```bash
   az login
   ```

3. **Create Resource Group**
   ```bash
   az group create --name trip-mate-rg --location eastus
   ```

4. **Create Container Registry**
   ```bash
   az acr create --resource-group trip-mate-rg --name tripmateacr --sku Basic
   ```

5. **Build and Push Image**
   ```bash
   az acr build --registry tripmateacr --image trip-mate:latest .
   ```

6. **Deploy to Azure Container Instances**
   ```bash
   az container create \
     --resource-group trip-mate-rg \
     --name trip-mate \
     --image tripmateacr.azurecr.io/trip-mate:latest \
     --dns-name-label trip-mate \
     --ports 9090
   ```

## CI/CD Pipeline

The repository includes a GitHub Actions workflow that automatically:

1. ✅ Builds the application on every push
2. ✅ Runs tests
3. ✅ Creates JAR artifacts
4. ✅ Builds Docker images
5. ✅ Pushes to Docker Hub (on master/main branch)

### Setting up CI/CD

1. **Add GitHub Secrets**
   
   Navigate to `Settings > Secrets and variables > Actions` and add:
   - `DOCKER_USERNAME`: Your Docker Hub username
   - `DOCKER_PASSWORD`: Your Docker Hub password/token

2. **Trigger Workflow**
   
   The workflow runs automatically on:
   - Push to `master` or `main` branch
   - Pull requests to `master` or `main` branch

## Database Setup

### Neon Database (Recommended)

[Neon](https://neon.tech) is a serverless PostgreSQL-compatible database platform that offers:
- ✅ Automatic scaling and connection pooling
- ✅ Built-in SSL security
- ✅ Zero downtime migrations
- ✅ Free tier available
- ✅ Perfect for both development and production deployments
- ✅ No local database installation required

#### Setting up Neon Database

1. **Create a Neon Account**
   - Visit [https://neon.tech](https://neon.tech)
   - Sign up for a free account
   - Create a new project

2. **Get Your Connection String**
   - In your Neon project dashboard, find the connection string
   - It will look like: `postgresql://[user]:[password]@[endpoint].neon.tech/[database]?sslmode=require`

3. **Configure Environment Variables**
   
   Update your `.env` file or set environment variables:
   ```bash
   # Example Neon connection (replace with your actual values)
   export SPRING_DATASOURCE_URL="jdbc:postgresql://ep-fancy-mode-xxx.pooler.c-2.us-east-1.aws.neon.tech/neondb?sslmode=require"
   export SPRING_DATASOURCE_USERNAME="neondb_owner"
   export SPRING_DATASOURCE_PASSWORD="your_neon_password"
   ```

4. **Connection Pooling**
   
   Neon provides connection pooling endpoints. Use the `-pooler` endpoint for best performance:
   ```
   jdbc:postgresql://your-endpoint-pooler.neon.tech/neondb?sslmode=require
   ```

5. **Deploy**
   
   The application is now configured to use Neon. Deploy as usual:
   ```bash
   ./mvnw clean package
   java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
   ```

#### Neon Best Practices

- **Use Connection Pooling**: Always use the `-pooler` endpoint in production
- **SSL is Required**: Neon requires SSL connections (already configured)
- **Environment Variables**: Never commit credentials - use environment variables
- **Backups**: Neon automatically backs up your data
- **Monitoring**: Use Neon's dashboard to monitor database performance

### Local Development Database (Optional)

If you prefer to use a local database for development (not recommended for production):

1. **Use Docker Compose**
   ```bash
   # The included docker-compose.yml provides a local database
   docker-compose up -d
   ```

2. **Or Install Database Locally**
   ```bash
   # Ubuntu/Debian
   sudo apt-get install postgresql postgresql-contrib
   
   # macOS
   brew install postgresql
   ```

2. **Create Database and User**
   ```sql
   CREATE DATABASE tripmate;
   CREATE USER tripmate_user WITH ENCRYPTED PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE tripmate TO tripmate_user;
   ```

3. **Update Connection String**
   ```bash
   export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tripmate
   export SPRING_DATASOURCE_USERNAME=tripmate_user
   export SPRING_DATASOURCE_PASSWORD=your_password
   ```

**Note:** For production deployments, always use Neon Database instead of local database installations.

## OAuth2 Configuration

### Google OAuth2 Setup

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing
3. Enable Google+ API
4. Go to **Credentials** > **Create Credentials** > **OAuth 2.0 Client ID**
5. Configure OAuth consent screen
6. Add authorized redirect URIs:
   - `http://localhost:9090/login/oauth2/code/google` (development)
   - `https://yourdomain.com/login/oauth2/code/google` (production)
7. Copy Client ID and Client Secret

### GitHub OAuth2 Setup

1. Go to [GitHub Developer Settings](https://github.com/settings/developers)
2. Click **New OAuth App**
3. Fill in the application details:
   - **Application name**: Trip-MateV2
   - **Homepage URL**: `http://localhost:9090` or your domain
   - **Authorization callback URL**: `http://localhost:9090/login/oauth2/code/github`
4. Click **Register application**
5. Copy Client ID and generate a Client Secret

## Troubleshooting

### Common Issues

#### 1. Application fails to start

**Problem**: Port 9090 is already in use

**Solution**:
```bash
# Find and kill process using port 9090
lsof -ti:9090 | xargs kill -9

# Or change the port in application.properties
server.port=8080
```

#### 2. Database connection errors

**Problem**: Cannot connect to database

**Solution**:
```bash
# For Neon DB: Verify your connection string and credentials
# Check that sslmode=require is included in the URL

# For local database (development only):
# Check if database is running
sudo systemctl status postgresql

# Start database if not running
sudo systemctl start postgresql

# Verify connection
psql -U tripmate_user -d tripmate
```

#### 3. OAuth2 authentication fails

**Problem**: Redirect URI mismatch

**Solution**:
- Ensure redirect URIs in Google/GitHub match exactly
- Check that `LANDING_PAGE_URL` environment variable is correct
- Verify OAuth credentials are properly set

#### 4. Docker build fails

**Problem**: Out of memory during build

**Solution**:
```bash
# Increase Docker memory limit
# Or build with limited parallelism
docker build --memory=4g -t trip-mate .
```

#### 5. Health check failures

**Problem**: Container health check keeps failing

**Solution**:
```bash
# Check application logs
docker logs trip-mate

# Verify actuator endpoint
curl http://localhost:9090/actuator/health

# Ensure database is accessible (for local database only)
docker exec trip-mate pg_isready -h postgres
```

### Getting Help

- 📖 Check [API Documentation](API%20Documentation.md)
- 🐛 [Report Issues](https://github.com/iamchirag06/Trip-MateV2/issues)
- 💬 [Discussions](https://github.com/iamchirag06/Trip-MateV2/discussions)

### Known Issues

#### Test Database Configuration

The current test suite requires a database connection. Running tests with `-DskipTests` is recommended for CI/CD builds until test database configuration is optimized for H2 in-memory database.

```bash
# Build without tests
./mvnw clean package -DskipTests

# Or use Docker which doesn't run tests
docker build -t trip-mate:latest .
```

## Monitoring and Maintenance

### Health Checks

The application exposes Spring Actuator endpoints:

```bash
# Health status
curl http://localhost:9090/actuator/health

# Application info
curl http://localhost:9090/actuator/info

# Metrics
curl http://localhost:9090/actuator/metrics
```

### Logs

```bash
# Docker logs
docker-compose logs -f app

# Heroku logs
heroku logs --tail

# Local logs
tail -f logs/spring.log
```

### Backup Database

```bash
# For Neon DB: Use Neon's built-in backup features via the dashboard

# For local database (development only):
pg_dump -U tripmate_user tripmate > backup.sql

# Restore
psql -U tripmate_user tripmate < backup.sql
```

---

<div align="center">
Made with ❤️ by <a href="https://github.com/iamchirag06">iamchirag06</a>
</div>
