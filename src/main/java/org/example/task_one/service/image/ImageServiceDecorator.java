package org.example.task_one.service.image;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.example.task_one.dto.ImageDto;
import org.example.task_one.helper.CurrentTime;
import org.example.task_one.log.LogService;
import org.example.task_one.model.Image;
import org.example.task_one.model.LogRecord;
import org.example.task_one.utils.enums.RecordType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceDecorator implements IImageService {
    private final IImageService imageService;
    public final LogService logService;
    private final CurrentTime time;

    public Image loadImage(MultipartFile file) throws Exception {
        var result = imageService.loadImage(file);

        var record = new LogRecord(
                RecordType.WRITE,
                "Сохранена фотография. Мета данные: " + result,
                time.getUTCTime());
        logService.Log(record);

        return result;
    }


    public byte[] downloadImage(String link) throws Exception {
        var result = imageService.downloadImage(link);

        var record = new LogRecord(
                RecordType.READ,
                "Получена фотография по адресу " + link,
                time.getUTCTime());
        logService.Log(record);

        return result;
    }


    public Image getImageMeta(String link) {
        var result = imageService.getImageMeta(link);

        var record = new LogRecord(
                RecordType.READ,
                "Получена мета информация о фотографии c link = " + link + ". Данные: " + result,
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public List<Image> getImageByAnimalId(long id) {
        var result = imageService.getImageByAnimalId(id);

        var record = new LogRecord(
                RecordType.READ,
                "Получена мета информация о фотографиях животного c id = " + id + ". Всего: " + result.size(),
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public Image getImageByLink(String link) {
        var result = imageService.getImageByLink(link);

        var record = new LogRecord(
                RecordType.READ,
                "Получена мета информация о фотографиях животного c link = " + link,
                time.getUTCTime());
        logService.Log(record);

        return result;
    }

    public boolean existByLink(String link) {
        return imageService.existByLink(link);
    }

    public void addAnimalId(String link, long id) {
        imageService.addAnimalId(link, id);

        var record = new LogRecord(
                RecordType.WRITE,
                "Добавлена фотография link = " + link + "для животного c id = " + id,
                time.getUTCTime());
        logService.Log(record);
    }
}
