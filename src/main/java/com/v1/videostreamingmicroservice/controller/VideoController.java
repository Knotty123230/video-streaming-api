package com.streaming.videostreaming.controller;

import com.streaming.videostreaming.constants.HttpConstants;
import com.streaming.videostreaming.dto.ChunkWithMetadata;
import com.streaming.videostreaming.dto.FileResponseDto;
import com.streaming.videostreaming.entity.FileMetadataEntity;
import com.streaming.videostreaming.service.ChunkService;
import com.streaming.videostreaming.service.RangeCalculator;
import com.streaming.videostreaming.service.VideoService;
import com.streaming.videostreaming.util.Range;
import com.streaming.videostreaming.util.HeadersUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.HttpHeaders.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;
    private final ChunkService service;

    @Value("${photon.streaming.default-chunk-size}")
    public Integer defaultChunkSize;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileResponseDto> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ) {
        FileMetadataEntity fileMetadata = videoService.save(file, name, description);
        return ResponseEntity.ok(
                FileResponseDto.builder()
                        .uuid(fileMetadata.getId())
                        .nameFile(fileMetadata.getName())
                        .description(fileMetadata.getDescription())
                        .name(fileMetadata.getHeader())
                        .build());
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<byte[]> fetchChunk(
            @RequestHeader(value = HttpHeaders.RANGE, required = false) String range,
            @PathVariable UUID uuid
    ) {
        log.info(range);
        Range parsedRange = RangeCalculator.parseHttpRangeString(range, defaultChunkSize);
        ChunkWithMetadata chunkWithMetadata = service.fetchChunk(uuid, parsedRange);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .header(CONTENT_TYPE, chunkWithMetadata.metadata().getHttpContentType())
                .header(ACCEPT_RANGES, HttpConstants.ACCEPTS_RANGES_VALUE)
                .header(CONTENT_LENGTH,
                        HeadersUtil.calculateContentLengthHeader(parsedRange, chunkWithMetadata.metadata().getSize()))
                .header(CONTENT_RANGE,
                        HeadersUtil.constructContentRangeHeader(parsedRange, chunkWithMetadata.metadata().getSize()))
                .body(chunkWithMetadata.chunk());
    }

}
