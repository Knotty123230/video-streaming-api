package com.streaming.videostreaming.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class FileResponseDto {
    private String uuid;
    private String name;
    private String description;
    private String nameFile;
}
