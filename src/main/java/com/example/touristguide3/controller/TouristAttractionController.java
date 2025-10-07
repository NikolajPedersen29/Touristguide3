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

    // Find bestemt attraktion
    @GetMapping("{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = service.findByName(name);
        model.addAttribute("attraction", touristAttraction);
        return "attraction";
    }

    // Attraction tags side
    @GetMapping("{name}/tags")
    public String showAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("tags", attraction.getTags());
        return "attractionTag";
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

    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.findByName(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("cities", City.values());
        model.addAttribute("tags", Tags.values());
        return "editAttraction";
    }

    @PostMapping("/{name}/update")
    public String updateAttraction(@PathVariable String name, @ModelAttribute TouristAttraction updatedAttraction) {
        // Find ID først, så brug det til update
        TouristAttraction existing = service.findByName(name);
        service.updateAttraction(existing.getId(), updatedAttraction);
        return "redirect:/attractions";
    }

    // DELETE - ændret til at bruge ID
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        // Find ID først, så brug det til delete
        TouristAttraction attraction = service.findByName(name);
        service.deleteAttraction(attraction.getId());
        return "redirect:/attractions";
    }
}