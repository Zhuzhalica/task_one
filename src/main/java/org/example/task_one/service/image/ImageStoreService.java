package org.example.task_one.service.image;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.example.task_one.dto.ImageDto;
import org.example.task_one.config.MinioProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageStoreService {

    private final MinioClient client;
    private final MinioProperties properties;

    public ImageDto putImage(MultipartFile file) throws Exception {
        var fileId = UUID.randomUUID().toString();

        var inputStream = new ByteArrayInputStream(file.getBytes());
        client.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(properties.getBucket())
                        .object(fileId)
                        .stream(inputStream, file.getSize(), properties.getImageSize())
                        .contentType(file.getContentType())
                        .build()
        );

        return new ImageDto(file.getOriginalFilename(), file.getSize(), fileId);
    }

    public byte[] downloadImage(String link) throws Exception {
        return IOUtils.toByteArray(
                client.getObject(
                        GetObjectArgs
                                .builder()
                                .bucket(properties.getBucket())
                                .object(link)
                                .build()
                )
        );
    }
}
