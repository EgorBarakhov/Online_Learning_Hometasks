package ru.kpfu.itis.barakhov.ands;

import java.util.Arrays;

public class MergeSort {
    private static int power;
    private static int arrayLengthPowerOfTwo;

    public static void main(String[] args) {
        int[] sourceArray = { 12, 0, -8, 587, 69, 90, 83, -412, 25, -999, 4 };
        int[] filledArray = fillToPowerOfTwo(sourceArray);
        System.out.println(Arrays.toString(filledArray));
        int groupLength = 1;
        int[] mergedArray = new int[0];
        for (int i = 1; i <= power; i++) {
            groupLength *= 2;
            int groups = arrayLengthPowerOfTwo / groupLength;
            for (int j = 0; j < groups; j++) {
                int[] arrayToMerge = new int[groupLength];
                System.arraycopy(filledArray, groupLength * j, arrayToMerge, 0, groupLength);
                mergedArray = merge(arrayToMerge);
                System.arraycopy(mergedArray, 0, filledArray, groupLength * j, groupLength);
            }
        }
        System.arraycopy(mergedArray, 0, sourceArray, 0, sourceArray.length);
        System.out.println(Arrays.toString(sourceArray));
    }

    private static int[] merge(int[] arrayToMerge) {
        int halfLength = arrayToMerge.length / 2;
        int[] left = new int[halfLength];
        int[] right = new int[halfLength];
        System.arraycopy(arrayToMerge, 0, left, 0, halfLength);
        System.arraycopy(arrayToMerge, halfLength, right, 0, halfLength);
        int[] mergedArray = new int[halfLength * 2];
        int leftCount = 0, rightCount = 0, mergeCount = 0;
        for (int i = 0; i < arrayToMerge.length; i++) {
            if (leftCount == halfLength) {
                for (int j = rightCount; j < halfLength; j++) {
                    mergedArray[mergeCount] = right[rightCount];
                    mergeCount++;
                    rightCount++;
                }
                break;
            }
            if (rightCount == halfLength) {
                for (int j = leftCount; j < halfLength; j++) {
                    mergedArray[mergeCount] = left[leftCount];
                    mergeCount++;
                    leftCount++;
                }
                break;
            }
            if (left[leftCount] < right[rightCount]) {
                mergedArray[mergeCount] = left[leftCount];
                leftCount++;
            } else {
                mergedArray[mergeCount] = right[rightCount];
                rightCount++;
            }
            mergeCount++;
        }
        return mergedArray;
    }

    private static int[] fillToPowerOfTwo(int[] sourceArray) {
        int sourceArrayLength = sourceArray.length;
        int maxEl = sourceArray[0];
        for (int i = 1; i < sourceArrayLength; i++) {
            maxEl = Math.max(maxEl, sourceArray[i]);
        }
        maxEl++;
        arrayLengthPowerOfTwo = 1;
        power = 0;
        while (arrayLengthPowerOfTwo < sourceArrayLength) {
            arrayLengthPowerOfTwo *= 2;
            power++;
        }
        int[] filledArray = new int[arrayLengthPowerOfTwo];
        System.arraycopy(sourceArray, 0, filledArray, 0, sourceArrayLength);
        for (int i = sourceArrayLength; i < arrayLengthPowerOfTwo; i++) {
            filledArray[i] = maxEl;
        }
        return filledArray;
    }
}
