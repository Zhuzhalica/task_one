package org.example.task_one.mapper;

import org.example.task_one.dto.CreateAnimalDto;
import org.example.task_one.dto.ResponseAnimalDto;
import org.example.task_one.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);

    Animal toAnimalEntity(CreateAnimalDto animalDto);

    ResponseAnimalDto toResponseAnimalDto(Animal animal);
}
