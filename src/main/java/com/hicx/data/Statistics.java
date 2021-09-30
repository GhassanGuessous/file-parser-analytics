package com.hicx.data;

public class Statistics {
    private Long numberOfWords;
    private Long numberOfDots;
    private String mostUsedWord;

    public Statistics(Long numberOfWords, Long numberOfDots, String mostUsedWord) {
        this.numberOfWords = numberOfWords;
        this.numberOfDots = numberOfDots;
        this.mostUsedWord = mostUsedWord;
    }

    public Long getNumberOfWords() {
        return numberOfWords;
    }

    public Long getNumberOfDots() {
        return numberOfDots;
    }

    public String getMostUsedWord() {
        return mostUsedWord;
    }

    @Override
    public String toString() {
        return "Statistics{" +
            "numberOfWords=" + numberOfWords +
            ", numberOfDots=" + numberOfDots +
            ", mostUsedWord='" + mostUsedWord + '\'' +
            '}';
    }
}
