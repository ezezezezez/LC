import java.util.HashSet;
import java.util.Set;

// 2658. Maximum Number of Fish in a Grid
public class P2658 {
    int m, n;
    boolean[][] vis;
    int ret;

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] > 0) {
                    ret = Math.max(ret, dfs(grid, i, j));
                }
            }
        }
        return ret;
    }

    int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return 0;
        if (vis[x][y]) return 0;
        if (grid[x][y] == 0) return 0;
        vis[x][y] = true;
        int ret = grid[x][y];
        ret += dfs(grid, x + 1, y);
        ret += dfs(grid, x - 1, y);
        ret += dfs(grid, x, y + 1);
        ret += dfs(grid, x, y - 1);
        return ret;
    }
}
