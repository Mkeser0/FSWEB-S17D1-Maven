package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private final Map<Integer, Animal> animals = new HashMap<>();

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    // Tüm hayvanları getir
    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    // ID'ye göre hayvan getir
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable int id) {
        Animal animal = animals.get(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Yeni hayvan ekle
    @PostMapping
    public ResponseEntity<String> saveAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return ResponseEntity.ok("Animal saved successfully."); // 200 OK döner
    }

    // Hayvan güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            animals.put(id, new Animal(id, updatedAnimal.getName()));
            return ResponseEntity.ok(animals.get(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Hayvan sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id) {
        Animal removed = animals.remove(id);
        if (removed != null) {
            return ResponseEntity.ok(removed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // course.name ve project.developer.fullname değerlerini döner
    @GetMapping("/info")
    public Map<String, String> getProjectInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("courseName", courseName);
        info.put("developerName", developerName);
        return info;
    }
}
