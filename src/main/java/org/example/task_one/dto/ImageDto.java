package org.example.task_one.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ImageDto implements Serializable {
    private String name;
    private long size;
    private String link;
}
