package ru.mail.polis.homework.io;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFile {

    /**
     * Реализовать копирование папки из pathFrom в pathTo. Скопировать надо все внутренности
     * Файлы копировать ручками через стримы. Используем новый API
     * В тесте для создания нужных файлов для первого запуска надо раскомментировать код в setUp()
     * 3 тугрика
     */
    public static void copyFiles(String pathFrom, String pathTo) throws IOException {
        Path sourcePath = Paths.get(pathFrom);
        Path distPath = Paths.get(pathTo);
        Path root = Files.isDirectory(sourcePath) ? distPath : distPath.getParent();

        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Files.createDirectory(dir.relativize(sourcePath).resolve(distPath));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    copyFiles(file, file.relativize(sourcePath).resolve(distPath));
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFiles(Path src, Path dst) throws IOException {
        try (InputStream is = Files.newInputStream(src)) {
            try (OutputStream os = Files.newOutputStream(dst)) {
                byte[] buffer = new byte[1024];
                int readSize;
                while ((readSize = is.read(buffer)) > 0) {
                    os.write(buffer, 0, readSize);
                }
            }
        }
    }
}


