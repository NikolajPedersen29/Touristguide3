-- Test data for attractions
INSERT INTO tourist_attraction (id, name, description, city)
VALUES (1, 'Tivoli', 'Forlystelsespark i København', 'COPENHAGEN');

INSERT INTO tourist_attraction (id, name, description, city)
VALUES (2, 'Legoland', 'Tema park med LEGO', 'BILLUND');

-- Tags for Tivoli
INSERT INTO attraction_tags (attraction_id, tags) VALUES (1, 'ENTERTAINMENT');
INSERT INTO attraction_tags (attraction_id, tags) VALUES (1, 'FAMILY');

-- Tags for Legoland
INSERT INTO attraction_tags (attraction_id, tags) VALUES (2, 'FAMILY');
INSERT INTO attraction_tags (attraction_id, tags) VALUES (2, 'THEME_PARK');