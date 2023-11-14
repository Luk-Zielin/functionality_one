package com.example.functionality_one.DTOs;

import org.springframework.http.HttpMessage;

public class FileMetadataDTO {
    private String filename;
    private String size;
    private String folders;

    public FileMetadataDTO(String filename, long size, String folders) {
        this.filename = filename;
        this.size = (size+" B");
        this.folders = folders;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = (size+" B");
    }

    public String getFolders() {
        return folders;
    }

    public void setFolders(String folders) {
        this.folders = folders;
    }
}
