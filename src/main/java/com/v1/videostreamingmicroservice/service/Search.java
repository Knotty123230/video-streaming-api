package com.example.searchmicroservice.service;

import com.example.searchmicroservice.entity.FileMetadataEntity;

import java.util.List;

public interface Search {
    List<FileMetadataEntity> search(String search);
}
