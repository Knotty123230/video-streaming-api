package com.example.searchmicroservice.service;

import com.example.searchmicroservice.entity.FileMetadataEntity;
import com.example.searchmicroservice.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoSearch implements Search {
    private final FileMetadataRepository fileMetadataRepository;
    @Override
    public List<FileMetadataEntity> search(String search) {
        String[] searchWords = search.toLowerCase().strip().split(" ");

        return fileMetadataRepository.findAll().stream()
                .filter(entity -> Arrays.stream(searchWords)
                        .anyMatch(keyword -> entity.getHeader().toLowerCase().contains(keyword)))
                .toList();
    }

}
