package com.example.touristguide3.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tourist_attraction")
public class TouristAttraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING) // Gemmer enum-navn som tekst i DB
    private City city;

    @ElementCollection(targetClass = Tags.class)
    @CollectionTable(
            name = "attraction_tags",
            joinColumns = @JoinColumn(name = "attraction_id")
    )
    @Enumerated(EnumType.STRING) // Gemmer enum-navne i join-tabellen
    private List<Tags> tags;

    public TouristAttraction() {}

    public TouristAttraction(String name, String description, City city, List<Tags> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }
    public List<Tags> getTags() { return tags; }
    public void setTags(List<Tags> tags) { this.tags = tags; }
}
