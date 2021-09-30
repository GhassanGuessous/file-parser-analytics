package com.hicx.data;

public class Statistics {

    private static final String EMPTY_STRING = "";

    public static Statistics NONE = new Statistics(0, 0, EMPTY_STRING);

    private long numberOfWords;
    private long numberOfDots;
    private String mostUsedWord;

    public Statistics(long numberOfWords, long numberOfDots, String mostUsedWord) {
        this.numberOfWords = numberOfWords;
        this.numberOfDots = numberOfDots;
        this.mostUsedWord = mostUsedWord;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistics{");
        sb.append("numberOfWords=").append(numberOfWords);
        sb.append(", numberOfDots=").append(numberOfDots);
        sb.append(", mostUsedWord='").append(mostUsedWord).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
