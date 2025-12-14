# Trip-MateV2 Backend API - Complete Endpoint List

This document provides a quick reference to all available API endpoints in the Trip-MateV2 backend service.

## Base URL
- **Development:** `http://localhost:9090`
- **Production:** `https://your-domain.com`

---

## 🔐 Authentication & Authorization

### OAuth2 Login
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/oauth2/authorization/google` | Initiate Google OAuth2 login | No |
| GET | `/oauth2/authorization/github` | Initiate GitHub OAuth2 login | No |
| GET | `/login/oauth2/code/google` | Google OAuth2 callback | No |
| GET | `/login/oauth2/code/github` | GitHub OAuth2 callback | No |

### Session Management
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/auth/user` | Get current authenticated user | Yes |
| GET | `/auth/logout` | Logout current user | Yes |

### Test Endpoints
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/test/public` | Public test endpoint | No |
| GET | `/api/test/private` | Private test endpoint (returns user details) | Yes |

---

## 👤 Users Management - `/api/users`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/users` | Get all users | Yes |
| GET | `/api/users/{id}` | Get user by ID | Yes |
| POST | `/api/users` | Create a new user | Yes |
| PUT | `/api/users/{id}` | Update existing user | Yes |
| DELETE | `/api/users/{id}` | Delete user | Yes |

**Example Response:**
```json
{
  "id": 1,
  "email": "user@example.com",
  "fullName": "John Doe",
  "provider": "GOOGLE",
  "createdAt": "2024-01-01T10:00:00"
}
```

---

## ⚙️ User Preferences - `/api/preferences`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/preferences` | Get all user preferences | Yes |
| GET | `/api/preferences/{id}` | Get preference by ID | Yes |
| POST | `/api/preferences` | Create new preference | Yes |
| PUT | `/api/preferences/{id}` | Update existing preference | Yes |
| DELETE | `/api/preferences/{id}` | Delete preference | Yes |

**Example Response:**
```json
{
  "id": 1,
  "userId": 1,
  "tripType": "Family",
  "preferredClimate": "Tropical",
  "activityLevel": "Moderate"
}
```

---

## 🗺️ Trip History - `/api/trips`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/trips` | Get all trip histories | Yes |
| GET | `/api/trips/{id}` | Get trip by ID | Yes |
| POST | `/api/trips` | Create new trip | Yes |
| PUT | `/api/trips/{id}` | Update existing trip | Yes |
| DELETE | `/api/trips/{id}` | Delete trip | Yes |

**Example Response:**
```json
{
  "id": 1,
  "userId": 1,
  "destinationId": 5,
  "tripDate": "2024-06-15",
  "duration": "7 days",
  "actualCost": 1500.00,
  "rating": 5
}
```

---

## 🎯 Activities - `/api/activities`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/activities` | Get all activities | Yes |
| GET | `/api/activities/{id}` | Get activity by ID | Yes |
| POST | `/api/activities` | Create new activity | Yes |
| PUT | `/api/activities/{id}` | Update existing activity | Yes |
| DELETE | `/api/activities/{id}` | Delete activity | Yes |

**Example Response:**
```json
{
  "id": 1,
  "name": "Scuba Diving",
  "category": "Adventure",
  "intensityLevel": "High",
  "costEstimate": 150.00
}
```

---

## 📍 Trip Activities - `/api/trip-activities`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/trip-activities` | Get all trip activities | Yes |
| GET | `/api/trip-activities/{id}` | Get trip activity by ID | Yes |
| POST | `/api/trip-activities` | Create new trip activity | Yes |
| PUT | `/api/trip-activities/{id}` | Update existing trip activity | Yes |
| DELETE | `/api/trip-activities/{id}` | Delete trip activity | Yes |

**Example Response:**
```json
{
  "id": 1,
  "tripId": 1,
  "activityId": 5,
  "enjoymentRating": 5
}
```

---

## 🌍 Destinations - `/api/destinations`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/destinations` | Get all destinations | Yes |
| GET | `/api/destinations/{id}` | Get destination by ID | Yes |
| POST | `/api/destinations` | Create new destination | Yes |
| PUT | `/api/destinations/{id}` | Update existing destination | Yes |
| DELETE | `/api/destinations/{id}` | Delete destination | Yes |

**Example Response:**
```json
{
  "id": 1,
  "name": "Bali",
  "country": "Indonesia",
  "type": "Beach",
  "averageCostPerDay": 75.00,
  "safetyRating": 8.5,
  "popularityScore": 9.2
}
```

---

## 💰 Budget Ranges - `/api/budgets`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/budgets` | Get all budget ranges | Yes |
| GET | `/api/budgets/{id}` | Get budget by ID | Yes |
| POST | `/api/budgets` | Create new budget | Yes |
| PUT | `/api/budgets/{id}` | Update existing budget | Yes |
| DELETE | `/api/budgets/{id}` | Delete budget | Yes |

**Example Response:**
```json
{
  "id": 1,
  "userId": 1,
  "tripType": "Family",
  "minBudget": 2000.00,
  "maxBudget": 5000.00,
  "currency": "USD"
}
```

---

## 💡 Recommendations - `/api/recommendations`

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/recommendations` | Get all recommendations | Yes |
| GET | `/api/recommendations/{id}` | Get recommendation by ID | Yes |
| POST | `/api/recommendations` | Create new recommendation | Yes |
| PUT | `/api/recommendations/{id}` | Update existing recommendation | Yes |
| DELETE | `/api/recommendations/{id}` | Delete recommendation | Yes |

**Example Response:**
```json
{
  "id": 1,
  "userId": 1,
  "destinationId": 5,
  "tripDurationDays": 7,
  "totalEstimatedCost": 2500.00,
  "matchScore": 92.5,
  "recommendationReason": "Perfect match for beach lovers"
}
```

---

## 🔍 Monitoring & Health

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/actuator/health` | Application health status | No |
| GET | `/actuator/info` | Application information | No |
| GET | `/actuator/metrics` | Application metrics | Yes |

---

## 📝 Common Response Codes

| Code | Description |
|------|-------------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 204 | No Content - Successful deletion |
| 400 | Bad Request - Invalid input |
| 401 | Unauthorized - Authentication required |
| 403 | Forbidden - Insufficient permissions |
| 404 | Not Found - Resource not found |
| 409 | Conflict - Duplicate resource |
| 500 | Internal Server Error |

---

## 📚 Additional Documentation

For detailed API documentation with request/response examples, see [API Documentation.md](API%20Documentation.md)

For database schema details, see [DATABASE_SCHEMA.md](DATABASE_SCHEMA.md)

---

**Total Endpoints:** 48 (8 authentication/test endpoints + 40 CRUD endpoints across 8 resources)
