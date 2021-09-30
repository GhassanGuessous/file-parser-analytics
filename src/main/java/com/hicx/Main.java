package com.hicx;

import java.io.IOException;
import java.util.List;

import com.hicx.data.file.FilesLoader;
import com.hicx.data.file.ProcessedFile;
import com.hicx.data.folder.SourceFolder;
import com.hicx.presentation.DefaultPrinter;
import com.hicx.processor.FolderProcessor;

public class Main {

    public static void main(String[] args) {

        if (args.length != 0) {
            final String directoryPath = args[0];

            SourceFolder sourceFolder;
            try {
                sourceFolder = new SourceFolder(directoryPath, FilesLoader.loadFromPath(directoryPath));

                List<ProcessedFile> processedFiles = new FolderProcessor().process(sourceFolder);

                new DefaultPrinter().print(processedFiles);

            } catch (IOException e) {
                throw new IllegalArgumentException("The file you provided does not exist!");
            }

        } else {
            throw new IllegalArgumentException("You did not provide any folder!");
        }
    }
}
