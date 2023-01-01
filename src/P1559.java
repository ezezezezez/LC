import java.io.*;
import java.lang.*;
import java.util.*;

// 1559. Detect Cycles in 2D Grid
public class P1559 {
    int[][] vis;
    Set<Integer> set;
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        vis = new int[m][n];
        for (int[] row : vis) Arrays.fill(row, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == -1) {
                    set = new HashSet<>();
                    if (dfs(grid, i, j, 0, grid[i][j])) return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] grid, int x, int y, int cur, char c) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != c) return false;
        if (vis[x][y] >= 0) {
            return set.contains(x * n + y) && cur - vis[x][y] >= 4;
        }
        // System.out.println(x + " " + y + " " + c);
        vis[x][y] = cur;
        set.add(x * n + y);
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i], ny = y + dir[i + 1];
            if (dfs(grid, nx, ny, cur + 1, c)) return true;
        }
        return false;
    }
}
