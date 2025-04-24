import java.util.ArrayList;
import java.util.List;

// 3212. Count Submatrices With Equal Frequency of X and Y
public class P3212 {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] x = new int[m + 1][n + 1], y = new int[m + 1][n + 1];
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x[i + 1][j + 1] = x[i + 1][j] + x[i][j + 1] - x[i][j];
                x[i + 1][j + 1] += grid[i][j] == 'X' ? 1 : 0;
                y[i + 1][j + 1] = y[i + 1][j] + y[i][j + 1] - y[i][j];
                y[i + 1][j + 1] += grid[i][j] == 'Y' ? 1 : 0;
                if (x[i + 1][j + 1] > 0 && x[i + 1][j + 1] == y[i + 1][j + 1]) ret++;
            }
        }
        return ret;
    }
}
