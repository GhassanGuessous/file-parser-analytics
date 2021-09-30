package com.hicx;

import java.io.IOException;
import java.util.List;

import com.hicx.data.file.File;
import com.hicx.data.file.FilesLoader;
import com.hicx.data.file.ProcessedFile;
import com.hicx.data.folder.SourceFolder;
import com.hicx.presentation.DefaultPrinter;
import com.hicx.processor.FolderProcessor;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 0) {
            final String directoryPath = args[0];

            List<File> files = FilesLoader.loadFromPath(directoryPath);

            SourceFolder sourceFolder = new SourceFolder(directoryPath, files);

            List<ProcessedFile> processedFiles = new FolderProcessor().process(sourceFolder);

            new DefaultPrinter().print(processedFiles);
        } else {
            throw new IllegalArgumentException("Provide a folder path please!");
        }
    }
}
