package com.hicx.data.folder;

import java.util.List;

import com.hicx.data.file.File;

public class SourceFolder {
    private String path;
    private List<File> files;

    public SourceFolder(String path, List<File> files) {
        this.path = path;
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public List<File> getFiles() {
        return files;
    }
}
