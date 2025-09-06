// 3651. Minimum Cost Path with Teleportations

import java.util.Arrays;

public class P3651 {
    public int minCost(int[][] grid, int k) {
        int INF = Integer.MAX_VALUE / 2;
        int m = grid.length, n = grid[0].length;
        int maxV = 0;
        for (int[] row : grid) {
            for (int v : row) {
                maxV = Math.max(maxV, v);
            }
        }
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + grid[i][j]);
                if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }

        int[] f = new int[maxV + 1];
        for (int t = 1; t <= k; t++) {
            Arrays.fill(f, INF);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = grid[i][j];
                    f[v] = Math.min(f[v], dp[i][j]);
                }
            }
            int mn = INF;
            for (int i = maxV; i >= 0; i--) {
                mn = Math.min(mn, f[i]);
                f[i] = mn;
            }
            int[][] dpNxt = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int v = grid[i][j];
                    dpNxt[i][j] = f[v];
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) dpNxt[i][j] = Math.min(dpNxt[i][j], dpNxt[i - 1][j] + grid[i][j]);
                    if (j > 0) dpNxt[i][j] = Math.min(dpNxt[i][j], dpNxt[i][j - 1] + grid[i][j]);
                }
            }
            dp = dpNxt;
        }
        return dp[m - 1][n - 1];
    }
}
