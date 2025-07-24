// 3619. Count Islands With Total Value Divisible by K

public class P3619 {
    boolean[][] vis;
    int m, n, k;
    int[][] grid;
    long sum;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.k = k;
        this.grid = grid;
        vis = new boolean[m][n];

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] > 0) {
                    sum = 0;
                    dfs(i, j);
                    if (sum % k == 0) ret++;
                }
            }
        }

        return ret;
    }

    void dfs(int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || vis[x][y]) return;
        vis[x][y] = true;
        sum += grid[x][y];
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
