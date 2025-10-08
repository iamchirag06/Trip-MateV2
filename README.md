# 🌎 Trip-MateV2

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![OAuth2](https://img.shields.io/badge/OAuth2-Authentication-green.svg)](https://oauth.net/2/)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-blue.svg)](https://github.com/iamchirag06/Trip-MateV2/actions)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🚀 A comprehensive Spring Boot application for managing travel-related activities, user preferences, and trip planning.

## ✨ Features

- 🔐 **OAuth2 Authentication** - Secure login with Google and GitHub
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
- ✅ [Deployment Checklist](DEPLOYMENT_CHECKLIST.md) - Pre and post-deployment checklist

## 🛠️ Technology Stack

- **Java 17** - Core backend development
- **Spring Boot 3.5** - Application framework
- **Spring Security** - Authentication & authorization
- **OAuth2** - Social login (Google & GitHub)
- **Spring Data JPA** - Data persistence
- **PostgreSQL** - Primary database
- **Thymeleaf** - Server-side templates
- **Docker** - Containerization
- **Maven** - Dependency management

## 🔧 Configuration

### Prerequisites

- Java JDK 17 or higher
- Maven 3.6+ (or use included Maven wrapper)
- PostgreSQL 12+ (or use Docker Compose)
- OAuth2 credentials from Google and GitHub

### Environment Variables

Create a `.env` file based on `.env.example`:

```bash
cp .env.example .env
```

Configure the following:
- Database connection details
- Google OAuth2 credentials
- GitHub OAuth2 credentials
- JWT secret key

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

# Run with Docker Compose (includes PostgreSQL)
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## 🔐 OAuth2 Setup

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

## 📊 API Endpoints

### Authentication
- `GET /login` - Login page
- `GET /api/test/public` - Public endpoint
- `GET /api/test/private` - Protected endpoint

### Resources
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
