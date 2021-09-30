package com.hicx.presentation;

import java.util.List;

import com.hicx.data.file.ProcessedFile;

public class DefaultPrinter implements Printer {

    @Override
    public void print(List<ProcessedFile> processedFiles) {
        processedFiles.forEach(this::print);
    }

    private void print(ProcessedFile processedFile) {
        System.out.println(processedFile.toString());
    }
}
