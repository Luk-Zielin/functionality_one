package com.example.functionality_one.services;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.entities.FileMetadata;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetadataService implements IMetadataService {


    @Override
    public ResponseEntity<FileMetadataDTO> createFile(FileMetadataDTO fileMetadataDTO, MetadataJpaRepository Repository) {
        FileMetadata fileMetadata = new FileMetadata(fileMetadataDTO.getFilename(),
                Integer.parseInt(fileMetadataDTO.getSize().split(" ")[0]),
                new ArrayList<String>(List.of(fileMetadataDTO.getFolders().split(", ")))
        );
        if (Repository.findByFilename(fileMetadata.getFilename()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        };
        FileMetadata savedFile = Repository.save(fileMetadata);
        return ResponseEntity.ok(fileMetadataDTO);
    }


    public ResponseEntity<FileMetadataDTO> readFile(String filename,MetadataJpaRepository Repository) {
        return Repository.findByFilename(filename)
                .map(fileMetadata -> {
                    FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(
                            fileMetadata.getFilename(),
                            fileMetadata.getSize(),
                            String.join(", ",fileMetadata.getFolders())
                    );
                    return ResponseEntity.ok(fileMetadataDTO);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FileMetadata> updateFile(String filename,FileMetadataDTO updatedFile,MetadataJpaRepository Repository) {
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    existingFile.setSize(Integer.parseInt(updatedFile.getSize().split(" ")[0]));
                    existingFile.setFolders(new ArrayList<>(List.of(updatedFile.getFolders())));
                    Repository.save(existingFile);
                    return ResponseEntity.ok(existingFile);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<FileMetadataDTO> deleteFile(String filename,MetadataJpaRepository Repository) {
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    Repository.delete(existingFile);
                    FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(existingFile);
                    return ResponseEntity.ok().body(fileMetadataDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
