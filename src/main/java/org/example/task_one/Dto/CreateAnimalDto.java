package org.example.task_one.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateAnimalDto {
    private String name;

    private String breed;

    private String color;

    private LocalDate birthDate;
}
