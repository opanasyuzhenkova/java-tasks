package ru.mail.polis.homework.objects;

public class StringTasks {

    /**
     * Убрать все лишние символы из строки и вернуть получившееся число.
     * Разрешенные символы: цифры, '-', '.', 'e'
     * Если '.' и 'e' больше чем 1, возвращаем null
     * Правила на '-' является валидность числа. --3 не валидно. -3e-1 валидино
     * Любой класс-обертка StringTasksTest над примитивами наследуется от Number
     * Можно использовать функции типа Double.valueOf()
     * <p>
     * Работайте со строкой, НЕ надо ее переводить в массив байт (это можно использовать только для цикла)
     * У класса Character есть полезные методы, например Character.isDigit()
     * БЕЗ РЕГУЛЯРОК!
     * 6 тугриков
     */
    public static Number valueOf(String str) {
        if (str == null || str.isEmpty()) return null;

        String extractedStr = extractNumberStr(str);
        if (extractedStr.startsWith(".e") || extractedStr.startsWith("e")) {
            return null;
        }
        String validStr = verification(extractedStr);
        if (validStr == null) {
            return null;
        }
        if (validStr.endsWith("d")) {
            return Double.valueOf(validStr);
        }
        Long result = Long.valueOf(validStr);
        if (result <= Integer.MAX_VALUE && result >= Integer.MIN_VALUE) {
            return result.intValue();
        }
        return result;
    }

    private static String verification(String extractedStr) {
        int dotCount = 0;
        int expCount = 0;
        int minusCount = 0;
        //double d = 1e1.4;
        char ch;

        for (int i = 0; i < extractedStr.length(); i++) {
            ch = extractedStr.charAt(i);
            if (ch == '.') {
                if (++dotCount > 1) {
                    return null;
                }
                if (i != 0 && extractedStr.charAt(i - 1) == 'e') {
                    return null;
                }
                if (i == extractedStr.length() - 2 && extractedStr.endsWith("0")) {
                    return null;
                }

            } else if (ch == 'e') {
                if (++expCount > 1) {
                    return null;
                }
                if (i == 0 || i == extractedStr.length() - 1) {
                    return null;
                }
                if (extractedStr.charAt(i - 1) == '.' || extractedStr.charAt(i + 1) == '.') {
                    return null;
                }

            } else if (ch == '-') {
                minusCount++;
                if (minusCount > 2) {
                    return null;
                }

                if (i != 0 && extractedStr.charAt(i - 1) != 'e') {
                    return null;
                }
            }
        }
        return (dotCount + expCount > 0) ? extractedStr + "d" : extractedStr;
    }

    private static String extractNumberStr(String str) { //Отсеиваем все лишнее, нужные символы добавляем в sb
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            switch (ch) {
                case '-':
                case '.':
                case 'e':
                    sb.append(ch);
                    break;
                default:
                    if (Character.isDigit(ch)) {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }
}
