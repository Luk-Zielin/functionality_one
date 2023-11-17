package com.example.functionality_one.services;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface IMetadataService {
    String createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository, Model model);

    ResponseEntity<FileMetadataDTO> updateFile(String filename, FileMetadataDTO updatedFile, MetadataJpaRepository Repository);

    String deleteFile(String filename, MetadataJpaRepository Repository, Model model);

    ResponseEntity<FileMetadataDTO> createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository);

    String readFile(String filename, MetadataJpaRepository Repository, Model model);
    ResponseEntity<FileMetadataDTO> readFile(String filename, MetadataJpaRepository Repository);
    String updateFile(String filename, FileMetadataDTO updatedFile,MetadataJpaRepository Repository, Model model);
}
