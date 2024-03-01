package org.example.task_one.repository;

import org.example.task_one.dto.ImageDto;
import org.example.task_one.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByLink(String link);
    List<Image> findAllByAnimalId(Long animalId);

    Optional<Image> findByLink(String link);
}
