package com.streaming.videostreaming.service;

import com.streaming.videostreaming.binarystorage.MinioStorageService;
import com.streaming.videostreaming.dto.ChunkWithMetadata;
import com.streaming.videostreaming.entity.FileMetadataEntity;
import com.streaming.videostreaming.exception.StorageException;
import com.streaming.videostreaming.exception.VideoNotFoundException;
import com.streaming.videostreaming.repository.FileMetadataRepository;
import com.streaming.videostreaming.util.Range;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChunkService {
    private final MinioStorageService storageService;
    private final FileMetadataRepository fileMetadataRepository;

    private byte[] readChunk(UUID uuid, Range range, long fileSize) {
        long startPosition = range.getRangeStart();
        long endPosition = range.getRangeEnd(fileSize);
        int chunkSize = (int) (endPosition - startPosition + 1);
        log.info("chunk size : {} Chunk start position {} Chunk end position {}", chunkSize, startPosition, endPosition);
        try (InputStream inputStream = storageService.getInputStream(uuid, startPosition, chunkSize)) {
            return inputStream.readAllBytes();
        } catch (Exception exception) {
            log.error("Exception occurred when trying to read file with ID = {}", uuid);
            throw new StorageException(exception);
        }
    }

    public ChunkWithMetadata fetchChunk(UUID uuid, Range range) {
        FileMetadataEntity fileMetadata = fileMetadataRepository.findById(uuid.toString())
                .orElseThrow(() -> new VideoNotFoundException(String.format("Video not found with id %s", uuid)));
        return new ChunkWithMetadata(fileMetadata, readChunk(uuid, range, fileMetadata.getSize()));
    }
}
