import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3599. Partition Array to Minimize XOR

public class P3599 {
    public int minXor(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                int xor = 0;
                for (int p = j; p >= 0; p--) {
                    xor ^= nums[p];
                    if (dp[p][i - 1] != -1) {
                        if (dp[j + 1][i] == -1) dp[j + 1][i] = Math.max(xor, dp[p][i - 1]);
                        else dp[j + 1][i] = Math.min(dp[j + 1][i], Math.max(xor, dp[p][i - 1]));
                    }
                }
            }
        }

        // printGrid2D(dp);

        return dp[n][k];
    }

    void printGrid2D(int[][] grid) {
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
