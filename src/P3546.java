import java.util.Arrays;

// 3546. Equal Sum Grid Partition I
public class P3546 {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] sum = new long[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }
        for (int i = 0; i < m - 1; i++) {
            if (sum[m][n] - sum[i + 1][n] == sum[i + 1][n]) return true;
        }
        for (int j = 0; j < n - 1; j++) {
            if (sum[m][n] - sum[m][j + 1] == sum[m][j + 1]) return true;
        }
        return false;
    }
}
