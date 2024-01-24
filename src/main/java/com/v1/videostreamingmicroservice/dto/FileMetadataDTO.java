package com.v1.videostreamingmicroservice.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "file")
public class FileMetadataEntityDTO {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String header;
    @Field(type = FieldType.Text)
    private String description;
}
