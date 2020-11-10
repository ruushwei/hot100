package com.zhw.leetcode.hot100.NO50to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 * @author 张宏伟 <zhanghongwei@kuaishou.com>
 * Created on 2020-11-10
 */
public class NO56 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        int length = intervals.length;
        int left = 0, right = 1;
        int[][] merges = new int[length][];
        int mergeIndex = -1;

        while (left < length && right < length) {
            int[] leftInterval = mergeIndex == -1 ? intervals[left] : merges[mergeIndex];
            int[] rightInterval = intervals[right];

            if (leftInterval[1] >= rightInterval[0] && leftInterval[0] <= rightInterval[1]) {
                // 合并
                if (mergeIndex == -1) {
                    mergeIndex++;
                }
                merges[mergeIndex] = new int[] {Math.min(leftInterval[0],rightInterval[0]),
                        Math.max(leftInterval[1],rightInterval[1])};
            } else {
                // 不能合并
                if (mergeIndex == -1) {
                    merges[++mergeIndex] = leftInterval;
                }
                merges[++mergeIndex] = rightInterval;
            }
            left++;
            right++;
        }
        merges = Arrays.copyOf(merges, mergeIndex + 1);
        return merges;
    }

    public static void main(String[] args) {
        int[][] merge = new NO56().merge(new int[][] {{1, 4}, {0, 2}, {3, 5}});
        for (int i = 0; i < merge.length; i++) {
            for (int j = 0; j < merge[i].length; j++) {
                System.out.print(merge[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] answerMerge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
