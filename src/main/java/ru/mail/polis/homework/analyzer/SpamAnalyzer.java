package ru.mail.polis.homework.analyzer;

public class SpamAnalyzer implements TextAnalyzer {

    private final String[] spam;

    public SpamAnalyzer(String[] spam) {
        this.spam = spam;
    }
    @Override
    public FilterType getFilterType() {
        return FilterType.SPAM;
    }

    @Override
    public boolean analyze(String text) {
        for (String word : spam)
             { if (text.contains(word)) {
                 return true;
             }
        }
        return false;
    }
}
