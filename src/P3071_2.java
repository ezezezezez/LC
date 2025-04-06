// 3071. Minimum Operations to Write the Letter Y on a Grid
public class P3071_2 {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int[] cnt1 = new int[3], cnt2 = new int[3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j && i <= n / 2) || (i + j == n - 1 && i <= n / 2) || (i > n / 2 && j == n / 2)) {
                    cnt1[grid[i][j]]++;
                } else {
                    cnt2[grid[i][j]]++;
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int u = 0; u <= 2; u++) {
            for (int v = 0; v <= 2; v++) {
                if (u == v) continue;
                ret = Math.min(ret, n * n - cnt1[u] - cnt2[v]);
            }
        }
        return ret;
    }
}
