package com.alangeorge.algo_design_analysis.part1.week1;

import java.io.*;
import java.util.*;

public class Inversions {
    public static int count = 0;

//    public static Set<Inversion> inversionsB(int[] ints) {
//        Set<Inversion> inversions = new HashSet<>();
//
//        for (int i = 0; i < ints.length; i++) {
//            for (int j = i + 1; j < ints.length; j++) {
//                count++;
////                System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
//                if (ints[i] > ints[j]) {
//                    inversions.add(new Inversion(ints[i], ints[j]));
////                    System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
//                }
//            }
//        }
//
//        return inversions;
//    }

    public static long inversionsB(int[] ints) {
        long inversions = 0;

        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                count++;
//                System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
                if (ints[i] > ints[j]) {
                    inversions++;
//                    System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
                }
            }
        }

        return inversions;
    }


    public static Set<Inversion> inversionsR(int[] ints) {
        count++;
        System.out.printf("ints = %s\n", Arrays.toString(ints));
        if (ints.length < 2) return new HashSet<>();
        if (ints.length == 2) {
            if (ints[0] > ints[1]) {
                Set<Inversion> results = new HashSet<>();
                results.add(new Inversion(ints[0], ints[1]));
                return results;
            } else {
                return new HashSet<>();
            }
        } else {
            int part1Size = ints.length/2 + ints.length % 2;
            int part2Size = ints.length/2;

            int[] part1 = new int[part1Size];
            int[] part2 = new int[part2Size];

            System.arraycopy(ints, 0, part1, 0, part1.length);
            System.arraycopy(ints, part1.length, part2, 0, part2.length);

            Set<Inversion> result = new HashSet<>();

            if (part1[part1.length - 1] > part2[0]) {
                result.add(new Inversion(part1[part1.length - 1], part2[0]));
            }
            result.addAll(inversionsR(part1));
            result.addAll(inversionsR(part2));
            result.addAll(splitInversions(ints, part1Size));

            return result;
        }
    }

    public static Set<Inversion> splitInversions(int[] ints, int split) {
        Set<Inversion> results = new HashSet<>();

        int part1i = 0;
        int part2i = split;

        for (int i = 0; i < ints.length; i++) {

        }

        return results;
    }

    static long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i++;
            } else {
                arr[i+j] = right[j];
                count += left.length-i;
                j++;
            }
        }
        return count;
    }

    static long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int left[] = Arrays.copyOfRange(arr, 0, m);
        int right[] = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }

    public static int[] loadInts() throws IOException {
        File file = new File("data/IntegerArray.txt");
        FileInputStream in = new FileInputStream(file);

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        List<Integer> ints = new ArrayList<>();

        String line = null;
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            ints.add(Integer.valueOf(line));
        }

        br.close();

        int[] result = new int[ints.size()];

        for (int i = 0; i < ints.size(); i++) {
            result[i] = ints.get(i);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
//        int[] in = {1, 3, 5, 2, 4, 6};
//        int[] in = {1, 5, 3, 2, 6, 4};
//        int[] in = { 4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54 };
        int[] in = loadInts();

//        System.out.printf("count = %d\n", invCount(in));

//        Set<Inversion> inversions = inversionsB(in);
        long inversions = inversionsB(in);
//        System.out.println(Arrays.toString(inversions.toArray()));
        System.out.println(inversions);
        System.out.printf("count: %d\n", count);
    }

    public static class Inversion {
        private int a;
        private int b;

        public Inversion(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Inversion{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
