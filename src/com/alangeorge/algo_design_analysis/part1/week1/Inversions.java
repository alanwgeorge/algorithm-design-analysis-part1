package com.alangeorge.algo_design_analysis.part1.week1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Inversions {
    public static int count = 0;

    public static Set<Inversion> inversionsB(int[] ints) {
        Set<Inversion> inversions = new HashSet<>();

        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                count++;
//                System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
                if (ints[i] > ints[j]) {
                    inversions.add(new Inversion(ints[i], ints[j]));
                    System.out.printf("(i, j) = (%d, %d), (ints[i], ints[j]) = (%d, %d)\n", i, j, ints[i], ints[j]);
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

    public static void main(String[] args) {
//        int[] in = {1, 3, 5, 2, 4, 6};
//        int[] in = {1, 5, 3, 2, 6, 4};
        int[] in = {8, 7, 6, 5, 4, 3, 2, 1};

        Set<Inversion> inversions = Inversions.inversionsR(in);

        System.out.println(Arrays.toString(inversions.toArray()));
        System.out.printf("count: %d\n", Inversions.count);
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
