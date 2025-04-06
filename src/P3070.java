import java.util.ArrayList;
import java.util.List;

// 3070. Count Submatrices with Top-Left Element and Sum Less Than k
public class P3070 {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int ret = 0;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i + 1][j + 1] = f[i][j + 1] + f[i + 1][j] - f[i][j] + grid[i][j];
                if (f[i + 1][j + 1] <= k) ret++;
            }
        }
        return ret;
    }
}
