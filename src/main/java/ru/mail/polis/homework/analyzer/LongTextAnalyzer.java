package ru.mail.polis.homework.analyzer;

public class LongTextAnalyzer implements TextAnalyzer {

    private final long length;

    public LongTextAnalyzer(long length) {
        this.length = length;
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.TOO_LONG;
    }

    @Override
    public boolean analyze(String text) {
        if (text == null) {
            return false; // Или другое поведение, которое вам необходимо
        }
        if (text.length() >= length) {
            return true;
        }
        return false;
    }


    public long getLength() {
        return length;
    }
}
