package com.example.touristguide3;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.models.TouristAttraction;
import com.example.touristguide3.repository.TouristAttractionRepository;
import com.example.touristguide3.service.TouristAttractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for TouristAttractionService
 * Bruger Mockito til at mocke repository’et.
 */
class TouristAttractionServiceTest {

    private TouristAttractionRepository repository;
    private TouristAttractionService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TouristAttractionRepository.class);
        service = new TouristAttractionService(repository);
    }

    // --- CREATE ---
    @Test
    void testAddAttractionCallsRepository() {
        TouristAttraction attraction = new TouristAttraction(
                "Den Lille Havfrue",
                "Berømt statue ved Langelinie",
                City.København,
                List.of(Tags.GRATIS, Tags.BERØMT)
        );

        service.addAttraction(attraction);

        verify(repository, times(1)).addAttraction(attraction);
    }

    // --- READ (ALL) ---
    @Test
    void testGetAllAttractionsReturnsList() {
        List<TouristAttraction> attractions = List.of(
                new TouristAttraction("Den Lille Havfrue", "Berømt statue", City.København, List.of(Tags.GRATIS)),
                new TouristAttraction("H.C. Andersens Hus", "Eventyrmuseum", City.Odense, List.of(Tags.Museum, Tags.Historie))
        );
        when(repository.getAllAttractions()).thenReturn(attractions);

        List<TouristAttraction> result = service.getAllAttractions();

        assertEquals(2, result.size());
        verify(repository, times(1)).getAllAttractions();
    }

    // --- READ (BY NAME) ---
    @Test
    void testFindByNameReturnsAttraction() {
        TouristAttraction attraction = new TouristAttraction(
                "Tivoli",
                "Forlystelsespark i centrum af København",
                City.København,
                List.of(Tags.BØRNEVENLIG, Tags.KUNST)
        );
        when(repository.findIdByName("Tivoli")).thenReturn(attraction.getId());

        TouristAttraction result = service.findById(repository.findIdByName("Tivoli"));

        assertNotNull(result);
        assertEquals("Tivoli", result.getName());
        assertEquals(City.København, result.getCity());
        verify(repository, times(1)).findIdByName("Tivoli");
    }

    // --- UPDATE BY ID ---
    @Test
    void testUpdateAttractionById() {
        TouristAttraction updated = new TouristAttraction(
                "Aros Kunstmuseum",
                "Kunstmuseum med regnbuepanorama",
                City.Århus,
                List.of(Tags.KUNST, Tags.Museum)
        );

        service.updateAttraction(1L, updated);

        verify(repository, times(1)).updateAttraction(1L, updated);
    }

    // --- DELETE BY ID ---
    @Test
    void testDeleteAttractionById() {
        service.deleteAttraction(1L);
        verify(repository, times(1)).deleteAttraction(1L);
    }

    // --- DELETE BY NAME ---
    @Test
    void testDeleteAttractionByName() {
        TouristAttraction attraction = new TouristAttraction(
                "Ribe VikingeCenter",
                "Historisk oplevelse fra vikingetiden",
                City.Esbjerg,
                List.of(Tags.Historie, Tags.Børnevenlig)
        );
        attraction.setId(42L);

        when(repository.findIdByName("Ribe Vikinge Center")).thenReturn(attraction.getId());

        service.deleteAttraction(repository.findIdByName("Ribe Vikinge Center"));

        verify(repository, times(1)).findIdByName("Ribe Vikinge Center");
        verify(repository, times(1)).deleteAttraction(42L);
    }

    // --- UPDATE BY NAME ---
    @Test
    void testUpdateAttractionByName() {
        TouristAttraction existing = new TouristAttraction(
                "Aalborg Zoo",
                "Zoologisk have i Aalborg",
                City.Ålborg,
                List.of(Tags.Børnevenlig)
        );
        existing.setId(5L);

        TouristAttraction updated = new TouristAttraction(
                "Aalborg Zoo",
                "Stor zoo med mange dyr",
                City.Ålborg,
                List.of(Tags.Børnevenlig, Tags.Natur)
        );

        when(repository.findIdByName("Aalborg Zoo")).thenReturn(existing.getId());

        service.updateAttraction(repository.findIdByName("Aalborg Zoo"), updated);

        verify(repository, times(1)).findIdByName("Aalborg Zoo");
        verify(repository, times(1)).updateAttraction(5L, updated);
    }
}

