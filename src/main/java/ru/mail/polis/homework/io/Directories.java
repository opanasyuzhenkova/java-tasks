package ru.mail.polis.homework.io;

import java.io.File;
import java.io.IOException;

public class Directories {


    /**
     * Реализовать рекурсивное удаление всех файлов и директорий из директории по заданному пути.
     * Метод должен возвращать количество удаленных файлов и директорий.
     * Если директории по существующему пути нет, то возвращаем 0.
     * Написать двумя способами. С использованием File
     * 2 тугрика
     */
    public static int removeWithFile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            file.delete();
            return 1;
        }
        int count = 1;
        for (File f : file.listFiles()) {
            count += removeWithFile(f.getName());
        }
        return count;
    }


    /**
     * С использованием Path
     * 2 тугрика
     */
    public static int removeWithPath(String path) throws IOException {
        return 0;
    }
}
