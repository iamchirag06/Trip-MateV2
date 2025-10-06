# 🌎 Trip-MateV2

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-72.5%25-orange.svg)](https://www.java.com)
[![HTML](https://img.shields.io/badge/HTML-27.5%25-blue.svg)](https://html.spec.whatwg.org/)
[![OAuth2](https://img.shields.io/badge/OAuth2-Authentication-green.svg)](https://oauth.net/2/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🚀 A comprehensive Spring Boot application for managing travel-related activities, user preferences, and trip planning.

## 🔌 API Endpoints

### 🔐 Authentication Endpoints
```http
GET /login                # Renders the login page
GET /api/test/public      # Public endpoint (no auth required)
GET /api/test/private     # Private endpoint (returns user details)
```

### 👤 User Management (`/api/users`)
```http
GET    /          # Get all users
GET    /{id}      # Get user by ID
POST   /          # Create a new user
PUT    /{id}      # Update an existing user
DELETE /{id}      # Delete a user
```

### ⚙️ User Preferences (`/api/preferences`)
```http
GET    /          # Get all user preferences
GET    /{id}      # Get preference by ID
POST   /          # Create a new preference
PUT    /{id}      # Update an existing preference
DELETE /{id}      # Delete a preference
```

### 🗺️ Trip History (`/api/trips`)
```http
GET    /          # Get all trip histories
GET    /{id}      # Get trip history by ID
POST   /          # Create a new trip history
PUT    /{id}      # Update an existing trip history
DELETE /{id}      # Delete a trip history
```

### 🎯 Activities (`/api/activities`)
```http
GET    /          # Get all activities
GET    /{id}      # Get activity by ID
POST   /          # Create a new activity
PUT    /{id}      # Update an existing activity
DELETE /{id}      # Delete an activity
```

### 📍 Trip Activities (`/api/trip-activities`)
```http
GET    /          # Get all trip activities
GET    /{id}      # Get trip activity by ID
POST   /          # Create a new trip activity
PUT    /{id}      # Update an existing trip activity
DELETE /{id}      # Delete a trip activity
```

### 🌍 Destinations (`/api/destinations`)
```http
GET    /          # Get all destinations
GET    /{id}      # Get destination by ID
POST   /          # Create a new destination
PUT    /{id}      # Update an existing destination
DELETE /{id}      # Delete a destination
```

### 💰 Budget Ranges (`/api/budgets`)
```http
GET    /          # Get all budget ranges
GET    /{id}      # Get budget range by ID
POST   /          # Create a new budget range
PUT    /{id}      # Update an existing budget range
DELETE /{id}      # Delete a budget range
```

### 💡 Recommendations (`/api/recommendations`)
```http
GET    /          # Get all recommendations
GET    /{id}      # Get recommendation by ID
POST   /          # Create a new recommendation
PUT    /{id}      # Update an existing recommendation
DELETE /{id}      # Delete a recommendation
```

## 🛠️ Technology Stack
- ![Java](https://img.shields.io/badge/Java-72.5%25-orange) Core backend development
- ![HTML](https://img.shields.io/badge/HTML-27.5%25-blue) Frontend templates
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-brightgreen) Application framework
- ![JPA](https://img.shields.io/badge/JPA-Hibernate-red) Data persistence
- ![REST](https://img.shields.io/badge/REST-API-yellow) API architecture
- ![OAuth2](https://img.shields.io/badge/OAuth2-Security-green) Authentication

## 🚀 Getting Started

1. Clone the repository
```bash
git clone https://github.com/iamchirag06/Trip-MateV2.git
cd Trip-MateV2
```

2. Ensure you have the following installed:
- Java JDK 11 or higher
- Maven 3.6 or higher

3. Run the application
```bash
./mvnw spring-boot:run
```

## 🔐 Authentication
The application implements OAuth2 authentication for secure access to protected endpoints. Make sure to include the appropriate authentication tokens in your API requests.

## 🤝 Contributing
Contributions are welcome! Feel free to:
- Submit bug reports
- Propose new features
- Create pull requests

---

<div align="center">
Made with ❤️ by <a href="https://github.com/iamchirag06">iamchirag06</a>
</div>
