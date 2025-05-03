import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 3290. Maximum Multiplication Score
public class P3290 {
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[][] f = new long[5][n + 1];
        for (long[] row : f) Arrays.fill(row, Math.round(-1e11));
        for (int i = 0; i <= n; i++) f[0][i] = 0L;
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j + 1] = Math.max(f[i][j], f[i - 1][j] + (long) a[i - 1] * b[j]);
            }
        }
        return f[4][n];
    }
}
