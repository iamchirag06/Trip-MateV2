# Trip-MateV2 Database Schema Documentation

## Overview
This document describes the database structure for the Trip-MateV2 application, which is designed to provide personalized travel recommendations based on user preferences, trip history, and destination attributes.

## Entity Relationship Diagram (ERD) Summary

### Core Entities
1. **Users** - Application users
2. **Destinations** - Travel destinations with attributes
3. **Activities** - Things to do at destinations
4. **Recommendations** - AI-generated trip recommendations
5. **TripHistory** - User's past trips

### Supporting Entities
- **UserPreference** - User travel preferences
- **BudgetRange** - User budget constraints
- **DestinationAttribute** - Destination characteristics
- **DestinationActivity** - Activities available at destinations
- **RecommendationActivity** - Activities included in recommendations
- **TripActivity** - Activities completed during trips

---

## Entity Details

### Users Table
**Purpose**: Store user account information

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| email | String | Unique, Not Null | User email (login) |
| password | String | Not Null | Encrypted password |
| fullName | String | | User's full name |
| dob | LocalDate | | Date of birth |
| provider | Enum | | OAuth provider (GOOGLE, GITHUB, LOCAL) |
| providerId | String | | OAuth provider ID |
| imageUrl | String | | Profile picture URL |
| createdAt | LocalDateTime | Auto | Account creation timestamp |
| updatedAt | LocalDateTime | Auto | Last update timestamp |

**Relationships**:
- One-to-Many with UserPreference
- One-to-Many with BudgetRange
- One-to-Many with TripHistory
- One-to-Many with Recommendation

---

### Destinations Table
**Purpose**: Store information about travel destinations

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| name | String(200) | Not Null | Destination name |
| country | String(100) | Not Null | Country name |
| region | String(100) | | Geographic region |
| type | String(100) | | Type (Beach, Mountain, City, Historical, Wildlife, Adventure) |
| description | String(2000) | | Detailed description |
| bestTimeToVisit | String(200) | | Best season/months to visit |
| averageCostPerDay | BigDecimal | >= 0 | Average daily cost |
| currency | String(10) | | Currency code (USD, EUR, etc.) |
| imageUrl | String(500) | | Destination image URL |
| visaRequirements | String(500) | | Visa information |
| safetyRating | Double | 0.0-10.0 | Safety score |
| popularityScore | Double | 0.0-10.0 | Popularity for recommendation algorithm |
| climate | String(100) | | Climate type (Tropical, Temperate, Cold, Desert, Mediterranean) |
| createdAt | LocalDateTime | Auto | Creation timestamp |
| updatedAt | LocalDateTime | Auto | Last update timestamp |

**Indexes**:
- idx_dest_country (country)
- idx_dest_type (type)
- idx_dest_safety (safetyRating)
- idx_dest_popularity (popularityScore)

**Relationships**:
- One-to-Many with DestinationAttribute
- One-to-Many with DestinationActivity
- One-to-Many with Recommendation
- One-to-Many with TripHistory

---

### Activities Table
**Purpose**: Store available activities/attractions

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| name | String(200) | Not Null | Activity name |
| category | String(100) | | Category (Adventure, Cultural, Relaxation, Food, Nature) |
| intensityLevel | String(50) | | Intensity (Low, Medium, High) |
| accessibilityRating | String(50) | | Accessibility (Excellent, Good, Fair, Limited) |
| costEstimate | BigDecimal | >= 0 | Estimated cost |
| description | String(1000) | | Activity description |
| createdAt | LocalDateTime | Auto | Creation timestamp |

**Indexes**:
- idx_activity_category (category)
- idx_activity_intensity (intensityLevel)

**Relationships**:
- One-to-Many with DestinationActivity
- One-to-Many with RecommendationActivity
- One-to-Many with TripActivity

---

### Recommendations Table
**Purpose**: Store AI-generated trip recommendations for users

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| user_id | Long | FK, Not Null | User reference |
| destination_id | Long | FK, Not Null | Destination reference |
| tripDurationDays | Integer | 1-365 | Recommended trip duration in days |
| totalEstimatedCost | BigDecimal | >= 0 | Total estimated cost |
| currency | String(10) | | Currency code |
| matchScore | Double | 0.0-100.0 | Percentage match with user preferences |
| recommendationReason | String(1000) | | Explanation for recommendation |
| saved | Boolean | Default: false | User saved this recommendation |
| generatedAt | LocalDateTime | Auto, Not Null | When recommendation was created |
| viewedAt | LocalDateTime | | When user viewed the recommendation |

