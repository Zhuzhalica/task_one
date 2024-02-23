package org.example.task_one;

import lombok.RequiredArgsConstructor;
import org.example.task_one.Model.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}
