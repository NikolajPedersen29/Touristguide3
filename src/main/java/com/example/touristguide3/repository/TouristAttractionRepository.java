package com.example.touristguide3.repository;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.models.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristAttractionRepository() {
        attractions.add(new TouristAttraction( "Tivoli", "Forlystelsespark", City.København, List.of(Tags.Børnevenlig)));
        attractions.add(new TouristAttraction( "Den Lille Havfrue", "Berømt statue ved havnen", City.København, List.of(Tags.Gratis, Tags.Historie)));
        attractions.add(new TouristAttraction("Rundetårn", "Høj bygning i Københavns centrum", City.København, List.of(Tags.Historie)));
    }

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
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

