import java.util.*;
import java.io.*;
import java.lang.*;

// 1020. Number of Enclaves

public class P1020 {
    int ret = 0, size = 0;
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    size = 0;
                    if (dfs(grid, i, j, vis)) {
                        // System.out.println(i + " " + j);
                        ret += size;
                    }
                }
            }
        }
        return ret;
    }

    boolean dfs(int[][] grid, int x, int y, boolean[][] vis) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (vis[x][y] || grid[x][y] == 0) return true;
        vis[x][y] = true;
        size++;
        boolean a = dfs(grid, x - 1, y, vis);
        boolean b = dfs(grid, x + 1, y, vis);
        boolean c = dfs(grid, x, y - 1, vis);
        boolean d = dfs(grid, x, y + 1, vis);
        return a && b && c && d;
    }
}
