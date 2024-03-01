package org.example.task_one.controller;


import lombok.RequiredArgsConstructor;
import org.example.task_one.dto.ImageDto;
import org.example.task_one.mapper.ImageMapper;
import org.example.task_one.service.image.ImageServiceDecorator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageServiceDecorator service;
    private final ImageMapper mapper = ImageMapper.INSTANCE;

    @PostMapping("/images/load")
    public ImageDto postImage(MultipartFile file) throws Exception {
        return mapper.toImageDto(service.loadImage(file));
    }

    @GetMapping(value = "/images/{link}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String link) throws Exception {
        return service.downloadImage(link);
    }

    @GetMapping("/images/{link}/meta")
    public ImageDto getMeta(@PathVariable String link){
        return mapper.toImageDto(service.getImageMeta(link));
    }

    @GetMapping("/animal/{id}/images/meta")
    public List<ImageDto> getAnimalMetas(@PathVariable int id){
        return service.getImageByAnimalId(id).stream().map(mapper::toImageDto).collect(Collectors.toList());
    }
}
