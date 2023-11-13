package com.example.functionality_one.DTOs;

public class FileMetadataDTO {
    private String filename;
    private long size;
    private String folders;

    public FileMetadataDTO(String filename, long size, String folders) {
        this.filename = filename;
        this.size = size;
        this.folders = folders;
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
}
