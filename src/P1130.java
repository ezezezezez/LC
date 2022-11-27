import java.util.*;
import java.io.*;
import java.lang.*;

// 1130. Minimum Cost Tree From Leaf Values

public class P1130 {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] maxVal = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxVal[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                maxVal[i][j] = Math.max(maxVal[i][j - 1], arr[j]);
            }
        }

        int[][] f = new int[n][n];

        for (int add = 1; add < n; add++) {
            for (int i = 0; i + add < n; i++) {
                int j = i + add;
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + maxVal[i][k] * maxVal[k + 1][j]);
                }
            }
        }

        return f[0][n - 1];
    }
}
