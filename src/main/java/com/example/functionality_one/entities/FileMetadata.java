package com.example.functionality_one.entities;

import jakarta.persistence.*;

@Entity
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String filename;
    private long size;

    private String folders;

    public FileMetadata() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFolders() {
        return folders;
    }

    public void setFolders(String folders) {
        this.folders = folders;
    }

    public FileMetadata(String filename, long size, String folders) {
        this.filename = filename;
        this.size = size;
        this.folders = folders;
    }
}
