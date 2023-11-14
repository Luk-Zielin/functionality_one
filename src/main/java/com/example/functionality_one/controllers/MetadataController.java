package com.example.functionality_one.controllers;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.entities.FileMetadata;
import com.example.functionality_one.repositories.metadataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")

public class MetadataController {
    @Autowired
    private metadataJpaRepository Repository;
    @GetMapping("/{filename}")
    public ResponseEntity<FileMetadataDTO> getFileMetadata(@PathVariable String filename){
        System.out.println("jestem 1");
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFile(@RequestBody FileMetadataDTO fileMetadataDTO) {
        System.out.println("jestem");
        FileMetadata fileMetadata = new FileMetadata(fileMetadataDTO.getFilename(),
                Integer.parseInt(fileMetadataDTO.getSize().split(" ")[0]),
                new ArrayList<String>(List.of(fileMetadataDTO.getFolders().split(",")))
        );
        if (Repository.findByFilename(fileMetadata.getFilename()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "There already exists this kind of file in the system"
            );
        }

        FileMetadata savedFile = Repository.save(fileMetadata);
        return ResponseEntity.ok(savedFile);
    }
    @PutMapping("/{filename}")
    public ResponseEntity<FileMetadata> updateFile(@PathVariable String filename, @RequestBody FileMetadataDTO updatedFile) {
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    existingFile.setSize(Integer.parseInt(updatedFile.getSize().split(" ")[0]));
                    existingFile.setFolders(new ArrayList(List.of(updatedFile.getFolders())));
                    Repository.save(existingFile);
                    return ResponseEntity.ok(existingFile);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{filename}")
    public ResponseEntity deleteFile(@PathVariable String filename) {
        return Repository.findByFilename(filename)
                .map(existingFile -> {
                    Repository.delete(existingFile);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
