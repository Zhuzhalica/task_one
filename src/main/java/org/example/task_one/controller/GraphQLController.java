package org.example.task_one.controller;

import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.ResponseAnimalDto;
import org.example.task_one.mapper.AnimalMapper;
import org.example.task_one.model.Image;
import org.example.task_one.service.animal.AnimalService;
import org.example.task_one.model.Animal;
import org.example.task_one.service.animal.AnimalServiceDecorator;
import org.example.task_one.service.image.ImageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GraphQLController {
    private final AnimalServiceDecorator animalService;
    private final ImageService imageService;
    private final AnimalMapper mapper = AnimalMapper.INSTANCE;

    @QueryMapping
    public List<Animal> getAnimals() {
        return animalService.getAllAnimals();
    }

    @QueryMapping
    public ResponseAnimalDto getAnimal(@Argument long id) {
        var responseAnimal = mapper.toResponseAnimalDto(animalService.getAnimalById(id));
        responseAnimal.setLinks(imageService.getImageByAnimalId(id).stream().map(Image::getLink).collect(Collectors.toList()));

        return responseAnimal;
    }

    @MutationMapping
    public Animal createAnimal(@Argument String name, @Argument String breed) {
        var animal = new Animal(name, breed);
        return animalService.addAnimal(animal);
    }

    @MutationMapping
    public ResponseAnimalDto addAnimalImage(@Argument long id, @Argument List<String> imageLinks) {
        var responseAnimal = mapper.toResponseAnimalDto(animalService.addAnimalImage(id, imageLinks));
        responseAnimal.setLinks(imageService.getImageByAnimalId(id).stream().map(Image::getLink).collect(Collectors.toList()));

        return responseAnimal;
    }
}