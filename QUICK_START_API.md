# 🚀 Quick Start Guide - Backend API

This guide helps you quickly set up and start using the Trip-MateV2 Backend API.

## ✅ Prerequisites

- Java 17+
- Maven 3.6+
- Neon Database account (or PostgreSQL)
- Google OAuth2 credentials
- GitHub OAuth2 credentials

## 📋 Setup Steps

### 1. Clone and Configure

```bash
# Clone the repository
git clone https://github.com/iamchirag06/Trip-MateV2.git
cd Trip-MateV2

# Copy environment template
cp .env.example .env
```

### 2. Set Up Neon Database

1. Go to [neon.tech](https://neon.tech) and create an account
2. Create a new project
3. Copy your connection string (looks like):
   ```
   postgresql://username:password@ep-xxx.neon.tech/neondb?sslmode=require
   ```
4. Update `.env`:
   ```bash
   SPRING_DATASOURCE_URL=jdbc:postgresql://ep-xxx-pooler.neon.tech/neondb?sslmode=require
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   ```

### 3. Configure OAuth2

#### Google OAuth2
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create project → Enable Google+ API
3. Create OAuth 2.0 credentials
4. Set redirect URI: `http://localhost:9090/login/oauth2/code/google`
5. Copy Client ID and Secret to `.env`:
   ```bash
   GOOGLE_CLIENT_ID=your_google_client_id
   GOOGLE_CLIENT_SECRET=your_google_client_secret
   ```

#### GitHub OAuth2
1. Go to [GitHub Developer Settings](https://github.com/settings/developers)
2. Create New OAuth App
3. Set callback URL: `http://localhost:9090/login/oauth2/code/github`
4. Copy Client ID and Secret to `.env`:
   ```bash
   GITHUB_CLIENT_ID=your_github_client_id
   GITHUB_CLIENT_SECRET=your_github_client_secret
   ```

### 4. Generate JWT Secret

```bash
# Generate a secure random key (256 bits)
openssl rand -base64 32

# Add to .env
JWT_SECRET=your_generated_secret_key
```

### 5. Run the Application

```bash
# Build and run
mvn spring-boot:run

# Or build JAR and run
mvn clean package
java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
```

The API will be available at: **http://localhost:9090**

## 🧪 Test the API

### 1. Check Health
```bash
curl http://localhost:9090/actuator/health
```

Expected response:
```json
{"status":"UP"}
```

### 2. Test Public Endpoint
```bash
curl http://localhost:9090/api/test/public
```

### 3. Test Authentication

Visit in browser:
```
http://localhost:9090/oauth2/authorization/google
```
or
```
http://localhost:9090/oauth2/authorization/github
```

After authentication, you can access protected endpoints.

### 4. Test Protected Endpoint

```bash
# Get current user (requires authentication)
curl http://localhost:9090/auth/user
```

## 📚 API Documentation

**Quick Reference:** See [ENDPOINTS.md](ENDPOINTS.md) for all 48 endpoints

**Detailed Documentation:** See [API Documentation.md](API%20Documentation.md)

### Main API Groups

1. **Authentication** - `/auth/`, `/oauth2/`
2. **Users** - `/api/users`
3. **Preferences** - `/api/preferences`
4. **Trips** - `/api/trips`
5. **Activities** - `/api/activities`
6. **Trip Activities** - `/api/trip-activities`
7. **Destinations** - `/api/destinations`
8. **Budgets** - `/api/budgets`
9. **Recommendations** - `/api/recommendations`

## 🐳 Docker Deployment

```bash
# Build and run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f

# Stop
docker-compose down
```

## 🚢 Production Deployment

### Important Configuration Changes

1. **CORS Settings** (`SecurityConfig.java`):
   ```java
   // Replace wildcard with your frontend domain
   configuration.setAllowedOriginPatterns(List.of("https://yourfrontend.com"));
   ```

2. **OAuth2 Redirect URLs**:
   - Update in Google Console: `https://yourapi.com/login/oauth2/code/google`
   - Update in GitHub: `https://yourapi.com/login/oauth2/code/github`

3. **Environment Variables**:
   - Update all URLs to production domains
   - Use strong JWT secrets
   - Enable HTTPS

### Deployment Platforms

See [DEPLOYMENT.md](DEPLOYMENT.md) for detailed guides:
- ✅ Render
- ✅ Heroku
- ✅ AWS
- ✅ Google Cloud
- ✅ Azure

## 🔧 Common Issues

### Issue: Database Connection Failed
**Solution:** Check Neon connection string includes `?sslmode=require`

### Issue: OAuth2 Redirect Error
**Solution:** Verify redirect URIs match exactly in OAuth provider console

### Issue: Port 9090 Already in Use
**Solution:** Change port in `application.properties`:
```properties
server.port=8080
```

### Issue: Tests Failing
**Solution:** Run with test profile:
```bash
mvn test -Dspring.profiles.active=test
```

## 📊 API Response Format

### Success Response
```json
{
  "id": 1,
  "name": "Example",
  "status": "active"
}
```

### Error Response
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "path": "/api/users/999"
}
```

## 🔒 Security Best Practices

1. ✅ Never commit `.env` file to Git
2. ✅ Use strong JWT secrets (256+ bits)
3. ✅ Restrict CORS to specific domains in production
4. ✅ Use HTTPS in production
5. ✅ Rotate OAuth2 secrets regularly
6. ✅ Monitor authentication logs

## 📞 Support

- **Documentation:** See `README.md` and `API Documentation.md`
- **Issues:** [GitHub Issues](https://github.com/iamchirag06/Trip-MateV2/issues)
- **Database:** [Neon Docs](https://neon.tech/docs)
- **Deployment:** See `DEPLOYMENT.md`

## ✨ What's Next?

1. Build your frontend application
2. Integrate with the API endpoints
3. Implement authentication flow
4. Deploy to production
5. Monitor and scale

---

**Ready to build something amazing! 🚀**
