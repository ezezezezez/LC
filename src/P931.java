import java.util.*;
import java.io.*;
import java.lang.*;

// 931. Minimum Falling Path Sum

public class P931 {
    int INF = 0x3f3f3f3f;
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] f = new int[n][n];
        for (int[] r : f) Arrays.fill(r, INF);

        for (int i = 0; i < n; i++) f[0][i] = matrix[0][i];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (j + k < 0 || j + k >= n) continue;
                    f[i][j] = Math.min(f[i][j], f[i - 1][j + k] + matrix[i][j]);
                }
            }
        }

        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, f[n - 1][i]);
        }

        return ret;
    }
}
