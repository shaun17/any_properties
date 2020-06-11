package sw.java.elk.demo;

import java.util.Arrays;

public class Sort {
    private int[] array;

    public static void main(String[] args) {
        int[] array = new int[]{6, 3, 4, 1, 7, 0, 8, 9, 5};
//        System.out.println(Arrays.toString(maopao(array)));
        System.out.println(Arrays.toString(kuaisu(0, array.length - 1, array)));




    }

    public static int[] maopao(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public static int[] kuaisu(int low, int high, int[] array) {
        int i = low;
        int j = high;
        int key = array[low];
        if (low < high) {
            while (i < j) {
                while (i < j && key <= array[j]) {
                    j--;
                }
                array[i] = array[j];
                while (i < j && key >= array[i]) {
                    i++;
                }
                array[j] = array[i];
                array[i] = key;
            }
            kuaisu(low, i - 1, array);
            kuaisu(i + 1, high, array);
        }
        return array;
    }

    public static int erfen(int[] a, int low, int high, int target) {

        int mid = (low + high) / 2;
        if (target < mid) {
            erfen(a, low, a[mid - 1], target);
        } else if (target > mid) {
            erfen(a, mid + 1, high, target);
        } else {
            return mid;
        }
        return -1;
    }
}