**Indexes**:
- idx_rec_user (user_id)
- idx_rec_dest (destination_id)
- idx_rec_score (matchScore)
- idx_rec_generated (generatedAt)
- idx_rec_saved (saved)

**Relationships**:
- Many-to-One with User
- Many-to-One with Destination
- One-to-Many with RecommendationActivity

---

### TripHistory Table
**Purpose**: Store user's completed trips for learning preferences

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| user_id | Long | FK, Not Null | User reference |
| destination_id | Long | FK, Not Null | Destination reference |
| tripDate | LocalDate | Not Null | Trip start date |
| duration | String(50) | Not Null | Trip duration (e.g., "3 days", "1 week") |
| actualCost | BigDecimal | >= 0 | Actual trip cost |
| rating | Integer | 1-5 | User's trip rating |
| review | String(2000) | | User's review/feedback |
| createdAt | LocalDateTime | Auto | Record creation timestamp |

**Indexes**:
- idx_trip_user (user_id)
- idx_trip_dest (destination_id)
- idx_trip_date (tripDate)
- idx_trip_rating (rating)

**Relationships**:
- Many-to-One with User
- Many-to-One with Destination
- One-to-Many with TripActivity

---

### UserPreference Table
**Purpose**: Store user's travel preferences for recommendations

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| user_id | Long | FK, Not Null | User reference |
| tripType | String(100) | | Type (Solo, Family, Couple, Group, Business) |
| preferredClimate | String(100) | | Preferred climate |
| activityLevel | String(50) | | Activity level (Low, Moderate, High, Very High) |
| accessibilityNeeds | String(500) | | Accessibility requirements |
| dietaryRestrictions | String(500) | | Dietary restrictions |
| createdAt | LocalDateTime | Auto | Creation timestamp |
| updatedAt | LocalDateTime | Auto | Last update timestamp |

**Indexes**:
- idx_pref_user (user_id)
- idx_pref_type (tripType)
- idx_pref_climate (preferredClimate)

**Relationships**:
- Many-to-One with User

---

### BudgetRange Table
**Purpose**: Store user's budget constraints by trip type

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| user_id | Long | FK, Not Null | User reference |
| tripType | String(100) | Not Null | Trip type |
| minBudget | BigDecimal | >= 0, Not Null | Minimum budget |
| maxBudget | BigDecimal | > 0, Not Null | Maximum budget |
| currency | String(10) | Not Null | Currency code |
| createdAt | LocalDateTime | Auto | Creation timestamp |
| updatedAt | LocalDateTime | Auto | Last update timestamp |

**Indexes**:
- idx_budget_user (user_id)
- idx_budget_type (tripType)

**Relationships**:
- Many-to-One with User

---

### DestinationAttribute Table
**Purpose**: Store additional attributes/characteristics of destinations

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| destination_id | Long | FK, Not Null | Destination reference |
| attributeType | String(100) | Not Null | Attribute type (Language, Religion, Culture, Transportation) |
| attributeValue | String(200) | Not Null | Attribute value |

**Indexes**:
- idx_attr_dest (destination_id)
- idx_attr_type (attributeType)

**Relationships**:
- Many-to-One with Destination

---

### DestinationActivity Table
**Purpose**: Link activities to destinations with context

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| destination_id | Long | FK, Not Null | Destination reference |
| activity_id | Long | FK, Not Null | Activity reference |
| bestTimeOfDay | String(50) | | Best time (Morning, Afternoon, Evening, Night, Anytime) |
| createdAt | LocalDateTime | Auto | Creation timestamp |

**Indexes**:
- idx_destact_dest (destination_id)
- idx_destact_activity (activity_id)

**Relationships**:
- Many-to-One with Destination
- Many-to-One with Activity

---

### RecommendationActivity Table
**Purpose**: Link activities to recommendations with itinerary details

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| recommendation_id | Long | FK, Not Null | Recommendation reference |
| activity_id | Long | FK, Not Null | Activity reference |
| dayNumber | Integer | >= 1, Not Null | Day of trip itinerary |
| timeOfDay | String(50) | | Scheduled time (Morning, Afternoon, Evening, Night) |
| estimatedCost | BigDecimal | >= 0 | Estimated activity cost |
| createdAt | LocalDateTime | Auto | Creation timestamp |

