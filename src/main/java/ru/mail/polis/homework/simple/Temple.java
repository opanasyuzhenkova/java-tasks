package ru.mail.polis.homework.simple;

import java.util.Arrays;

public class Temple {
    public static String arraysWork(int n) {
        int[] array1 = new int[n];
        System.out.println(Arrays.toString(array1));
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(array2));
        array1[0] = array2[0] + array2[3];
        try {
            int tenElement = array1[array2.length];
            System.out.println(tenElement);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        return Arrays.toString(array1);
    }

    public static void main(String[] args) {
        System.out.println(arraysWork(10));
    }
}
