package com.example.functionality_one.controllers;

import com.example.functionality_one.DTOs.FileMetadataDTO;
import com.example.functionality_one.entities.FileMetadata;
import com.example.functionality_one.repositories.MetadataJpaRepository;
import com.example.functionality_one.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")

public class MetadataController {
    @Autowired
    MetadataJpaRepository Repository;
    MetadataService metadataService;
    public MetadataController(){
        metadataService = new MetadataService();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<FileMetadataDTO> getFileMetadata(@PathVariable String filename){
        System.out.println("jestem 1"); //todo usuń
        return metadataService.readFile(filename,Repository);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postFile(@RequestBody FileMetadataDTO fileMetadataDTO) {
        System.out.println("jestem"); // todo usuń
        return metadataService.createFile(fileMetadataDTO,Repository);
    }
    @PutMapping("/{filename}")
    public ResponseEntity<FileMetadata> putFile(@PathVariable String filename, @RequestBody FileMetadataDTO updatedFile) {
        return metadataService.updateFile(filename, updatedFile,Repository);
    }
    @DeleteMapping("/{filename}")
    public ResponseEntity<FileMetadataDTO> deleteFile(@PathVariable String filename) {
        return metadataService.deleteFile(filename,Repository);
    }

}
