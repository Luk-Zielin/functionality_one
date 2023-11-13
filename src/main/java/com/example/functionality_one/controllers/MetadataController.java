package com.example.functionality_one.controllers;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.entities.FileMetadata;
import com.example.functionality_one.repositories.metadataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/files")

public class MetadataController {
    @Autowired
    private metadataJpaRepository Repository;
    @GetMapping("/{filename}")
    public Optional<ResponseEntity<FileMetadataDTO>> getFileMetadata(@PathVariable String filename){
        System.out.println("jestem 1");
        return Repository.findByFilename(filename)
                .map(fileMetadata -> {
                    FileMetadataDTO fileMetadataDTO = new FileMetadataDTO(
                            fileMetadata.getFilename(),
                            fileMetadata.getSize(),
                            fileMetadata.getFolders()
                    );
                    return ResponseEntity.ok(fileMetadataDTO);
                });
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileMetadata> createFile(@RequestBody FileMetadataDTO fileMetadataDTO) {
        System.out.println("jestem");
        FileMetadata fileMetadata = new FileMetadata(fileMetadataDTO.getFilename(),
                fileMetadataDTO.getSize(),
                fileMetadataDTO.getFolders()
        );
        if (Repository.findByFilename(fileMetadata.getFilename()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        FileMetadata savedFile = Repository.save(fileMetadata);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
    }

}
