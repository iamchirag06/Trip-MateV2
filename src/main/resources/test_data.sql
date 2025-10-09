-- Insert test activities (ensuring they exist before other tables reference them)
INSERT INTO activities (name, category, intensity_level, accessibility_rating, cost_estimate, description, created_at)
VALUES 
    ('Scuba Diving', 'Adventure', 'High', 'Good', 150.00, 'Experience the underwater world with professional diving instructors', CURRENT_TIMESTAMP),
    ('Temple Tour', 'Cultural', 'Low', 'Excellent', 30.00, 'Visit ancient temples and learn about local history', CURRENT_TIMESTAMP),
    ('Beach Yoga', 'Relaxation', 'Medium', 'Excellent', 25.00, 'Morning yoga sessions by the beach with experienced instructors', CURRENT_TIMESTAMP);

-- Insert destinations
INSERT INTO destinations (name, country, region, type, description, best_time_to_visit, average_cost_per_day, currency, image_url, visa_requirements, safety_rating, popularity_score, climate)
VALUES 
    ('Bali', 'Indonesia', 'Southeast Asia', 'Beach', 'Beautiful island known for its beaches, culture and spirituality', 'April-October', 100.00, 'USD', 'https://example.com/bali.jpg', 'Visa on arrival available', 8.5, 9.0, 'Tropical'),
    ('Kyoto', 'Japan', 'East Asia', 'Historical', 'Ancient capital of Japan with numerous temples and traditional gardens', 'March-May', 200.00, 'USD', 'https://example.com/kyoto.jpg', 'Visa required', 9.5, 8.5, 'Temperate'),
    ('Swiss Alps', 'Switzerland', 'Europe', 'Mountain', 'Majestic mountain range perfect for hiking and skiing', 'December-March', 300.00, 'USD', 'https://example.com/swiss-alps.jpg', 'Visa required for some nationalities', 9.8, 8.8, 'Cold');

-- Insert test user
INSERT INTO users (email, full_name, image_url, provider_id, provider, created_at)
VALUES ('test@example.com', 'Test User', 'https://example.com/profile.jpg', 'test123', 'LOCAL', CURRENT_TIMESTAMP);

-- Insert budget ranges for the test user
INSERT INTO budget_ranges (trip_type, min_budget, max_budget, currency, user_id)
VALUES 
    ('Beach Vacation', 1000.00, 2000.00, 'USD', (SELECT id FROM users WHERE email='test@example.com')),
    ('City Break', 800.00, 1500.00, 'USD', (SELECT id FROM users WHERE email='test@example.com')),
    ('Adventure Trip', 1500.00, 3000.00, 'USD', (SELECT id FROM users WHERE email='test@example.com'));

-- Link activities to destinations
INSERT INTO destination_activities (destination_id, activity_id, created_at)
VALUES 
    ((SELECT id FROM destinations WHERE name='Bali'), (SELECT id FROM activities WHERE name='Scuba Diving'), CURRENT_TIMESTAMP),
    ((SELECT id FROM destinations WHERE name='Bali'), (SELECT id FROM activities WHERE name='Beach Yoga'), CURRENT_TIMESTAMP),
    ((SELECT id FROM destinations WHERE name='Kyoto'), (SELECT id FROM activities WHERE name='Temple Tour'), CURRENT_TIMESTAMP);

-- Add destination attributes
INSERT INTO destination_attributes (destination_id, attribute_type, attribute_value)
VALUES
    ((SELECT id FROM destinations WHERE name='Bali'), 'Language', 'Indonesian'),
    ((SELECT id FROM destinations WHERE name='Kyoto'), 'Language', 'Japanese'),
    ((SELECT id FROM destinations WHERE name='Swiss Alps'), 'Language', 'German, French, Italian');

-- Add user preferences for the test user
INSERT INTO user_preferences (user_id, preferred_climate, activity_level, trip_type, created_at, updated_at)
VALUES
    ((SELECT id FROM users WHERE email='test@example.com'), 'Tropical', 'High', 'Adventure', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);