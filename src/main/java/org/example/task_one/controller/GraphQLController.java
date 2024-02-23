package org.example.task_one.controller;

import lombok.RequiredArgsConstructor;
import org.example.task_one.AnimalService;
import org.example.task_one.Model.Animal;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphQLController {
    private final AnimalService animalService;

    @QueryMapping
    public List<Animal> getAnimals() {
        return animalService.getAllAnimals();
    }

    @QueryMapping
    public Animal getAnimal(@Argument long id) {
        return animalService.getAnimalById(id);
    }

    @MutationMapping
    public Animal createAnimal(@Argument String name, @Argument String breed) {
        var animal = new Animal(name, breed);
        return animalService.addAnimal(animal);
    }
}