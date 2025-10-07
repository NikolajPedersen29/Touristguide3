package com.example.touristguide3;

import com.example.touristguide3.models.TouristAttraction;
import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.repository.TouristAttractionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TouristAttractionRepositoryIntegrationTest {

    @Autowired
    private TouristAttractionRepository repository;

    @Test
    void testGetAllAttractions() {
        List<TouristAttraction> attractions = repository.getAllAttractions();
        assertThat(attractions).isNotEmpty();
    }


}