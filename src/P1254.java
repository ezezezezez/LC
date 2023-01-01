import java.util.*;
import java.io.*;
import java.lang.*;

// 1254. Number of Closed Islands

public class P1254 {
    int ret = 0;
    boolean[][] vis, succeeded;
    int m, n;
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int closedIsland(int[][] grid) {
        m = grid.length; n = grid[0].length;
        vis = new boolean[m][n]; succeeded = new boolean[m][n];
        for (boolean[] row : succeeded) Arrays.fill(row, true);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !vis[i][j]) {
                    if (dfs(grid, i, j)) ret++;
                }
            }
        }

        return ret;
    }

    boolean dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (grid[x][y] == 1 || vis[x][y]) return succeeded[x][y];
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (!dfs(grid, nx, ny)) {
                return succeeded[x][y] = false;
            }
        }
        return true;
    }
}
