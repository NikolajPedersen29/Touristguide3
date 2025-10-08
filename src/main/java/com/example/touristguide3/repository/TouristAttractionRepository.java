package com.example.touristguide3.repository;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.models.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TouristAttractionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristAttractionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TouristAttraction> attractionMapper = new RowMapper<>() {
        @Override
        public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
            TouristAttraction attraction = new TouristAttraction();
            attraction.setId(rs.getLong("id"));
            attraction.setName(rs.getString("name"));
            attraction.setDescription(rs.getString("description"));
            attraction.setCity(City.valueOf(rs.getString("city")));
            return attraction;
        }
    };

    // --- READ (find by id) ---
    public TouristAttraction findById(Long id) {
        String sql = "SELECT * FROM tourist_attraction WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, attractionMapper, id);
    }

    // Tilføj til TouristAttractionRepository.java
    public Long findIdByName(String name) {
        try {
            String sql = "SELECT id FROM tourist_attraction WHERE LOWER(name) = LOWER(?)";
            return jdbcTemplate.queryForObject(sql, Long.class, name);
        } catch (Exception e) {
            return null;
        }
    }

    // --- READ (find by name) ---
    public TouristAttraction findByName(String name) {
        String sql = "SELECT * FROM tourist_attraction WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, attractionMapper, name);
    }

    // --- CREATE ---
    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO tourist_attraction (name, description, city) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                attraction.getName(),
                attraction.getDescription(),
                attraction.getCity().name());
        // tags skal tilføjes via attraction_tags-tabellen
    }

    // --- READ (alle) ---
    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT * FROM tourist_attraction";
        return jdbcTemplate.query(sql, attractionMapper);
    }

    public void updateAttraction(Long id, TouristAttraction updated) {
        // 1. Opdater attraction data
        String sql = "UPDATE tourist_attraction SET name = ?, description = ?, city = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updated.getName(),
                updated.getDescription(),
                updated.getCity().name(),
                id);

        // 2. Slet gamle tags
        String deleteTagsSql = "DELETE FROM attraction_tags WHERE attraction_id = ?";
        jdbcTemplate.update(deleteTagsSql, id);

        // 3. Indsæt nye tags
        String insertTagSql = "INSERT INTO attraction_tags (attraction_id, tag) VALUES (?, ?)";
        for (Tags tag : updated.getTags()) {
            jdbcTemplate.update(insertTagSql, id, tag.name());
        }
    }

    // --- DELETE ---
    public void deleteAttraction(Long id) {
        String sql = "DELETE FROM tourist_attraction WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    public void addTagsToAttraction(Long attractionId, List<Tags> tags) {
        if (tags == null) return;

        String sql = "INSERT INTO attraction_tags (attraction_id, tag) VALUES (?, ?)";
        for (Tags tag : tags) {
            jdbcTemplate.update(sql, attractionId, tag.name());
        }
    }

    public List<Tags> getTagsForAttraction(Long attractionId) {
        String sql = "SELECT tag FROM attraction_tags WHERE attraction_id = ?";
        List<String> tagNames = jdbcTemplate.queryForList(sql, String.class, attractionId);

        return tagNames.stream()
                .map(Tags::valueOf)
                .collect(Collectors.toList());
    }

}
