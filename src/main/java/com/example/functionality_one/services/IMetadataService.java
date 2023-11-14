package com.example.functionality_one.services;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import org.springframework.http.ResponseEntity;

public interface IMetadataService {
    public ResponseEntity createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository);
    public ResponseEntity deleteFile(String filename,MetadataJpaRepository Repository);
    public ResponseEntity readFile(String filename,MetadataJpaRepository Repository);
    public ResponseEntity updateFile(String filename, FileMetadataDTO updatedFile,MetadataJpaRepository Repository);
}
