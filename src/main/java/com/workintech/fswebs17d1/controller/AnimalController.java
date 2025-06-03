package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping("/")
    public List<Animal> getValue(){
        return animals.values().stream().toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable int id) {
        Animal animal = animals.get(id);
        if (animal != null) {
            return ResponseEntity.ok(animal); // 200 OK + body: Animal JSON
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> saveAnimal(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return ResponseEntity.ok("Animal saved successfully.");
    }


    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal){
        animals.put(id,
                new Animal(id, animal.getName()));
        return animals.get(id);
    }


    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable int id){
        Animal animal = animals.get(id);
        animals.remove(animal);
        return animal;
    }
}