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

@Repository
public class TouristAttractionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristAttractionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- RowMapper: sådan oversætter vi SQL resultater til vores model ---
    private final RowMapper<TouristAttraction> attractionMapper = new RowMapper<>() {
        @Override
        public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
            TouristAttraction attraction = new TouristAttraction();
            attraction.setName(rs.getString("name"));
            attraction.setDescription(rs.getString("description"));
            attraction.setCity(City.valueOf(rs.getString("city")));
            // Tags håndteres særskilt (vi henter dem fra join-tabellen)
            return attraction;
        }
    };

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

    // --- READ (find by name) ---
    public TouristAttraction findByName(String name) {
        String sql = "SELECT * FROM tourist_attraction WHERE LOWER(name) = LOWER(?)";
        return jdbcTemplate.queryForObject(sql, attractionMapper, name);
    }

    // --- UPDATE ---
    public void updateAttraction(Long id, TouristAttraction updated) {
        String sql = "UPDATE tourist_attraction SET name = ?, description = ?, city = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updated.getName(),
                updated.getDescription(),
                updated.getCity().name(),
                id);
    }

    // --- DELETE ---
    public void deleteAttraction(Long id) {
        String sql = "DELETE FROM tourist_attraction WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
