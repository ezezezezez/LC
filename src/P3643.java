// 3643. Flip Square Submatrix Vertically
public class P3643 {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = grid[i][j];
            }
        }
        for (int i = x; i < x + k / 2; i++) {
            int ri = x + k - 1 - (i - x);
            for (int j = y; j < y + k; j++) {
                int t = ret[i][j];
                ret[i][j] = ret[ri][j];
                ret[ri][j] = t;
            }
        }
        return ret;
    }
}
