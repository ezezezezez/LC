import java.util.*;
import java.io.*;
import java.lang.*;

// 934. Shortest Bridge

public class P935 {
    int mod = (int)(1e9 + 7);
    int[] hdx = new int[]{-2, -2, -1, 1, 2,  2, 1, -1};
    int[] hdy = new int[]{-1,  1,  2, 2, 1, -1, -2, -2};
    public int knightDialer(int n) {
        int[][] pad = new int[4][3];
        int[][][] f = new int[4][3][n + 1];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                f[i][j][1] = 1;
            }
        }
        f[3][1][1] = 1;

        for (int m = 2; m <= n; m++) {
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 3; y++) {
                    if (x == 3 && (y == 0 || y == 2)) continue;
                    for (int k = 0; k < 8; k++) {
                        int nx = x + hdx[k], ny = y + hdy[k];
                        if (valid(nx, ny)) {
                            f[x][y][m] = (f[x][y][m] + f[nx][ny][m - 1]) % mod;
                        }
                    }
                }
            }
        }

        int ret = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (x == 3 && (y == 0 || y == 2)) continue;
                ret = (ret + f[x][y][n]) % mod;
            }
        }

        return ret;
    }

    boolean valid(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 3) return false;
        if (x == 3 && (y == 0 || y == 2)) return false;
        return true;
    }
}
