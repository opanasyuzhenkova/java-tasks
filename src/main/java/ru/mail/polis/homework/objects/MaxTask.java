package ru.mail.polis.homework.objects;

import java.util.Arrays;

public class MaxTask {

    /**
     * Вам дан массив и количество элементов в возвращаемом массиве
     * Вернуть нужно массив из count максимальных элементов array, упорядоченный по убыванию.
     * Если массив null или его длина меньше count, то вернуть null
     * Например ({1, 3, 10, 11, 22, 0}, 2) -> {22, 11}
     * ({1, 3, 22, 11, 22, 0}, 3) -> {22, 22, 11}
     * НЕЛЬЗЯ СОРТИРОВАТЬ массив array и его копии
     * 4 тугрика
     */
    public static int[] getMaxArray(int[] array, int count) {
        if (array == null || array.length < count) {
            return null;
        }
        if (count == 0) {
            return new int[]{};
        }
        int[] res = Arrays.copyOf(array, count); // делаем копию массива с 0 до count
        Arrays.sort(res);
        for (int i = count; i < array.length; i++) {
            if (array[i] <= res[0]) {
                continue;
            }
            insert(res, array[i]);
        }
        int temp;
        for (int i = 0; i < count / 2; i++) {
            temp = res[i];
            res[i] = res[count - i - 1];
            res[count - i - 1] = temp;
        }
        return res;
    }

    public static void insert(int[] arr, int e) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < e) {
                arr[i - 1] = arr[i];
            } else {
                arr[i - 1] = e;
                return;
            }
        }
        arr[arr.length - 1] = e;
    }
}
