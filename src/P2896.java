import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 2896. Apply Operations to Make Two Strings Equal
public class P2896 {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = c1[i] == c2[i] ? 0 : 1;
        }
        int[][][] dp = new int[n + 1][2][2];
        for (int[][] grid : dp) {
            for (int[] row : grid) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    int cur = f[i] ^ j;
                    if (cur == 1) {
                        dp[i + 1][1][k] = Math.min(dp[i + 1][1][k], dp[i][j][k] + 1);
                        if (k == 1) {
                            dp[i + 1][0][0] = Math.min(dp[i + 1][0][0], dp[i][j][k]);
                        } else {
                            dp[i + 1][0][1] = Math.min(dp[i + 1][0][1], dp[i][j][k] + x);
                        }
                    } else {
                        dp[i + 1][0][k] = Math.min(dp[i + 1][0][k], dp[i][j][k]);
                    }
                }
            }
        }
        // for (int[][] grid : dp) {
        //     System.out.println();
        //     for (int[] row : grid) {
        //         System.out.println(Arrays.toString(row));
        //     }
        // }
        return dp[n][0][0] >= Integer.MAX_VALUE / 2 ? -1 : dp[n][0][0];
    }
}
