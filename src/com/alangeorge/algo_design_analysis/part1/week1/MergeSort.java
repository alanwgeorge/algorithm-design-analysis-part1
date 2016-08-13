package com.alangeorge.algo_design_analysis.part1.week1;

import java.util.Arrays;

public class MergeSort {

    public static int[] sort(int[] in) {
        if (in.length == 1) return in;
        if (in.length == 2) {
            if (in[0] > in[1]) {
                int temp = in[0];
                in[0] = in[1];
                in[1] = temp;
            }
            return in;
        } else {
            int[] part1 = new int[in.length/2];
            int[] part2 = new int[in.length/2 + in.length % 2];

            System.arraycopy(in, 0, part1, 0, part1.length);
            System.arraycopy(in, part1.length, part2, 0, part2.length);

            return merge(sort(part1), sort(part2));
        }
    }

    public static int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];

        int ai = 0;
        int bi = 0;
        for (int mi = 0; mi < merged.length; mi++) {
            if (ai == a.length){
                merged[mi] = b[bi++];
                continue;
            }

            if (bi == b.length) {
                merged[mi] = a[ai++];
                continue;
            }

            if (a[ai] < b[bi]) {
                merged[mi] = a[ai++];
            } else {
                merged[mi] = b[bi++];
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] unsorted = {8, 5, 4, 2, 6, 9, 1, 3, 7, 0, -1};

        int[] sorted = MergeSort.sort(unsorted);

        System.out.println(Arrays.toString(unsorted));
        System.out.println(Arrays.toString(sorted));
    }
}
