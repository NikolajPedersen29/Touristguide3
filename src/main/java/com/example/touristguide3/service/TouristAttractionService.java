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

    // --- CREATE ---
    public void addAttraction(TouristAttraction attraction) {
        repository.addAttraction(attraction);
    }

    // --- READ (alle) ---
    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    // --- READ (find by name) ---
    public TouristAttraction findByName(String name) {
        return repository.findByName(name);
    }

    // --- UPDATE ---
    public void updateAttraction(Long id, TouristAttraction updated) {
        repository.updateAttraction(id, updated);
    }

    // --- DELETE ---
    public void deleteAttraction(Long id) {
        repository.deleteAttraction(id);
    }
    // --- DELETE by name ---
    public void deleteAttraction(String name) {
        TouristAttraction attraction = repository.findByName(name);
        repository.deleteAttraction(attraction.getId());
    }

    // --- UPDATE by name ---
    public void updateAttraction(String name, TouristAttraction updated) {
        TouristAttraction existing = repository.findByName(name);
        repository.updateAttraction(existing.getId(), updated);
    }
}