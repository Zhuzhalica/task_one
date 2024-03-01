package org.example.task_one.service.animal;

import org.example.task_one.dto.ResponseAnimalDto;
import org.example.task_one.model.Animal;

import java.util.List;

public interface IAnimalService {
    public List<Animal> getAllAnimals();

    public Animal getAnimalById(long id);

    public Animal addAnimal(Animal animal);

    Animal addAnimalImage(long id, List<String> imageLinks);
}
