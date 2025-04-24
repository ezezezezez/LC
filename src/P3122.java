import java.util.Arrays;

// 3122. Minimum Number of Operations to Satisfy Conditions
public class P3122 {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][10];
        for (int[] row : f) Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 0; i < 10; i++) {
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (grid[j][n - 1] == i) cnt++;
            }
            f[n - 1][i] = m - cnt;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                int cnt = 0;
                for (int p = 0; p < m; p++) {
                    if (grid[p][i] == j) {
                        cnt++;
                    }
                }
                for (int k = 0; k < 10; k++) {
                    if (k == j) continue;
                    f[i][j] = Math.min(f[i][j], f[i + 1][k] + m - cnt);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) ret = Math.min(ret, f[0][i]);
        return ret;
    }
}
