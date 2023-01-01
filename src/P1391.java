import java.io.*;
import java.lang.*;
import java.util.*;

// 1386. Cinema Seat Allocation

public class P1391 {
    int m, n;
    boolean[][] vis;
    public boolean hasValidPath(int[][] grid) {
        m = grid.length; n = grid[0].length;
        vis = new boolean[m][n];
        return dfs(grid, 0, 0, -1);
    }

    boolean dfs(int[][] grid, int x, int y, int pre) {
        if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) return false;
        if (pre != -1) {
            if (pre == 1) {
                if (grid[x][y] != 2 && grid[x][y] != 3 && grid[x][y] != 4) return false;
            } else if (pre == 2) {
                if (grid[x][y] != 1 && grid[x][y] != 3 && grid[x][y] != 5) return false;
            } else if (pre == 3) {
                if (grid[x][y] != 2 && grid[x][y] != 5 && grid[x][y] != 6) return false;
            } else {
                if (grid[x][y] != 1 && grid[x][y] != 4 && grid[x][y] != 6) return false;
            }
        }
        if (x == m - 1 && y == n - 1) return true;

        vis[x][y] = true;

        if (grid[x][y] == 1) {
            if (dfs(grid, x, y + 1, 2)) return true;
            if (dfs(grid, x, y - 1, 4)) return true;
        } else if (grid[x][y] == 2) {
            if (dfs(grid, x + 1, y, 3)) return true;
            if (dfs(grid, x - 1, y, 1)) return true;
        } else if (grid[x][y] == 3) {
            if (dfs(grid, x, y - 1, 4)) return true;
            if (dfs(grid, x + 1, y, 3)) return true;
        } else if (grid[x][y] == 4) {
            if (dfs(grid, x, y + 1, 2)) return true;
            if (dfs(grid, x + 1, y, 3)) return true;
        } else if (grid[x][y] == 5) {
            if (dfs(grid, x, y - 1, 4)) return true;
            if (dfs(grid, x - 1, y, 1)) return true;
        } else {
            if (dfs(grid, x, y + 1, 2)) return true;
            if (dfs(grid, x - 1, y, 1)) return true;
        }

        return false;
    }
}
