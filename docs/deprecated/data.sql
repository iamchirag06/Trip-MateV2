-- Users
INSERT INTO users (name, email) VALUES
                                    ('Alice Johnson', 'alice@example.com'),
                                    ('Bob Smith', 'bob@example.com'),
                                    ('Charlie Brown', 'charlie@example.com');

-- User Preferences
INSERT INTO user_preferences (user_id, preference_type, value) VALUES
                                                                   (1, 'destination_type', 'Beach'),
                                                                   (1, 'budget', 'Medium'),
                                                                   (2, 'destination_type', 'Mountain'),
                                                                   (3, 'destination_type', 'Adventure');

-- Health Conditions
INSERT INTO health_conditions (condition_name) VALUES
                                                   ('Asthma'),
                                                   ('Heart Condition'),
                                                   ('None');

-- Budget Ranges
INSERT INTO budget_ranges (min_budget, max_budget) VALUES
                                                       (10000, 20000),
                                                       (20001, 40000),
                                                       (40001, 60000);

-- Destinations
INSERT INTO destinations (name, location, type) VALUES
                                                    ('Goa Beach', 'Goa, India', 'Beach'),
                                                    ('Manali Hills', 'Himachal Pradesh, India', 'Mountain'),
                                                    ('Andaman Islands', 'Andaman, India', 'Beach'),
                                                    ('Leh-Ladakh', 'Jammu & Kashmir, India', 'Adventure');

-- Activities
INSERT INTO activities (activity_name, description) VALUES
                                                        ('Scuba Diving', 'Explore marine life under the sea'),
                                                        ('Hiking', 'Mountain hiking experience'),
                                                        ('Paragliding', 'Adventure flying sport'),
                                                        ('Beach Volleyball', 'Fun game on the beach');

-- Trip History
INSERT INTO trip_history (user_id, destination_id, start_date, end_date, rating) VALUES
                                                                                     (1, 1, '2023-05-01', '2023-05-07', 8.5),
                                                                                     (2, 2, '2023-06-10', '2023-06-20', 9.0),
                                                                                     (3, 4, '2023-07-15', '2023-07-22', 9.5);

-- Trip Activities
INSERT INTO trip_activities (trip_id, activity_id) VALUES
                                                       (1, 1),
                                                       (2, 2),
                                                       (3, 3);

-- Recommendations
INSERT INTO recommendations (user_id, destination_id, score) VALUES
                                                                 (1, 3, 8.7),
                                                                 (2, 4, 9.3),
                                                                 (3, 1, 7.8);

-- Recommendation Activities
INSERT INTO recommendation_activities (recommendation_id, activity_id) VALUES
                                                                           (1, 1),
                                                                           (1, 4),
                                                                           (2, 2),
                                                                           (2, 3),
                                                                           (3, 1);
