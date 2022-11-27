import java.util.*;
import java.io.*;
import java.lang.*;

// 1219. Path with Maximum Gold

public class P1219 {
    int ret = 0, cur = 0;
    int m, n;
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int getMaximumGold(int[][] grid) {
        m = grid.length; n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    cur = 0;
                    dfs(grid, i, j, new boolean[m][n]);
                }
            }
        }
        return ret;
    }

    void dfs(int[][] grid, int x, int y, boolean[][] vis) {
        vis[x][y] = true;
        cur += grid[x][y];
        ret = Math.max(ret, cur);
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            if (vis[nx][ny]) continue;
            if (grid[nx][ny] == 0) continue;
            dfs(grid, nx, ny, vis);
        }
        vis[x][y] = false;
        cur -= grid[x][y];
    }
}
