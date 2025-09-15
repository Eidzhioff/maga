package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Task1 {
    private static int[] genArray(int length) {
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = rnd.nextInt(1000);
        }
        return array;
    }

    //Сортировка через отдельный массив. Информация по сортивовке https://www.baeldung.com/java-sorting
    public static void getSortArray(int[] arrayToSort) {

        Integer[] compareArray = new Integer[arrayToSort.length];

        for (int i = 0; i < arrayToSort.length; i++) {
            compareArray[i] = Integer.valueOf(arrayToSort[i]);
        }

        Arrays.sort(compareArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                int compareBoolean = Integer.compare(a % 2, b % 2);

                if (compareBoolean == 0) {
                    return Integer.compare(a, b);
                }

                return compareBoolean;
            }
        });

        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = compareArray[i];
        }
    }

    //Кастомный compareBoolean
    private static boolean OverrideCompare(int a, int b) {

        if (a % 2 == 0 && b % 2 != 0) {
            return true;
        }

        if (a % 2 != 0 && b % 2 == 0) {
            return false;
        }

        return a < b;
    }

    //Кастомная сортировка
    public static void getSortArrayINT(int[] arrayToSort) {

        for (int i = 0; i < arrayToSort.length - 1; i++) {

            int left_target = i;

            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (OverrideCompare(arrayToSort[j], arrayToSort[left_target])) {
                    left_target = j;
                }
            }

            if (left_target != i) {
                int swap = arrayToSort[i];
                arrayToSort[i] = arrayToSort[left_target];
                arrayToSort[left_target] = swap;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = genArray(10);
        System.out.println("Исходный массив " + Arrays.toString(array));

        //sort + Comparator реализация через int
        getSortArrayINT(array);
        System.out.println("Реализация через встроенный Integer " + Arrays.toString(array));

        //Реализация через отдельный массив, но при этом возвращаем исходный (что не нарушает поставленную задачу косвенно)
        getSortArray(array);
        System.out.println("Своя реализация через int[] " + Arrays.toString(array));
    }
}