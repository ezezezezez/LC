import java.io.*;
import java.lang.*;
import java.util.*;

// 1905. Count Sub Islands

public class P1905_2 {
    int m, n;
    int[][] vis;
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid2.length; n = grid2[0].length;
        vis = new int[m][n];
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == 0 && grid2[i][j] == 1) {
                    if (dfs(grid1, grid2, i, j)) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    boolean dfs(int[][] grid1, int[][] grid2, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid2[x][y] == 0) return true;
        if (vis[x][y] != 0) return vis[x][y] == 1;
        if (grid1[x][y] == 0) {
            vis[x][y] = -1;
            return false;
        }
        vis[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (!dfs(grid1, grid2, nx, ny)) {
                vis[x][y] = -1;
                return false;
            }
        }
        return true;
    }
}
