// 2684. Maximum Number of Moves in a Grid
public class P2684 {
    public int maxMoves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ret = 0;
        boolean[][] b = new boolean[m][n];
        for (int i = 0; i < m; i++) b[i][0] = true;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                if (i - 1 >= 0 && b[i - 1][j - 1] && grid[i - 1][j - 1] < v) {
                    b[i][j] = true;
                    ret = j;
                    continue;
                }
                if (b[i][j - 1] && grid[i][j - 1] < v) {
                    b[i][j] = true;
                    ret = j;
                    continue;
                }
                if (i + 1 < m && b[i + 1][j - 1] && grid[i + 1][j - 1] < v) {
                    b[i][j] = true;
                    ret = j;
                    continue;
                }
            }
        }
        return ret;
    }
}
