package pro.sky.java.course2.lesson2_14;

import java.util.Random;

public class MainIntList {
    private static Integer[] generateRandomArray(Integer[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000);
        }
        return arr;
    }

    public static void main(String[] args) {
        checkTime();

        IntList nums = new IntListImpl();
        nums.add(5855);
        nums.add(256);
        nums.add(2596);
        nums.add(8526);
        nums.add(741);
    }

    private static void checkTime() {
        long start;

        IntList nums1 = new IntListImpl();
        Integer[] num1 = generateRandomArray(nums1.toArray());
        start = System.currentTimeMillis();
        sortBubble(num1);
        System.out.println("Bubble Sort: " + (System.currentTimeMillis() - start));

        IntList nums2 = new IntListImpl();
        Integer[] num2 = generateRandomArray(nums2.toArray());
        start = System.currentTimeMillis();
        sortSelection(num2);
        System.out.println("Selection Sort: " + (System.currentTimeMillis() - start));

        IntList nums3 = new IntListImpl();
        Integer[] num3 = generateRandomArray(nums3.toArray());
        start = System.currentTimeMillis();
        sortInsertion(num3);
        System.out.println("Insert Sort: " + (System.currentTimeMillis() - start));
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
