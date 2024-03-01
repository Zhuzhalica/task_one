package org.example.task_one.mapper;

import org.example.task_one.dto.ImageDto;
import org.example.task_one.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
    ImageDto toImageDto(Image image);
    Image toImage(ImageDto imageDto);
}
