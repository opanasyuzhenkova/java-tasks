package ru.mail.polis.homework.analyzer;

public class NegativeAnalyzer extends SpamAnalyzer{

    private static final String[] NEGATIVE_EMOTIONS = {"=(", ":(", ":|"};

    public NegativeAnalyzer() {
        super(NEGATIVE_EMOTIONS);
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.NEGATIVE_TEXT;
    }


}
