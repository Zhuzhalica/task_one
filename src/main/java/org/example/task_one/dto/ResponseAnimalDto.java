package org.example.task_one.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseAnimalDto {
    private Long id;
    private String name;
    private String breed;
    private List<String> links;
}
