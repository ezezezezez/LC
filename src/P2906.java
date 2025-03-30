// 2906. Construct Product Matrix
public class P2906 {
    long mod = 12345;
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] ret = new int[n][m];
        long prod = 1;
        long[][] pre = new long[n + 1][m + 1], suf = new long[n + 1][m + 1];
        pre[0][m] = suf[n][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    pre[i + 1][j + 1] = pre[i][m] * grid[i][j] % mod;
                } else {
                    pre[i + 1][j + 1] = pre[i + 1][j] * grid[i][j] % mod;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (j == m - 1) {
                    suf[i][j] = suf[i + 1][0] * grid[i][j] % mod;
                } else {
                    suf[i][j] = suf[i][j + 1] * grid[i][j] % mod;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long f = j == 0 ? pre[i][m] : pre[i + 1][j];
                long g = j == m - 1 ? suf[i + 1][0] : suf[i][j + 1];
                ret[i][j] = (int) (f * g % mod);
            }
        }
        return ret;
    }
}
