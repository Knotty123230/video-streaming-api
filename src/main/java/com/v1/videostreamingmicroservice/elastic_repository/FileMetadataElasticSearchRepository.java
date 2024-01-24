package com.v1.videostreamingmicroservice.repository;

import com.v1.videostreamingmicroservice.entity.FileMetadataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository("elastic")
public interface FileMetadataElasticSearchRepository extends ElasticsearchRepository<FileMetadataEntity, String> {
        Page<FileMetadataEntity> findByHeader(String name, Pageable pageable);
        Page<FileMetadataEntity> findByDescription(String description, Pageable pageable);
}
