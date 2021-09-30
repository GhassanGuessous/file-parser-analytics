package com.hicx.data.file;

import com.hicx.data.Extension;
import com.hicx.data.Statistics;

public class ProcessedFile extends File {

    public static ProcessedFile NONE = new ProcessedFile(null, null, null);

    private Statistics statistics;

    public ProcessedFile(String name, Extension extension, Statistics statistics) {
        super(name, extension);
        this.statistics = statistics;
    }
}
