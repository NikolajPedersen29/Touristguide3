package com.example.touristguide3.repository;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.models.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristAttractionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TouristAttraction> rowMapper = (ResultSet rs, int rowNum) -> {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setName(rs.getString("name"));
        attraction.setDescription(rs.getString("description"));
        attraction.setCity(City.valueOf(rs.getString("city")));
        attraction.setTags(List.of(Tags.valueOf(rs.getString("tags"))));
        return attraction;
    };

    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT * FROM tourist_attractions";
        return jdbcTemplate.query(sql, rowMapper);
    }


    // Create
    public TouristAttraction addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    // READ
    public TouristAttraction findByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }

    // Update
    public TouristAttraction updateAttraction(String oldName, String newName, String description, City city, List<Tags> tags) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(oldName)) {
                attraction.setName(newName);
                attraction.setDescription(description);
                attraction.setCity(city);
                attraction.setTags(tags);
                return attraction;
            }
        }
        return null;
    }


    //Delete
    public TouristAttraction deleteAttraction(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attractions.remove(attraction);
                return attraction;
            }
        }
        return null;
    }


}

