package ru.mail.polis.homework.objects;


import java.util.Objects;

/**
 * Нужно найти символ, который встречается подряд в строке чаще всего, и указать количество повторений.
 * Если более одного символа с максимальным значением, то нужно вернуть тот символ,
 * который первый встречается в строчке
 * Если строка пустая или null, то вернуть null
 * Пример abbasbdlbdbfklsssbb -> (s, 3)
 * 4 тугрика
 */
public class RepeatingCharacters {

    public static Pair<Character, Integer> getMaxRepeatingCharacters(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        if (str.length() == 1) {
            new Pair<>(str.charAt(0), 1);
        }

        char ch = str.charAt(0);
        int count = 1;
        char maxCh = ch;
        int maxCount = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
                continue;
            }
            if (maxCount < count) {
                maxCount = count;
                maxCh = ch;
            }
            count = 1;
            ch = str.charAt(i);
        }
        if (maxCount < count) {
            maxCount = count;
            maxCh = ch;
        }

        return new Pair<>(maxCh, maxCount);
    }

    public static class Pair<T, V> {
        private final T first;
        private final V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

    }

    public static void main(String[] args) {
        Pair<Character, Integer> result = getMaxRepeatingCharacters("aabbbbcd");
        if (result != null) {
            System.out.println("Most frequent character: " + result.getFirst());
            System.out.println("Number of repetitions: " + result.getSecond());
        } else {
            System.out.println("The input string is empty or null.");
        }
    }

}

