# 🌎 Trip-MateV2 Backend API Documentation

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.java.com)
[![OAuth2](https://img.shields.io/badge/OAuth2-Authentication-green.svg)](https://oauth.net/2/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> 🚀 Complete RESTful API documentation for the Trip-MateV2 backend service for managing travel-related activities, user preferences, and trip planning.

## 📋 Table of Contents

- [Base URL](#base-url)
- [Authentication](#authentication-endpoints)
- [API Endpoints](#api-endpoints)
  - [Users](#users-apiusers)
  - [User Preferences](#user-preferences-apipreferences)
  - [Trip History](#trip-history-apitrips)
  - [Activities](#activities-apiactivities)
  - [Trip Activities](#trip-activities-apitrip-activities)
  - [Destinations](#destinations-apidestinations)
  - [Budget Ranges](#budget-ranges-apibudgets)
  - [Recommendations](#recommendations-apirecommendations)
- [Response Formats](#response-formats)
- [Error Handling](#error-handling)

## 🌐 Base URL

```
Development: http://localhost:9090
Production: https://your-domain.com
```

## 🔐 Authentication Endpoints

### OAuth2 Authentication
```http
GET /oauth2/authorization/google    # Initiate Google OAuth2 login
GET /oauth2/authorization/github    # Initiate GitHub OAuth2 login
GET /login/oauth2/code/google       # Google OAuth2 callback
GET /login/oauth2/code/github       # GitHub OAuth2 callback
```

### User Session Management
```http
GET /auth/user                      # Get current authenticated user details
GET /auth/logout                    # Logout endpoint
```

**Response Example for `/auth/user`:**
```json
{
  "authenticated": true,
  "id": 1,
  "email": "user@example.com",
  "roles": ["ROLE_USER"],
  "authLogs": [...]
}
```

### Test Endpoints
```http
GET /api/test/public                # Public endpoint (no auth required)
GET /api/test/private               # Private endpoint (returns user details)
```

## 🔌 API Endpoints

All endpoints below require authentication unless otherwise specified.

### 👤 Users (`/api/users`)

Manage user accounts and profiles.

```http
GET    /api/users          # Get all users
GET    /api/users/{id}     # Get user by ID
POST   /api/users          # Create a new user
PUT    /api/users/{id}     # Update an existing user
DELETE /api/users/{id}     # Delete a user
```

**User Object Structure:**
```json
{
  "id": 1,
  "email": "user@example.com",
  "fullName": "John Doe",
  "dob": "1990-01-15",
  "provider": "GOOGLE",
  "providerId": "google-id-123",
  "imageUrl": "https://example.com/image.jpg",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### ⚙️ User Preferences (`/api/preferences`)

Manage user travel preferences for personalized recommendations.

```http
GET    /api/preferences          # Get all user preferences
GET    /api/preferences/{id}     # Get preference by ID
POST   /api/preferences          # Create a new preference
PUT    /api/preferences/{id}     # Update an existing preference
DELETE /api/preferences/{id}     # Delete a preference
```

**UserPreference Object Structure:**
```json
{
  "id": 1,
  "userId": 1,
  "tripType": "Family",
  "preferredClimate": "Tropical",
  "activityLevel": "Moderate",
  "accessibilityNeeds": "Wheelchair accessible",
  "dietaryRestrictions": "Vegetarian",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

**Trip Types:** Solo, Family, Couple, Group, Business  
**Activity Levels:** Low, Moderate, High, Very High  
**Climate Types:** Tropical, Temperate, Cold, Desert, Mediterranean

### 🗺️ Trip History (`/api/trips`)

Track and manage user's past and planned trips.

```http
GET    /api/trips          # Get all trip histories
GET    /api/trips/{id}     # Get trip history by ID
POST   /api/trips          # Create a new trip history
PUT    /api/trips/{id}     # Update an existing trip history
DELETE /api/trips/{id}     # Delete a trip history
```

**TripHistory Object Structure:**
```json
{
  "id": 1,
  "userId": 1,
  "destinationId": 5,
  "tripDate": "2024-06-15",
  "duration": "7 days",
  "actualCost": 1500.00,
  "rating": 5,
  "review": "Amazing experience! Highly recommended.",
  "createdAt": "2024-01-01T10:00:00"
}
```

**Rating:** 1-5 stars

### 🎯 Activities (`/api/activities`)

Manage travel activities and attractions.

```http
GET    /api/activities          # Get all activities
GET    /api/activities/{id}     # Get activity by ID
POST   /api/activities          # Create a new activity
PUT    /api/activities/{id}     # Update an existing activity
DELETE /api/activities/{id}     # Delete an activity
```

**Activity Object Structure:**
```json
{
  "id": 1,
  "name": "Scuba Diving",
  "category": "Adventure",
  "intensityLevel": "High",
  "accessibilityRating": "Good",
  "costEstimate": 150.00,
  "description": "Explore underwater marine life",
  "createdAt": "2024-01-01T10:00:00"
}
```

**Categories:** Adventure, Cultural, Relaxation, Food, Nature  
**Intensity Levels:** Low, Medium, High  
**Accessibility Ratings:** Excellent, Good, Fair, Limited

### 📍 Trip Activities (`/api/trip-activities`)

Link activities to specific trips with user feedback.

```http
GET    /api/trip-activities          # Get all trip activities
GET    /api/trip-activities/{id}     # Get trip activity by ID
POST   /api/trip-activities          # Create a new trip activity
PUT    /api/trip-activities/{id}     # Update an existing trip activity
DELETE /api/trip-activities/{id}     # Delete a trip activity
```

**TripActivity Object Structure:**
```json
{
  "id": 1,
  "tripId": 1,
  "activityId": 5,
  "enjoymentRating": 5,
  "createdAt": "2024-01-01T10:00:00"
}
```

**Enjoyment Rating:** 1-5 stars

### 🌍 Destinations (`/api/destinations`)

Browse and manage travel destinations worldwide.

```http
GET    /api/destinations          # Get all destinations
GET    /api/destinations/{id}     # Get destination by ID
POST   /api/destinations          # Create a new destination
PUT    /api/destinations/{id}     # Update an existing destination
DELETE /api/destinations/{id}     # Delete a destination
```

**Destination Object Structure:**
```json
{
  "id": 1,
  "name": "Bali",
  "country": "Indonesia",
  "region": "Southeast Asia",
  "type": "Beach",
  "description": "Tropical paradise with stunning beaches",
  "bestTimeToVisit": "April to October",
  "averageCostPerDay": 75.00,
  "currency": "USD",
  "imageUrl": "https://example.com/bali.jpg",
  "visaRequirements": "Visa on arrival for most countries",
  "safetyRating": 8.5,
  "popularityScore": 9.2,
  "climate": "Tropical",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

**Destination Types:** Beach, Mountain, City, Historical, Wildlife, Adventure  
**Climate Types:** Tropical, Temperate, Cold, Desert, Mediterranean  
**Safety Rating:** 0.0-10.0  
**Popularity Score:** 0.0-10.0

### 💰 Budget Ranges (`/api/budgets`)

Define and manage budget constraints for different trip types.

```http
GET    /api/budgets          # Get all budget ranges
GET    /api/budgets/{id}     # Get budget range by ID
POST   /api/budgets          # Create a new budget range
PUT    /api/budgets/{id}     # Update an existing budget range
DELETE /api/budgets/{id}     # Delete a budget range
```

**BudgetRange Object Structure:**
```json
{
  "id": 1,
  "userId": 1,
  "tripType": "Family",
  "minBudget": 2000.00,
  "maxBudget": 5000.00,
  "currency": "USD",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### 💡 Recommendations (`/api/recommendations`)

Get AI-generated personalized travel recommendations.

```http
GET    /api/recommendations          # Get all recommendations
GET    /api/recommendations/{id}     # Get recommendation by ID
POST   /api/recommendations          # Create a new recommendation
PUT    /api/recommendations/{id}     # Update an existing recommendation
DELETE /api/recommendations/{id}     # Delete a recommendation
```

**Recommendation Object Structure:**
```json
{
  "id": 1,
  "userId": 1,
  "destinationId": 5,
  "tripDurationDays": 7,
  "totalEstimatedCost": 2500.00,
  "currency": "USD",
  "matchScore": 92.5,
  "recommendationReason": "Perfect match for beach lovers with family-friendly activities",
  "saved": false,
  "generatedAt": "2024-01-01T10:00:00",
  "viewedAt": "2024-01-02T14:30:00"
}
```

**Match Score:** 0.0-100.0 (percentage match with user preferences)

## 📊 Response Formats

### Success Response
```json
{
  "data": { ... },
  "status": 200,
  "message": "Success"
}
```

### List Response
```json
{
  "data": [ ... ],
  "status": 200,
  "message": "Success",
  "total": 50
}
```

## ⚠️ Error Handling

### Error Response Format
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/users"
}
```

### HTTP Status Codes

| Code | Description |
|------|-------------|
| 200  | OK - Request successful |
| 201  | Created - Resource created successfully |
| 204  | No Content - Request successful, no content returned |
| 400  | Bad Request - Invalid request parameters |
| 401  | Unauthorized - Authentication required |
| 403  | Forbidden - Insufficient permissions |
| 404  | Not Found - Resource not found |
| 409  | Conflict - Resource conflict (e.g., duplicate) |
| 500  | Internal Server Error - Server error |

## 🛠️ Technology Stack

- ![Java](https://img.shields.io/badge/Java-17-orange) Backend development
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen) Application framework
- ![JPA](https://img.shields.io/badge/JPA-Hibernate-red) Data persistence
- ![REST](https://img.shields.io/badge/REST-API-yellow) API architecture
- ![OAuth2](https://img.shields.io/badge/OAuth2-Security-green) Authentication
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon-blue) Database

## 🚀 Getting Started

To integrate with this API:

1. Set up OAuth2 authentication (Google or GitHub)
2. Obtain authentication tokens
3. Use the tokens in subsequent API requests
4. Handle responses and errors appropriately

## 📝 Notes

- All timestamps are in ISO 8601 format
- All monetary values are in decimal format
- All endpoints support standard REST conventions
- Pagination is available for list endpoints (use `?page=0&size=20`)
- Sorting is available (use `?sort=fieldName,asc`)

---

<div align="center">
Made with ❤️ by <a href="https://github.com/iamchirag06">iamchirag06</a>
</div>
