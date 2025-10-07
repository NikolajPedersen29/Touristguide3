DROP TABLE IF EXISTS attraction_tags;
DROP TABLE IF EXISTS tourist_attraction;
DROP TABLE IF EXISTS tags;


-- Opretter tabellen for turistattraktioner (OPDATERET)
CREATE TABLE IF NOT EXISTS tourist_attraction (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
description TEXT,
city VARCHAR(100) NOT NULL);

-- Opretter join-tabellen for tags (OPDATERET)
CREATE TABLE IF NOT EXISTS attraction_tags (
attraction_id INT,
tag VARCHAR(50),
PRIMARY KEY (attraction_id, tag),
FOREIGN KEY (attraction_id) REFERENCES tourist_attraction(id) ON DELETE CASCADE);