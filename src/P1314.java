import java.util.*;
import java.io.*;
import java.lang.*;

// 1314. Matrix Block Sum

public class P1314 {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] ret = new int[m][n];

        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i + 1][j + 1] = f[i + 1][j] + f[i][j + 1] - f[i][j] + mat[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = Math.max(0, i - k), b = Math.max(0, j -  k);
                int c = Math.min(m - 1, i + k), d = Math.min(n - 1, j + k);
                ret[i][j] = f[c + 1][d + 1] - f[c + 1][b] - f[a][d + 1] + f[a][b];
            }
        }

        return ret;
    }
}
