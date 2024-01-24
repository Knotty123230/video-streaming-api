package com.streaming.videostreaming.service;

import com.streaming.videostreaming.entity.FileMetadataEntity;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    FileMetadataEntity save(MultipartFile video, String name, String description);

}
