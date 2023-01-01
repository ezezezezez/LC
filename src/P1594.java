import java.io.*;
import java.lang.*;
import java.util.*;

// 1594. Maximum Non Negative Product in a Matrix

public class P1594 {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int mod = (int)(1e9 + 7);
        long[][][] f = new long[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i + 1][j + 1][0] = Long.MIN_VALUE;
                f[i + 1][j + 1][1] = Long.MAX_VALUE;
            }
        }
        if (grid[0][0] == 0) {
            f[1][1][0] = f[1][1][1] = 0;
        } else if (grid[0][0] > 0) {
            f[1][1][0] = grid[0][0];
        } else {
            f[1][1][1] = grid[0][0];
        }
        for (int i = 1; i < n; i++) {
            if (f[1][i][0] != Long.MIN_VALUE) {
                f[1][i + 1][0] = f[1][i][0] * grid[0][i] >= 0 ? f[1][i][0] * grid[0][i] : Long.MIN_VALUE;
                f[1][i + 1][1] = f[1][i][0] * grid[0][i] <= 0 ? f[1][i][0] * grid[0][i] : Long.MAX_VALUE;
            } else {
                f[1][i + 1][0] = f[1][i][1] * grid[0][i] >= 0 ? f[1][i][1] * grid[0][i] : Long.MIN_VALUE;
                f[1][i + 1][1] = f[1][i][1] * grid[0][i] <= 0 ? f[1][i][1] * grid[0][i] : Long.MAX_VALUE;
            }
        }
        for (int i = 1; i < m; i++) {
            if (f[i][1][0] != Long.MIN_VALUE) {
                f[i + 1][1][0] = f[i][1][0] * grid[i][0] >= 0 ? f[i][1][0] * grid[i][0] : Long.MIN_VALUE;
                f[i + 1][1][1] = f[i][1][0] * grid[i][0] <= 0 ? f[i][1][0] * grid[i][0] : Long.MAX_VALUE;
            } else {
                f[i + 1][1][0] = f[i][1][1] * grid[i][0] >= 0 ? f[i][1][1] * grid[i][0] : Long.MIN_VALUE;
                f[i + 1][1][1] = f[i][1][1] * grid[i][0] <= 0 ? f[i][1][1] * grid[i][0] : Long.MAX_VALUE;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    f[i + 1][j + 1][0] = f[i + 1][j + 1][1] = 0;
                } else if (grid[i][j] > 0) {
                    long mx = Math.max(f[i][j + 1][0], f[i + 1][j][0]), mn = Math.min(f[i][j + 1][1], f[i + 1][j][1]);
                    f[i + 1][j + 1][0] = mx == Long.MIN_VALUE ? Long.MIN_VALUE : mx * grid[i][j];
                    f[i + 1][j + 1][1] = mn == Long.MAX_VALUE ? Long.MAX_VALUE : mn * grid[i][j];
                } else {
                    long mx = Math.max(f[i][j + 1][0], f[i + 1][j][0]), mn = Math.min(f[i][j + 1][1], f[i + 1][j][1]);
                    f[i + 1][j + 1][0] = mn == Long.MAX_VALUE ? Long.MIN_VALUE : mn * grid[i][j];
                    f[i + 1][j + 1][1] = mx == Long.MIN_VALUE ? Long.MAX_VALUE : mx * grid[i][j];
                }
            }
        }

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(f[i + 1][j + 1][0] + " ");
        //     }
        //     System.out.println();
        // }

        return (int)(f[m][n][0] == Long.MIN_VALUE ? -1 : f[m][n][0] % mod);
    }
}
