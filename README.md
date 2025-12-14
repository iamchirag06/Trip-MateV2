# 🌎 Trip-MateV2

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![OAuth2](https://img.shields.io/badge/OAuth2-Authentication-green.svg)](https://oauth.net/2/)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-blue.svg)](https://github.com/iamchirag06/Trip-MateV2/actions)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🚀 A comprehensive Spring Boot application for managing travel-related activities, user preferences, and trip planning.

## ✨ Features

- 🔐 **JWT Authentication** - Secure API authentication with JWT tokens
- 🔑 **OAuth2 Support** - Backend OAuth2 integration with Google and GitHub (returns JWT tokens)
- 👤 **User Management** - Complete user profile and preferences system
- 🗺️ **Trip Planning** - Create and manage trip histories
- 🎯 **Activity Management** - Track and organize travel activities
- 🌍 **Destination Catalog** - Browse and manage destinations
- 💰 **Budget Management** - Plan trips within budget ranges
- 💡 **Smart Recommendations** - Get personalized travel suggestions

## 🚀 Quick Start

### Using Docker (Recommended)

The fastest way to get started:

```bash
# Clone the repository
git clone https://github.com/iamchirag06/Trip-MateV2.git
cd Trip-MateV2

# Copy and configure environment variables
cp .env.example .env
# Edit .env with your configuration

# Start the application
docker-compose up -d
```

The application will be available at `http://localhost:9090`

### Local Development

```bash
# Ensure Java 17+ and Maven are installed
./mvnw spring-boot:run
```

## 📚 Documentation

- 📖 [API Documentation](API%20Documentation.md) - Complete API reference
- 🚀 [Deployment Guide](DEPLOYMENT.md) - Deployment instructions for various platforms
  - Docker & Docker Compose
  - Heroku
  - AWS (Elastic Beanstalk & ECS)
  - Google Cloud Platform
  - Azure
- ⚡ [Quick Start: Render](QUICK_START_RENDER.md) - **Deploy to Render in 8 minutes**
- 🎯 [Render Deployment Guide](docs/RENDER_DEPLOYMENT.md) - Complete Render.com deployment guide
- ✅ [Deployment Checklist](DEPLOYMENT_CHECKLIST.md) - Pre and post-deployment checklist
- 🧪 [Backend Testing Report](BACKEND_TESTING_REPORT.md) - Test coverage and validation results
- 🗄️ [Neon Database Setup](docs/NEON_SETUP.md) - Neon PostgreSQL setup guide
- 📊 [Database Schema](DATABASE_SCHEMA.md) - Complete database schema documentation

## 🛠️ Technology Stack

- **Java 17** - Core backend development
- **Spring Boot 3.5** - Application framework
- **Spring Security** - Authentication & authorization
- **JWT** - JSON Web Token for stateless authentication
- **OAuth2** - Social login integration (Google & GitHub) for backend token generation
- **Spring Data JPA** - Data persistence
- **Neon DB** - Serverless PostgreSQL-compatible database (primary)
- **Docker** - Containerization
- **Maven** - Dependency management

## 🔧 Configuration

### Prerequisites

- Java JDK 17 or higher
- Maven 3.6+ (or use included Maven wrapper)
- [Neon Database](https://neon.tech) account (recommended) or Docker Compose for local development
- OAuth2 credentials from Google and GitHub

### Environment Variables

Create a `.env` file based on `.env.example`:

```bash
cp .env.example .env
```

Configure the following:
- Database connection details (Neon DB recommended - see [DEPLOYMENT.md](DEPLOYMENT.md) for setup)
- Google OAuth2 credentials
- GitHub OAuth2 credentials
- JWT secret key

#### Database Options:
- **Neon Database** (Recommended): Serverless PostgreSQL-compatible database with built-in pooling and SSL - perfect for both development and production
- **Local with Docker Compose**: Includes a local database container for development (see docker-compose.yml)

See [DEPLOYMENT.md](DEPLOYMENT.md) for detailed configuration instructions.

## 📦 Building from Source

```bash
# Build the application
./mvnw clean package

# Run tests
./mvnw test

# Run the JAR
java -jar target/Trip-MateV2-0.0.1-SNAPSHOT.jar
```

## 🐳 Docker Support

```bash
# Build Docker image
docker build -t trip-mate:latest .

# Run with Docker Compose (includes local database for development)
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## 🔐 OAuth2 Setup

This application uses OAuth2 **for backend token generation only**. The OAuth2 flow returns JWT tokens in JSON format (no HTML redirects). External applications should use these tokens for subsequent API calls via the `Authorization: Bearer <token>` header.

### Google OAuth2

1. Visit [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project
3. Enable Google+ API
4. Create OAuth 2.0 credentials
5. Configure authorized redirect URIs:
   - Development: `http://localhost:9090/login/oauth2/code/google`
   - Production: `https://yourdomain.com/login/oauth2/code/google`

### GitHub OAuth2

1. Visit [GitHub Developer Settings](https://github.com/settings/developers)
2. Create a new OAuth App
3. Configure callback URL:
   - Development: `http://localhost:9090/login/oauth2/code/github`
   - Production: `https://yourdomain.com/login/oauth2/code/github`

### Authentication Flow

1. **OAuth2 Login**: Initiate OAuth2 flow via `/oauth2/authorization/google` or `/oauth2/authorization/github`
2. **Receive JWT**: After successful authentication, receive a JSON response with JWT token:
   ```json
   {
     "success": true,
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
     "tokenType": "Bearer",
     "user": {
       "id": 1,
       "email": "user@example.com",
       "authorities": [{"authority": "ROLE_USER"}]
     }
   }
   ```
3. **Use JWT**: Include the token in all API requests:
   ```bash
   curl -H "Authorization: Bearer <token>" http://localhost:9090/api/users
   ```
2. Create a new OAuth App
3. Configure callback URL:
   - Development: `http://localhost:9090/login/oauth2/code/github`
   - Production: `https://yourdomain.com/login/oauth2/code/github`

## 📊 API Endpoints

### Authentication
- `GET /oauth2/authorization/google` - Initiate Google OAuth2 authentication (returns JWT in JSON)
- `GET /oauth2/authorization/github` - Initiate GitHub OAuth2 authentication (returns JWT in JSON)
- `GET /auth/user` - Get current authenticated user info (requires JWT token)

### Health Check
- `GET /actuator/health` - Public health check endpoint (no authentication required)

### Protected Resources (require JWT token via `Authorization: Bearer <token>` header)
- `/api/users` - User management
- `/api/preferences` - User preferences
- `/api/trips` - Trip history
- `/api/activities` - Activity management
- `/api/trip-activities` - Trip-activity associations
- `/api/destinations` - Destination catalog
- `/api/budgets` - Budget ranges
- `/api/recommendations` - Travel recommendations

See [API Documentation](API%20Documentation.md) for complete endpoint details.

## 🔍 Monitoring

Spring Boot Actuator endpoints are available:

```bash
# Health check
curl http://localhost:9090/actuator/health

# Application info
curl http://localhost:9090/actuator/info
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Contributors and supporters of this project

## 📧 Contact

**Chirag** - [@iamchirag06](https://github.com/iamchirag06)

Project Link: [https://github.com/iamchirag06/Trip-MateV2](https://github.com/iamchirag06/Trip-MateV2)

---

<div align="center">
Made with ❤️ by <a href="https://github.com/iamchirag06">iamchirag06</a>
</div>
