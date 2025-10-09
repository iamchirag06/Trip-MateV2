# How to Use Your Neon Database Connection

This guide shows you exactly how to configure Trip-MateV2 with your Neon database.

## Your Connection String

You provided this Neon JDBC connection string:
```
jdbc:postgresql://ep-fancy-mode-adfwqkh9-pooler.c-2.us-east-1.aws.neon.tech/neondb?user={username}&password={password}&sslmode=require&channelBinding=require
```

## Breaking Down the Connection String

From your connection string, we extract:
- **Endpoint**: `ep-fancy-mode-adfwqkh9-pooler.c-2.us-east-1.aws.neon.tech`
- **Database**: `neondb`
- **Username**: `DB_username`
- **Password**: `DB_password`
- **SSL Mode**: `require`

Note: The `-pooler` in the endpoint means you're using the connection pooling endpoint, which is perfect for production! ✅

## Configuration Method

### Option 1: Using .env File (Recommended)

1. Copy the `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` and update the database section:
   ```bash
   # Database Configuration
   SPRING_DATASOURCE_URL=SPRING_DATASOURCE_URL
   SPRING_DATASOURCE_USERNAME=your_neon_DB_username
   SPRING_DATASOURCE_PASSWORD=your_neon_DB_Password
   ```

3. Configure OAuth2 and JWT settings (required):
   ```bash
   # OAuth2 Google Configuration
   GOOGLE_CLIENT_ID=your_google_client_id
   GOOGLE_CLIENT_SECRET=your_google_client_secret
   
   # OAuth2 GitHub Configuration
   GITHUB_CLIENT_ID=your_github_client_id
   GITHUB_CLIENT_SECRET=your_github_client_secret
   
   # JWT Configuration
   JWT_SECRET=your_jwt_secret_key_minimum_256_bits
   
   # Landing Page URL
   LANDING_PAGE_URL=http://localhost:9090/login/oauth2/code/google
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Option 2: Using Environment Variables

Set environment variables directly in your terminal:

```bash
export SPRING_DATASOURCE_URL="source_url"
export SPRING_DATASOURCE_USERNAME="username"
export SPRING_DATASOURCE_PASSWORD="password"

# Also set OAuth2 and JWT variables
export GOOGLE_CLIENT_ID="your_google_client_id"
export GOOGLE_CLIENT_SECRET="your_google_client_secret"
export GITHUB_CLIENT_ID="your_github_client_id"
export GITHUB_CLIENT_SECRET="your_github_client_secret"
export JWT_SECRET="your_jwt_secret_key_minimum_256_bits"
export LANDING_PAGE_URL="http://localhost:9090/login/oauth2/code/google"

# Run the application
./mvnw spring-boot:run
```

### Option 3: For Heroku Deployment

```bash
heroku config:set SPRING_DATASOURCE_URL="source_url"
heroku config:set SPRING_DATASOURCE_USERNAME="username"
heroku config:set SPRING_DATASOURCE_PASSWORD="password"

# Also set OAuth2 and JWT variables
heroku config:set GOOGLE_CLIENT_ID="your_google_client_id"
heroku config:set GOOGLE_CLIENT_SECRET="your_google_client_secret"
heroku config:set GITHUB_CLIENT_ID="your_github_client_id"
heroku config:set GITHUB_CLIENT_SECRET="your_github_client_secret"
heroku config:set JWT_SECRET="your_jwt_secret_key"
heroku config:set LANDING_PAGE_URL="https://your-app.herokuapp.com/login/oauth2/code/google"
```

### Option 4: For Docker

```bash
docker run -d \
  -e SPRING_DATASOURCE_URL="source_url" \
  -e SPRING_DATASOURCE_USERNAME="username" \
  -e SPRING_DATASOURCE_PASSWORD="password" \
  -e GOOGLE_CLIENT_ID="your_google_client_id" \
  -e GOOGLE_CLIENT_SECRET="your_google_client_secret" \
  -e GITHUB_CLIENT_ID="your_github_client_id" \
  -e GITHUB_CLIENT_SECRET="your_github_client_secret" \
  -e JWT_SECRET="your_jwt_secret" \
  -e LANDING_PAGE_URL="https://yourdomain.com/login/oauth2/code/google" \
  -p 9090:9090 \
  --name trip-mate \
  trip-mate:latest
```

## Verifying the Connection

After starting the application, check the logs for:

```
Hikari - Starting...
Hikari - Start completed.
```

Or test the health endpoint:
```bash
curl http://localhost:9090/actuator/health
```

Expected response:
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "Neon"
      }
    }
  }
}
```

## What's Already Configured

The application is pre-configured with optimal settings for Neon:

### Connection Pooling (HikariCP)
```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

### SSL Support
The JDBC driver automatically supports SSL connections when `sslmode=require` is in the URL.

### Database Schema
The application will automatically create the necessary tables on first run using Hibernate DDL auto-update.

## Security Best Practices

⚠️ **Important Security Notes:**

1. **Never commit the `.env` file** - It's already in `.gitignore`
2. **Rotate passwords regularly** in the Neon console
3. **Use different credentials** for development and production
4. **Keep SSL mode** at `require` or higher
5. **Use environment variables** in production deployments

## Troubleshooting

### Issue: Connection timeout
**Solution**: Check your network connectivity and ensure the Neon endpoint is reachable.

### Issue: SSL handshake failed
**Solution**: Ensure `sslmode=require` is in your connection URL. The application is already configured to handle SSL.

### Issue: Authentication failed
**Solution**: Verify your username and password are correct. If needed, reset the password in the Neon console.

### Issue: Too many connections
**Solution**: You're already using the `-pooler` endpoint which should handle this. If issues persist, check the Neon console for connection limits.

## Production Checklist

Before deploying to production:

- [ ] Set all environment variables
- [ ] Verify SSL is enabled (`sslmode=require` in URL)
- [ ] Test database connection locally first
- [ ] Set up OAuth2 credentials from Google and GitHub
- [ ] Generate a secure JWT secret (minimum 256 bits)
- [ ] Configure correct landing page URL
- [ ] Test the health endpoint
- [ ] Review Neon connection limits for your plan
- [ ] Set up monitoring in Neon console
- [ ] Configure backup strategy

## Next Steps

1. **Configure OAuth2**: Set up Google and GitHub OAuth2 credentials
   - [Google Cloud Console](https://console.cloud.google.com/)
   - [GitHub Developer Settings](https://github.com/settings/developers)

2. **Review Documentation**:
   - [Neon Setup Guide](./NEON_SETUP.md) - Detailed Neon configuration
   - [Deployment Guide](../DEPLOYMENT.md) - Complete deployment instructions
   - [Quick Reference](./QUICK_REFERENCE.md) - Quick reference guide

3. **Deploy**: Choose your deployment platform
   - Heroku
   - AWS
   - Docker
   - Other cloud platforms

## Support

For issues:
- **Neon Database**: [Neon Support](https://neon.tech/docs/introduction/support)
- **Application**: [GitHub Issues](https://github.com/iamchirag06/Trip-MateV2/issues)

---

**Your database is ready to use! 🚀**
