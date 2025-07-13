// 3366. Minimum Array Sum

import java.util.Arrays;

public class P3366 {
    public int minArraySum(int[] nums, int kk, int op1, int op2) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][op1 + 1][op2 + 1];
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i <= op1; i++) {
            for (int j = 0; j <= op2; j++) {
                dp[0][i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= op1; j++) {
                for (int k = 0; k <= op2; k++) {
                    dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], dp[i][j][k] + nums[i]);
                    if (j + 1 <= op1) {
                        dp[i + 1][j + 1][k] = Math.min(dp[i + 1][j + 1][k], dp[i][j][k] + (nums[i] + 1) / 2);
                    }
                    if (k + 1 <= op2 && nums[i] >= kk) {
                        dp[i + 1][j][k + 1] = Math.min(dp[i + 1][j][k + 1], dp[i][j][k] + nums[i] - kk);
                    }
                    if (j + 1 <= op1 && k + 1 <= op2 && nums[i] >= kk) {
                        int v1 = nums[i] - kk;
                        dp[i + 1][j + 1][k + 1] = Math.min(dp[i + 1][j + 1][k + 1], dp[i][j][k] + (v1 + 1) / 2);
                        int v2 = (nums[i] + 1) / 2;
                        if (v2 >= kk) {
                            dp[i + 1][j + 1][k + 1] = Math.min(dp[i + 1][j + 1][k + 1], dp[i][j][k] + v2 - kk);
                        }
                    }
                }
            }
        }

        return dp[n][op1][op2];
    }
}
