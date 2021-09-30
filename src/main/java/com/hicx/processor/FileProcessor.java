package com.hicx.processor;

import com.hicx.data.file.File;
import com.hicx.data.file.ProcessedFile;

public interface FileProcessor {
    ProcessedFile process(File file);
}
