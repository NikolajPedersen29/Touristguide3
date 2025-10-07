package com.example.touristguide3.service;

import com.example.touristguide3.models.TouristAttraction;
import com.example.touristguide3.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionService {

    private final TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository repository) {
        this.repository = repository;
    }

    public void addAttraction(TouristAttraction attraction) {
        // Først tilføj attraktionen
        repository.addAttraction(attraction);

        // Find ID for den nye attraktion
        Long attractionId = repository.findIdByName(attraction.getName());

        // Tilføj tags
        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            repository.addTagsToAttraction(attractionId, attraction.getTags());
        }
    }

    // Tilføj metode til at hente tags
    public TouristAttraction findByIdWithTags(Long id) {
        TouristAttraction attraction = repository.findById(id);
        List<Tags> tags = repository.getTagsForAttraction(id);
        attraction.setTags(tags);
        return attraction;
    }

    // --- READ (alle) ---
    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    // --- READ (find by id) ---
    public TouristAttraction findById(Long id) {
        return repository.findById(id);
    }

    // --- UPDATE ---
    public void updateAttraction(Long id, TouristAttraction updated) {
        repository.updateAttraction(id, updated);
    }

    // --- DELETE ---
    public void deleteAttraction(Long id) {
        repository.deleteAttraction(id);
    }
}