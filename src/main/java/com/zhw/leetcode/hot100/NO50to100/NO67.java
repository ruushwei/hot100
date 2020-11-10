package com.zhw.leetcode.hot100.NO50to100;

/**
 * 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * @author 张宏伟 <zhanghongwei@kuaishou.com>
 * Created on 2020-11-10
 */
public class NO67 {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return -1;
        }

        int row = grid.length, column = grid[0].length;
        int[][] dp = new int[row][column];

        for (int i = 0; i < row; i++) {
            dp[i][0] = i == 0 ? grid[0][0] : dp[i-1][0] + grid[i][0];
        }

        for (int j = 0; j < column; j++) {
            dp[0][j] = j == 0 ? grid[0][0] : dp[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[row-1][column-1];
    }

    public static void main(String[] args) {
        int pathSum = new NO67().minPathSum(new int[][] {
                {1,2,3}, {4,5,6}
        });
        System.out.println(pathSum);
    }
}
