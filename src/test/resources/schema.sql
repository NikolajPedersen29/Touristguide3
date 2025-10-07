DROP TABLE IF EXISTS attraction_tags;
DROP TABLE IF EXISTS tourist_attraction;

CREATE TABLE tourist_attraction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    city VARCHAR(50)
);

CREATE TABLE attraction_tags (
    attraction_id BIGINT NOT NULL,
    tags VARCHAR(50),
    FOREIGN KEY (attraction_id) REFERENCES tourist_attraction(id) ON DELETE CASCADE
);