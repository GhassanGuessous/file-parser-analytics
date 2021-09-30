package com.hicx.data.file;

import com.hicx.data.Extension;

public class File {
    private String name;
    private Extension extension;

    public File(String name, Extension extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public Extension getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "File{" +
            "name='" + name + '\'' +
            ", extension=" + extension +
            '}';
    }
}
