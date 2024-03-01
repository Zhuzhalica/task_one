package org.example.task_one.service.animal;

import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.ResponseAnimalDto;
import org.example.task_one.exceptions.custom.EntityNotFoundException;
import org.example.task_one.model.Animal;
import org.example.task_one.repository.AnimalRepository;
import org.example.task_one.service.image.ImageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService implements IAnimalService {
    private final AnimalRepository animalRepository;
    private final ImageService imageService;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Cacheable(value = "AnimalService::getAnimalById", key = "#id")
    public Animal getAnimalById(long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @CacheEvict(value = "AnimalService::getAnimalById", key = "#id")
    public Animal addAnimalImage(long id, List<String> imageLinks) {
        var animal = getAnimalById(id);
        if (animal == null) {
            throw new EntityNotFoundException("Животного с таким id не существует");
        }

        for (var link : imageLinks) {
            if (imageService.existByLink(link)) {
                imageService.addAnimalId(link, id);
            }
        }


        return getAnimalById(id);
    }
}
