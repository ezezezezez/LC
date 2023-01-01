import java.io.*;
import java.lang.*;
import java.util.*;

// 1895. Largest Magic Square

public class P1895 {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] rows = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i][j + 1] = rows[i][j] + grid[i][j];
            }
        }
        int[][] cols = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cols[i + 1][j] = cols[i][j] + grid[i][j];
            }
        }
        for (int len = Math.min(m, n); len >= 1; len--) {
            for (int i = len - 1; i < m; i++) {
                for (int j = len - 1; j < n; j++) {
                    boolean valid = true;
                    int t = rows[i][j + 1] - rows[i][j + 1 - len];
                    for (int k = i; k >= i - len + 1; k--) {
                        if (rows[k][j + 1] - rows[k][j + 1 - len] != t) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;
                    for (int k = j; k >= j - len + 1; k--) {
                        if (cols[i + 1][k] - cols[i + 1 - len][k] != t) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;
                    int diag1 = 0, diag2 = 0;
                    for (int k = 0; k < len; k++) {
                        int ii = i - k, jj = j - k;
                        diag1 += grid[ii][jj];
                    }
                    if (diag1 != t) continue;
                    for (int k = 0; k < len; k++) {
                        int ii = i - k, jj = j - len + 1 + k;
                        diag2 += grid[ii][jj];
                    }
                    if (diag2 != t) continue;
                    return len;
                }
            }
        }
        return 1;
    }
}
