-- Users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL
);

-- User Preferences
CREATE TABLE user_preferences (
                                  id SERIAL PRIMARY KEY,
                                  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                                  preference_type VARCHAR(50),
                                  value VARCHAR(100)
);

-- Health Conditions
CREATE TABLE health_conditions (
                                   id SERIAL PRIMARY KEY,
                                   condition_name VARCHAR(100) NOT NULL
);

-- Budget Ranges
CREATE TABLE budget_ranges (
                               id SERIAL PRIMARY KEY,
                               min_budget NUMERIC,
                               max_budget NUMERIC
);

-- Destinations
CREATE TABLE destinations (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              location VARCHAR(100),
                              type VARCHAR(50)
);

-- Activities
CREATE TABLE activities (
                            id SERIAL PRIMARY KEY,
                            activity_name VARCHAR(100) NOT NULL,
                            description TEXT
);

-- Trip History
CREATE TABLE trip_history (
                              id SERIAL PRIMARY KEY,
                              user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                              destination_id BIGINT REFERENCES destinations(id) ON DELETE CASCADE,
                              start_date DATE,
                              end_date DATE,
                              rating NUMERIC
);

-- Trip Activities (link trips to activities)
CREATE TABLE trip_activities (
                                 id SERIAL PRIMARY KEY,
                                 trip_id BIGINT REFERENCES trip_history(id) ON DELETE CASCADE,
                                 activity_id BIGINT REFERENCES activities(id) ON DELETE CASCADE
);

-- Recommendations
CREATE TABLE recommendations (
                                 id SERIAL PRIMARY KEY,
                                 user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                                 destination_id BIGINT REFERENCES destinations(id) ON DELETE CASCADE,
                                 score NUMERIC
);

-- Recommendation Activities (link recommendations to activities)
CREATE TABLE recommendation_activities (
                                           id SERIAL PRIMARY KEY,
                                           recommendation_id BIGINT REFERENCES recommendations(id) ON DELETE CASCADE,
                                           activity_id BIGINT REFERENCES activities(id) ON DELETE CASCADE
);
