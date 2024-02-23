package org.example.task_one.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal")
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "breed")
    private String breed;

    public Animal(String name, String breed){
        this.name = name;
        this.breed = breed;
    }
}
