package com.streaming.videostreaming.service;

import com.streaming.videostreaming.binarystorage.MinioStorageService;
import com.streaming.videostreaming.entity.FileMetadataEntity;
import com.streaming.videostreaming.exception.StorageException;
import com.streaming.videostreaming.repository.FileMetadataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVideoService implements VideoService {

    private final MinioStorageService storageService;

    private final FileMetadataRepository fileMetadataRepository;

    @Override
    @Transactional
    public FileMetadataEntity save(MultipartFile video, String name, String description) {
        try {
            UUID fileUuid = UUID.randomUUID();
            FileMetadataEntity metadata = FileMetadataEntity.builder()
                    .id(fileUuid.toString())
                    .size(video.getSize())
                    .header(name)
                    .description(description)
                    .name(video.getName())
                    .httpContentType(video.getContentType())
                    .build();
            FileMetadataEntity save = fileMetadataRepository.save(metadata);
            storageService.save(video, fileUuid);
            return save;
        } catch (Exception ex) {
            log.error("Exception occurred when trying to save the file", ex);
            throw new StorageException(ex);
        }
    }


}
