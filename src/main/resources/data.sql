-- Indsætter eksempler på turistattraktioner (OPDATERET)
INSERT INTO tourist_attraction (name, description, city) VALUES
('Tivoli', 'En berømt forlystelsespark i hjertet af København.', 'KØBENHAVN'),
('Den Lille Havfrue', 'En ikonisk statue baseret på H.C. Andersens eventyr.', 'KØBENHAVN'),
('Rundetårn', 'En høj bygning i hjertet af København', 'KØBENHAVN');

-- Indsætter relationer mellem attraktioner og tags (OPDATERET)
INSERT INTO attraction_tags (attraction_id, tag) VALUES
(1, 'HISTORISK'),
(1, 'FAMILIEVENLIG'),
(1, 'UDENDØRS'),
(2, 'HISTORISK'),
(2, 'KULTUR'),
(2, 'BERØMT'),
(3, 'HISTORISK'),
(3, 'BERØMT');