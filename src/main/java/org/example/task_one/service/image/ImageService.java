package org.example.task_one.service.image;

import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.ImageDto;
import org.example.task_one.exceptions.custom.EntityNotFoundException;
import org.example.task_one.mapper.ImageMapper;
import org.example.task_one.model.Animal;
import org.example.task_one.model.Image;
import org.example.task_one.repository.ImageRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageStoreService service;
    private final ImageRepository repository;
    private final ImageMapper mapper = ImageMapper.INSTANCE;

    @Cacheable(value = "ImageService::getImageMeta", key = "#file.originalFilename")
    public Image loadImage(MultipartFile file) throws Exception {
        var image = mapper.toImage(service.putImage(file));
        repository.save(image);

        return image;
    }

    public byte[] downloadImage(String link) throws Exception {
        if (!repository.existsByLink(link)) {
            throw new EntityNotFoundException("Фотографии по ссылке " + link + " не существует");
        }

        return service.downloadImage(link);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#link")
    public Image getImageMeta(String link) {
        var image = repository.findByLink(link)
                .orElseThrow(() -> new EntityNotFoundException("Фотографии с link " + link + " не существует"));

        return image;
    }

    public List<Image> getImageByAnimalId(long id) {
        return repository.findAllByAnimalId(id);
    }

    public Image getImageByLink(String link) {
        return repository.findByLink(link).orElse(null);
    }

    public boolean existByLink(String link) {
        return repository.existsByLink(link);
    }

    public void addAnimalId(String link, long id) {
        var image = repository.findByLink(link)
                .orElseThrow(() -> new EntityNotFoundException("Фотографии с link " + link + " не существует"));
        image.setAnimalId(id);
        repository.save(image);
    }
}
