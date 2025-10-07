package com.example.touristguide3.controller;

import com.example.touristguide3.models.City;
import com.example.touristguide3.models.Tags;
import com.example.touristguide3.models.TouristAttraction;
import com.example.touristguide3.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristAttractionController {
    private final TouristAttractionService service;

    public TouristAttractionController(TouristAttractionService service) {
        this.service = service;
    }

    // Find alle attraktioner
    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> touristAttractions = service.getAllAttractions();
        model.addAttribute("attractions", touristAttractions);
        return "attractionsList";
    }

    // Create Form
    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tags.values());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        service.addAttraction(attraction);
        return "redirect:/attractions";
    }

    // EDIT - bruger ID nu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TouristAttraction attraction = service.findById(id);
        model.addAttribute("attraction", attraction);
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tags.values());
        return "editAttraction";
    }

    @PostMapping("/update/{id}")
    public String updateAttraction(@PathVariable Long id, @ModelAttribute TouristAttraction updatedAttraction) {
        service.updateAttraction(id, updatedAttraction);
        return "redirect:/attractions";
    }

    // DELETE - bruger ID nu
    @PostMapping("/delete/{id}")
    public String deleteAttraction(@PathVariable Long id) {
        service.deleteAttraction(id);
        return "redirect:/attractions";
    }
    // Opdater showAttractionTags metoden
    @GetMapping("/{id}/tags")
    public String showAttractionTags(@PathVariable Long id, Model model) {
        TouristAttraction attraction = service.findByIdWithTags(id);
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", attraction.getTags());
        return "attractionTag";
    }
}