// 2850. Minimum Moves to Spread Stones Over Grid
public class P2850 {
    int ret = Integer.MAX_VALUE;
    public int minimumMoves(int[][] grid) {
        dfs(grid, 0);
        return ret;
    }

    void dfs(int[][] grid, int time) {
        if (done(grid)) {
            ret = Math.min(ret, time);
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    for (int u = 0; u < 3; u++) {
                        for (int v = 0; v < 3; v++) {
                            if (grid[u][v] > 1) {
                                grid[i][j]++;
                                grid[u][v]--;
                                dfs(grid, time + Math.abs(u - i) + Math.abs(v - j));
                                grid[i][j]--;
                                grid[u][v]++;
                            }
                        }
                    }
                }
            }
        }
    }

    boolean done(int[][] grid) {
        for (int[] row : grid) {
            for (int v : row) {
                if (v != 1) return false;
            }
        }
        return true;
    }
}
