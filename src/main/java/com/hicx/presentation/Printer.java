package com.hicx.presentation;

import java.util.List;

import com.hicx.data.file.ProcessedFile;

public interface Printer {
    void print(List<ProcessedFile> processedFiles);
}
