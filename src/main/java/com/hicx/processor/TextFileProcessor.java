package com.hicx.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.hicx.data.file.File;
import com.hicx.data.file.ProcessedFile;

public class TextFileProcessor implements FileProcessor {

    private String sourceFolderPath;
    private String processedFilesFolderPath;

    public TextFileProcessor(String sourceFolderPath) {
        this.sourceFolderPath = sourceFolderPath;
        this.processedFilesFolderPath = sourceFolderPath + "/processed";
    }

    @Override
    public ProcessedFile process(File sourceFile) {
        String filePath = buildFilePath(sourceFile);
        java.io.File file = new java.io.File(filePath);
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

                //TODO generateStats & moveFileToProcessedFolder

            }
            return new ProcessedFile(sourceFile.getName(), sourceFile.getExtension(), null);
        } catch (IOException e) {
            e.printStackTrace();
            return ProcessedFile.NONE;
        }

    }

    private String buildFilePath(File sourceFile) {
        return new StringBuilder(sourceFolderPath)
                .append("/")
                .append(sourceFile.getName())
                .append(".")
                .append(sourceFile.getExtension().getCode())
                .toString();
    }
}
