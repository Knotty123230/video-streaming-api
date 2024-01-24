package com.example.searchmicroservice.controller;

import com.example.searchmicroservice.entity.FileMetadataEntity;
import com.example.searchmicroservice.service.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
   private final Search search;
   @GetMapping
    public ResponseEntity<List<FileMetadataEntity>> getSearch(@RequestParam("search") String search){
        return ResponseEntity.ok(this.search.search(search));
    }
}
