package org.example.task_one;

import org.example.task_one.Dto.CreateAnimalDto;
import org.example.task_one.Model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    Animal toAnimalEntity(CreateAnimalDto car);
}
