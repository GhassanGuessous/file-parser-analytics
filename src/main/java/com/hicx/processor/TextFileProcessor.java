package com.hicx.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.hicx.data.Extension;
import com.hicx.data.Statistics;
import com.hicx.data.file.File;
import com.hicx.data.file.ProcessedFile;

public class TextFileProcessor implements FileProcessor {

    private static final String NON_LETTER_CHARACTERS_REGEX = "[^a-zA-Z ]";
    private static final String ONE_OR_MORE_SPACE_CHARACTER = "\\s+";
    private static final String WORD_END_WITH_DOT_REGEX = ".*[.]";
    private static final String SPACE = " ";

    private String sourceFolderPath;
    private String processedFilesFolderPath;

    public TextFileProcessor(String sourceFolderPath) {
        this.sourceFolderPath = sourceFolderPath;
        this.processedFilesFolderPath = sourceFolderPath + "/processed";
    }

    @Override
    public ProcessedFile process(File sourceFile) {
        if (sourceFile.getExtension().equals(Extension.TXT)) {
            String filePath = buildSourceFilePath(sourceFile);
            java.io.File file = new java.io.File(filePath);

            Statistics statistics = analyse(file);
            moveToProcessedFolder(sourceFile);

            return new ProcessedFile(sourceFile.getName(), sourceFile.getExtension(), statistics);
        }
        return ProcessedFile.NONE;
    }

    private Statistics analyse(java.io.File file) {
        Map<String, Integer> wordOccurrencesMap = new HashMap<>();
        BufferedReader bufferedReader = null;
        try {
            long wordCount = 0;
            long dotCount = 0;
            String line;
            bufferedReader = new BufferedReader(new FileReader(file));

            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsWithPunctuation = line.split(SPACE);
                String[] wordsWithoutPunctuation = line.replaceAll(NON_LETTER_CHARACTERS_REGEX, "").toLowerCase().split(ONE_OR_MORE_SPACE_CHARACTER);

                calculateWordOccurrences(wordOccurrencesMap, wordsWithoutPunctuation);

                wordCount += wordsWithoutPunctuation.length;
                dotCount += Arrays.stream(wordsWithPunctuation)
                    .filter(word -> word.matches(WORD_END_WITH_DOT_REGEX))
                    .count();
            }

            if (wordCount == 0 || wordOccurrencesMap.isEmpty()) {
                return Statistics.NONE;
            }

            Optional<String> mostUsedWord = Optional.ofNullable(
                Collections.max(wordOccurrencesMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
            return new Statistics(wordCount,dotCount, mostUsedWord.orElse(""));

        } catch (IOException e) {
            return Statistics.NONE;
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateWordOccurrences(Map<String, Integer> wordOccurrencesMap, String[] onlyWords) {
        for (String word : onlyWords) {
            if(wordOccurrencesMap.containsKey(word)) {
                wordOccurrencesMap.put(word, wordOccurrencesMap.get(word) + 1);
            } else {
                wordOccurrencesMap.put(word, 1);
            }
        }
    }

    private void moveToProcessedFolder(File sourceFile) {
        try {
            Files.createDirectories(Paths.get(processedFilesFolderPath));
            Files.move(Paths.get(buildSourceFilePath(sourceFile)), Paths.get(buildDestinationFilePath(sourceFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildSourceFilePath(File sourceFile) {
        return buildFilePath(sourceFile, new StringBuilder(sourceFolderPath));
    }

    private String buildDestinationFilePath(File sourceFile) {
        return buildFilePath(sourceFile, new StringBuilder(processedFilesFolderPath));
    }

    private String buildFilePath(File sourceFile, StringBuilder pathBuilder) {
        return pathBuilder
                .append("/")
                .append(sourceFile.getName())
                .append(".")
                .append(sourceFile.getExtension().getCode())
                .toString();
    }
}
