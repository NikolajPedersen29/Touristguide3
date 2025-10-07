package com.example.touristguide3.models;

import java.util.List;

public class TouristAttraction {

    private Long id;
    private String name;
    private String description;
    private City city;
    private List<Tags> tags;

    public TouristAttraction() {}

    public TouristAttraction(String name, String description, City city, List<Tags> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public TouristAttraction(Long id, String name, String description, City city, List<Tags> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    // --- Getters og Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

}
