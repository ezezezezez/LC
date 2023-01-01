import java.io.*;
import java.lang.*;
import java.util.*;

// 1738. Find Kth Largest XOR Coordinate Value

public class P1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i + 1][j + 1] = f[i][j + 1] ^ f[i + 1][j] ^ f[i][j] ^ matrix[i][j];
            }
        }
        int[] vals = new int[m * n];
        int idx = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                vals[idx++] = f[i][j];
            }
        }
        Arrays.sort(vals);
        return vals[vals.length - k];
    }
}
