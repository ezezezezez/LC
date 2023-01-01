import java.io.*;
import java.lang.*;
import java.util.*;

// 1504. Count Submatrices With All Ones

public class P1504 {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int ret = 0;
        int[][] h = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                h[i][j] = mat[i][j] == 0 ? 0 : (i == 0 ? 1 : 1 + h[i - 1][j]);
            }
        }
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = j - 1;
                while (p >= 0 && h[i][p] >= h[i][j]) p--;
                if (p >= 0) {
                    f[i][j] = h[i][j] * (j - p) + f[i][p];
                } else {
                    f[i][j] = h[i][j] * (j - p);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret += f[i][j];
            }
        }
        return ret;
    }
}
