package com.hicx.data.folder;

import java.util.List;

import com.hicx.data.file.File;

public class ProcessedFilesFolder extends SourceFolder {

    public ProcessedFilesFolder(String path, List<File> files) {
        super(path, files);
    }
}
