package com.alangeorge.algo_design_analysis.part1.week2;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static int count = 0;

    public static int[] partition(int[] ints) {
        count = 0;


        int splitI = 1;
        int temp;

        for (int targetI = 2; targetI < ints.length; targetI++) {
            if (ints[targetI] < ints[0]) {
                count++;
                temp = ints[splitI];
                ints[splitI] = ints[targetI];
                ints[targetI] = temp;
                splitI++;
            }
        }

//        temp = ints[0];
//        ints[0] = ints[splitI - 1];
//        ints[splitI - 1] = temp;

        System.out.printf("splitI = %d\n", splitI);
        System.out.printf("count = %d\n", count);

        return ints;
    }

    public static void main(String[] args) {
        int[] in = {3, 8, 2, 5, 1, 4, 7, 6};

        System.out.printf("%s\n", Arrays.toString(partition(in)));

        int size = 10;
        int[] in2 = new int[size];
        int[] index = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            in2[i] = random.nextInt(100);
            index[i] = i;
        }

        System.out.printf("%s\n", Arrays.toString(in2));
        System.out.printf("%s\n", Arrays.toString(index));
        int[] result = partition(in2);
        System.out.printf("%s\n", Arrays.toString(in2));
        System.out.printf("%s\n", in2[count]);

    }
}
