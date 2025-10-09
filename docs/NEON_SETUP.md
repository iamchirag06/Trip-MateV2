# 🚀 Neon Database Setup Guide

This guide will help you set up Trip-MateV2 with [Neon](https://neon.tech) - a serverless PostgreSQL-compatible database platform.

## Why Neon?

Neon is recommended for production deployments because it offers:

- ✅ **Serverless Architecture**: Automatic scaling based on demand
- ✅ **Connection Pooling**: Built-in pooler for efficient connection management
- ✅ **SSL Security**: Encrypted connections by default
- ✅ **Instant Branching**: Create database branches for testing
- ✅ **Free Tier**: Generous free tier for development and small projects
- ✅ **Zero Downtime**: Automated backups and point-in-time recovery
- ✅ **Fast Deployment**: No server management required

## Prerequisites

- A Neon account (sign up at [neon.tech](https://neon.tech))
- Git clone of this repository
- Java 17+ and Maven installed

## Step-by-Step Setup

### 1. Create a Neon Project

1. Visit [https://console.neon.tech](https://console.neon.tech)
2. Click **"New Project"**
3. Choose your project settings:
   - **Name**: Trip-MateV2
   - **Region**: Choose the region closest to your deployment
   - **Database Version**: 15 or later (recommended)
4. Click **"Create Project"**

### 2. Get Your Connection Details

After creating the project, you'll see the connection details. You need:

1. **Connection String** - Located in the project dashboard
2. Copy the **JDBC connection string** (not the psql format)

Example format:
```
postgresql://[user]:[password]@[endpoint].neon.tech/[database]?sslmode=require
```

For Java/JDBC, convert it to:
```
jdbc:postgresql://[endpoint].neon.tech/[database]?sslmode=require
```

### 3. Configure Connection Pooling

Neon provides two types of endpoints:

- **Direct endpoint**: Direct connection to the database
- **Pooler endpoint**: Connection pooling (recommended for production)

The pooler endpoint has `-pooler` in the hostname:
```
jdbc:postgresql://ep-fancy-mode-xxx-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require
```

**Always use the pooler endpoint for production deployments** to handle connection scaling efficiently.

### 4. Set Environment Variables

Create or update your `.env` file:

```bash
# Copy the example file if you haven't already
cp .env.example .env
```

Edit `.env` with your Neon connection details:

```bash
# Neon Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://ep-your-endpoint-pooler.region.aws.neon.tech/neondb?sslmode=require
SPRING_DATASOURCE_USERNAME=neondb_owner
SPRING_DATASOURCE_PASSWORD=your_neon_password

# OAuth2 Configuration (set these up separately)
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
GITHUB_CLIENT_ID=your_github_client_id
GITHUB_CLIENT_SECRET=your_github_client_secret

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_minimum_256_bits

# Landing Page URL
LANDING_PAGE_URL=https://yourdomain.com/login/oauth2/code/google
```

**Important Notes:**
- Replace `your-endpoint` with your actual Neon endpoint
- Always include `?sslmode=require` in the URL
- Never commit the `.env` file to version control
- Use the `-pooler` endpoint for better performance

### 5. Initialize the Database Schema

The application will automatically create the necessary tables on first run using Hibernate DDL auto-update.

For production, you have two options:

#### Option A: Automatic Schema Creation (Development)

Set in `application-prod.properties`:
```properties
spring.jpa.hibernate.ddl-auto=update
```

#### Option B: Manual Schema Migration (Recommended for Production)

1. Set in `application-prod.properties`:
   ```properties
   spring.jpa.hibernate.ddl-auto=validate
   ```

2. Create tables manually using the schema from `DATABASE_SCHEMA.md`

3. Or use a migration tool like Flyway or Liquibase

### 6. Test the Connection

Run the application locally to verify the connection:

```bash
# Build the application
./mvnw clean package

# Run with the production profile
java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

The application should start successfully. Check the logs for:
```
Hikari - Starting...
Hikari - Start completed.
```

### 7. Deploy to Production

Once tested locally, deploy to your production environment:

#### Heroku
```bash
heroku config:set SPRING_DATASOURCE_URL="jdbc:postgresql://your-endpoint-pooler.neon.tech/neondb?sslmode=require"
heroku config:set SPRING_DATASOURCE_USERNAME="neondb_owner"
heroku config:set SPRING_DATASOURCE_PASSWORD="your_password"
```

#### Docker
```bash
docker run -d \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://your-endpoint-pooler.neon.tech/neondb?sslmode=require" \
  -e SPRING_DATASOURCE_USERNAME="neondb_owner" \
  -e SPRING_DATASOURCE_PASSWORD="your_password" \
  -p 9090:9090 \
  trip-mate:latest
```

#### AWS/GCP/Azure
Set the environment variables in your cloud provider's console or deployment configuration.

## Configuration Options

### SSL Mode

Neon requires SSL connections. The connection string must include `sslmode=require`:

```
jdbc:postgresql://endpoint.neon.tech/neondb?sslmode=require
```

Available SSL modes:
- `require` - Requires SSL, verifies CA (recommended)
- `disable` - No SSL (not supported by Neon)
- `prefer` - Prefers SSL (not recommended for Neon)

### Connection Pooling Settings

The application uses HikariCP for connection pooling. These settings are optimized for Neon:

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```

These values are already configured in `application-prod.properties`.

### Performance Tuning

For better performance with Neon:

1. **Use the pooler endpoint** (`-pooler` in hostname)
2. **Keep pool size moderate** (10-20 connections is usually enough)
3. **Enable prepared statement caching**
4. **Use connection timeout** to handle network issues

## Monitoring

### Neon Console

Monitor your database in the Neon console:
- Connection count
- CPU and memory usage
- Query performance
- Storage usage

### Application Actuator

The application provides health checks:

```bash
curl http://your-app-url/actuator/health
```

## Troubleshooting

### Connection Timeout

**Problem**: Application times out when connecting to Neon

**Solutions**:
1. Check that `sslmode=require` is in the connection string
2. Verify network connectivity to Neon endpoint
3. Increase connection timeout in properties
4. Check Neon project status in console

### SSL Errors

**Problem**: SSL handshake failures

**Solutions**:
1. Ensure `sslmode=require` is in connection URL
2. Verify Java has up-to-date CA certificates
3. Check that PostgreSQL driver version supports SSL (auto-included in Spring Boot)

### Too Many Connections

**Problem**: "Too many connections" error

**Solutions**:
1. Use the pooler endpoint (`-pooler` in hostname)
2. Reduce `maximum-pool-size` in HikariCP settings
3. Check for connection leaks in application code
4. Upgrade Neon plan if needed

### Authentication Failed

**Problem**: Cannot authenticate to database

**Solutions**:
1. Verify username and password are correct
2. Check that credentials are URL-encoded if they contain special characters
3. Regenerate password in Neon console if needed
4. Ensure environment variables are properly set

## Best Practices

### Security
- ✅ Never commit credentials to version control
- ✅ Use environment variables for all secrets
- ✅ Rotate passwords regularly
- ✅ Use SSL mode `require` or higher
- ✅ Limit database user permissions

### Performance
- ✅ Use the pooler endpoint for production
- ✅ Set appropriate pool sizes (10-20 connections)
- ✅ Enable connection timeout
- ✅ Use prepared statements
- ✅ Index frequently queried columns

### Reliability
- ✅ Enable automated backups in Neon
- ✅ Set up monitoring and alerts
- ✅ Test connection failover
- ✅ Have a backup and recovery plan
- ✅ Use database branching for testing

## Migration from Local Database

If you're migrating from a local database to Neon:

### 1. Export Data
```bash
pg_dump -U tripmate_user -d tripmate > backup.sql
```

### 2. Import to Neon
```bash
# Get Neon connection string from console
psql "postgresql://user:password@endpoint.neon.tech/neondb?sslmode=require" < backup.sql
```

### 3. Update Configuration
Update environment variables to point to Neon (see Step 4 above)

### 4. Test
Run the application and verify all data is accessible

## Additional Resources

- [Neon Documentation](https://neon.tech/docs)
- [Neon Community Forum](https://community.neon.tech)
- [PostgreSQL-compatible JDBC Documentation](https://jdbc.postgresql.org/documentation/)
- [Spring Boot Database Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.datasource)

## Support

For issues related to:
- **Neon Database**: [Neon Support](https://neon.tech/docs/introduction/support)
- **This Application**: [GitHub Issues](https://github.com/iamchirag06/Trip-MateV2/issues)

---

**Happy deploying! 🚀**
