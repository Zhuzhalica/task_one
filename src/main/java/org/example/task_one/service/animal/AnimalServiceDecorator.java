package org.example.task_one.service.animal;

import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.ResponseAnimalDto;
import org.example.task_one.helper.CurrentTime;
import org.example.task_one.model.Animal;
import org.example.task_one.model.LogRecord;
import org.example.task_one.utils.enums.RecordType;
import org.example.task_one.log.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnimalServiceDecorator implements IAnimalService {
    private final IAnimalService animalService;
    private final LogService logService;
    private final CurrentTime time;

    public List<Animal> getAllAnimals() {
        var result = animalService.getAllAnimals();

        var record = new LogRecord(
                RecordType.READ,
                "Получены данные о всех животных. Всего их было - " + result.size(),
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public Animal getAnimalById(long id) {
        var result = animalService.getAnimalById(id);

        var record = new LogRecord(
                RecordType.READ,
                "Получены данные о животном с id = " + id,
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public Animal addAnimal(Animal animal) {
        var result = animalService.addAnimal(animal);

        var record = new LogRecord(
                RecordType.WRITE,
                "Создано животное с id = " + result.getId() + ". Параметры: " + animal.toString(),
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public Animal addAnimalImage(long id, List<String> imageLinks) {
        var result = animalService.addAnimalImage(id, imageLinks);

        var record = new LogRecord(
                RecordType.WRITE,
                "Животному с id = " + result.getId() + " добавили " + imageLinks.size() + " фотографии",
                time.getUTCTime());
        logService.Log(record);

        return result;
    }
}
