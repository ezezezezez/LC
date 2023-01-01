import java.io.*;
import java.lang.*;
import java.util.*;

// 1905. Count Sub Islands

public class P1905 {
    int m, n;
    int[][] memo;
    boolean[][] vis;
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid2.length; n = grid2[0].length;
        memo = new int[m][n];
        vis = new boolean[m][n];
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] == 0 && grid2[i][j] == 1) {
                    boolean res = dfs(grid1, grid2, i, j);
                    ret += res ? 1 : 0;
                    mark(grid2, i, j, res);
                }
            }
        }
        return ret;
    }

    boolean dfs(int[][] grid1, int[][] grid2, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid2[x][y] == 0 || vis[x][y]) return true;
        vis[x][y] = true;
        if (grid1[x][y] == 0) return false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (!dfs(grid1, grid2, nx, ny)) return false;
        }
        return true;
    }

    void mark(int[][] grid, int x, int y, boolean res) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || memo[x][y] != 0) return;
        memo[x][y] = res ? 1 : -1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            mark(grid, nx, ny, res);
        }
    }
}
