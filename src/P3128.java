import java.util.Arrays;

// 3128. Right Triangles
public class P3128 {
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long ret = 0;
        long[] f = new long[n], g = new long[n];
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ret += f[j];
                    ret += (cnt - 1) * g[j];
                    g[j]++;
                    f[j] += cnt - 1;
                }
            }
        }
        return ret;
    }
}
