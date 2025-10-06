package com.example.touristguide3.service;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
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

    // Finder alle attraktioner
    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    //Finder bestemt attraktion
    public TouristAttraction getAttractionByName(String name) {
        return repository.findByName(name);
    }

    // Add Attraction
    public TouristAttraction addAttraction(TouristAttraction attraction) {
        return repository.addAttraction(attraction);
    }

    // delete message metode
    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    // Update
    public TouristAttraction updateAttraction(String oldName, String newName, String description, City city, List<Tags> tags) {
        return repository.updateAttraction(oldName, newName, description, city, tags);
    }


}


