public class P2556_2 {
    int m, n;
    public boolean isPossibleToCutPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if (!dfs(grid, 0, 0)) return true;
        return !dfs(grid, 0, 0);
    }

    boolean dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (grid[x][y] == 0) return false;
        if (x == m - 1 && y == n - 1) return true;
        boolean ret = dfs(grid, x + 1, y);
        ret = ret || dfs(grid, x, y + 1);
        if (x != 0 || y != 0) grid[x][y] = 0;
        return ret;
    }
}
