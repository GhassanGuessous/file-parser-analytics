package com.hicx.data.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.hicx.data.Extension;

public class FilesLoader {

    private static final String SPLIT_FILE_NAME_EXTENSION_REGEX = "\\.(?=[^\\.]+$)";

    public static List<File> loadFromPath(String directoryPath) throws IOException {
        return Files.list(Paths.get(directoryPath))
            .filter(file -> !Files.isDirectory(file))
            .map(Path::getFileName)
            .map(Path::toString)
            .map(path -> {
                String baseName = path.split(SPLIT_FILE_NAME_EXTENSION_REGEX)[0];
                String extension = path.split(SPLIT_FILE_NAME_EXTENSION_REGEX)[1];
                return new File(baseName, Extension.valueOf(extension.toUpperCase()));
            })
            .collect(Collectors.toList());
    }
}
