package com.hicx.processor;

import java.util.List;
import java.util.stream.Collectors;

import com.hicx.data.Extension;
import com.hicx.data.file.ProcessedFile;
import com.hicx.data.folder.SourceFolder;

public class FolderProcessor {

    public List<ProcessedFile> process(SourceFolder folder) {
        FileProcessor textFileProcessor = new TextFileProcessor(folder.getPath());
        return folder.getFiles().stream()
            .filter(file -> file.getExtension().equals(Extension.TXT))
            .map(textFileProcessor::process)
            .collect(Collectors.toList());
    }
}
