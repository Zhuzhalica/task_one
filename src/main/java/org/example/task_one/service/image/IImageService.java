package org.example.task_one.service.image;

import org.example.task_one.dto.ImageDto;
import org.example.task_one.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image loadImage(MultipartFile file) throws Exception;

    byte[] downloadImage(String link) throws Exception;

    Image getImageMeta(String link);
    List<Image> getImageByAnimalId(long id);
    Image getImageByLink(String link);

    boolean existByLink(String link);

    void addAnimalId(String link, long id);
}