**Indexes**:
- idx_recact_rec (recommendation_id)
- idx_recact_activity (activity_id)
- idx_recact_day (dayNumber)

**Relationships**:
- Many-to-One with Recommendation
- Many-to-One with Activity

---

### TripActivity Table
**Purpose**: Track activities completed during trips with user feedback

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| id | Long | PK, Auto-increment | Unique identifier |
| trip_id | Long | FK, Not Null | TripHistory reference |
| activity_id | Long | FK, Not Null | Activity reference |
| enjoymentRating | Integer | 1-5 | User's enjoyment rating |
| createdAt | LocalDateTime | Auto | Creation timestamp |

**Indexes**:
- idx_tripact_trip (trip_id)
- idx_tripact_activity (activity_id)
- idx_tripact_rating (enjoymentRating)

**Relationships**:
- Many-to-One with TripHistory
- Many-to-One with Activity

---

## Recommendation Algorithm Design

### Key Fields for Matching Algorithm

1. **User Profile Matching**:
   - UserPreference.preferredClimate → Destination.climate
   - UserPreference.activityLevel → Activity.intensityLevel
   - UserPreference.tripType → Destination.type
   - BudgetRange.minBudget/maxBudget → Destination.averageCostPerDay

2. **Learning from History**:
   - TripHistory.rating → Weight destinations/activities
   - TripActivity.enjoymentRating → Prefer similar activities
   - Past destination types → Recommend similar types

3. **Scoring Components**:
   - Destination.popularityScore (0-10)
   - Destination.safetyRating (0-10)
   - User preference match (0-100)
   - Budget fit score (0-100)
   - Historical preference (0-100)

4. **Final Match Score Calculation**:
   ```
   matchScore = (
     preferenceMatch * 0.30 +
     budgetFit * 0.25 +
     popularityScore * 0.20 +
     safetyRating * 0.15 +
     historicalPreference * 0.10
   )
   ```

---

## Performance Optimizations

### Indexing Strategy
All foreign keys are indexed for efficient joins. Additional indexes are placed on:
- Frequently filtered columns (country, type, climate)
- Sorting columns (matchScore, rating, generatedAt)
- Boolean flags (saved)

### Fetch Strategy
- All relationships use `FetchType.LAZY` to prevent N+1 queries
- Only load related entities when explicitly needed

### Cascade Operations
- `CascadeType.ALL` for owned entities (attributes, activities in recommendations)
- `orphanRemoval = true` for dependent entities

---

## Data Integrity

### Validation Constraints
- Required fields: `@NotNull`, `@NotBlank`
- Numeric ranges: `@Min`, `@Max`, `@PositiveOrZero`, `@Positive`
- String lengths: `@Size`
- Decimal ranges: `@DecimalMin`, `@DecimalMax`

### Automatic Timestamps
- `@PrePersist` sets createdAt on entity creation
- `@PreUpdate` updates updatedAt on entity modification

### Referential Integrity
- Foreign keys marked as `nullable = false` where required
- Cascade delete for dependent data
- Orphan removal for owned collections

---

## Database Technology

- **DBMS**: PostgreSQL 12+ (Production), H2 (Testing)
- **ORM**: Hibernate via Spring Data JPA
- **Schema Management**: `spring.jpa.hibernate.ddl-auto=update`

---

## Migration Notes

When migrating from previous schema:
1. `TripHistory.tripDate` changed from String to LocalDate
2. `TripHistory.rating` changed from String to Integer
3. `Recommendation.tripDuration` renamed to `tripDurationDays` and changed to Integer
4. New fields added:
   - `Activity.category`
   - `Destination.popularityScore`
   - `Destination.climate`
5. New indexes added for performance
6. Validation constraints added

---

## Future Enhancements

Potential schema improvements:
1. Add full-text search indexes for descriptions
2. Add geospatial data types for location-based queries
3. Add seasonal pricing variations
4. Add user groups/following for social features
5. Add notification preferences
6. Add flight/hotel booking integration tables
7. Add photo/media galleries for destinations
8. Add user reviews and ratings for destinations

---

**Last Updated**: 2025-10-09
**Version**: 2.0
