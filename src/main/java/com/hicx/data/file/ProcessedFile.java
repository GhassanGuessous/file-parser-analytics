package com.hicx.data.file;

import com.hicx.data.Extension;
import com.hicx.data.Statistics;

public class ProcessedFile extends File {

    private Statistics statistics;

    public ProcessedFile(String name, Extension extension, Statistics statistics) {
        super(name, extension);
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("file name: ").append(getName()).append(".").append(getExtension().getCode());
        sb.append(", statistics: ").append(statistics);
        sb.append('}');
        return sb.toString();
    }
}
