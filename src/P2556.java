public class P2556 {
    int m, n;
    public boolean isPossibleToCutPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                if (i - 1 >= 0) dp[i][j] += dp[i - 1][j];
                if (j - 1 >= 0) dp[i][j] += dp[i][j - 1];
            }
        }
        if (dp[m - 1][n - 1] == 0) return true;
        int[][] dp2 = new int[m][n];
        dp2[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) continue;
                if (i + 1 < m) dp2[i][j] += dp2[i + 1][j];
                if (j + 1 < n) dp2[i][j] += dp2[i][j + 1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                if (i == 0 && j == 0) continue;
                if (i == m - 1 && j == n - 1) continue;
                if (dp[i][j] * dp2[i][j] == dp[m - 1][n - 1]) return true;
            }
        }
        return false;
    }
}
