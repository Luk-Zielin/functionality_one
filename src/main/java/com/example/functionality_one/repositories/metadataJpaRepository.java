package com.example.functionality_one.repositories;

import com.example.functionality_one.entities.FileMetadata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface metadataJpaRepository extends JpaRepository<FileMetadata,String> {
    Optional<FileMetadata> findByFilename(String filename);
}
