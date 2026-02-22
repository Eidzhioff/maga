package org.example;

import java.util.Arrays;
import java.util.Random;

public class RE_Task1 {
    private static int[] genArray(int length) {
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = rnd.nextInt(1000);
        }
        return array;
    }
    public static void getSortArrayINT(int[] arrayToSort) {
        int left_target = 0;
        int right_target = arrayToSort.length - 1;

        while (left_target < right_target) {

            while (left_target < right_target && arrayToSort[left_target] % 2 != 0) {
                left_target++;
            }

            while (left_target < right_target && arrayToSort[right_target] % 2 == 0) {
                right_target--;
            }

            if (left_target < right_target) {
                int swap = arrayToSort[left_target];
                arrayToSort[left_target] = arrayToSort[right_target];
                arrayToSort[right_target] = swap;
                left_target++;
                right_target--;
            }
        }
    }

    public static void main(String[] args) {
        //Извините, не внимательно прочел задание и усложнил себе задачу.
        //Думал, что две части массива обязательно долны быть отсортированны.
        //Так что тут я просто запускаю два итератора с обоих сторон, поэтому все должно быть быстрее.
        //И на этот раз сначала нечетыне
        int[] array = genArray(10);
        System.out.println("Исходный массив " + Arrays.toString(array));

        getSortArrayINT(array);
        System.out.println("Правильная реализация " + Arrays.toString(array));

    }
}