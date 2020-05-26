package ru.kpfu.itis.barakhov.ands;

import java.util.Arrays;

public class RecursiveMergeSort {
    public static void main(String[] args) {
        int[] array1 = { 12, 0, -8, 587, 69, 90, 83, -412, 25, -999, 4 };
        int[] buffer1 = Arrays.copyOf(array1, array1.length);
        int[] buffer2 = new int[array1.length];
        int[] result = mergeSort(buffer1, buffer2, 0, array1.length);
        System.out.println(Arrays.toString(result));
    }
    public static int[] mergeSort(int[] buffer1, int[] buffer2, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }

        int middle = startIndex + (endIndex - startIndex) / 2;
        int[] sorted1 = mergeSort(buffer1, buffer2, startIndex, middle);
        int[] sorted2 = mergeSort(buffer1, buffer2, middle, endIndex);

        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        int[] result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result[destIndex++] = sorted1[index1] < sorted2[index2] ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endIndex) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
}