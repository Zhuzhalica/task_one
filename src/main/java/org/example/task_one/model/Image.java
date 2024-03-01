package org.example.task_one.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
@Entity
@ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "animal_id")
    private Long animalId;

    @Column(name = "link")
    private String link;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Integer size;
}
