import java.io.*;
import java.lang.*;
import java.util.*;

// 1824. Minimum Sideway Jumps

public class P1824 {
    public int minSideJumps(int[] obs) {
        int INF = 0x3f3f3f3f;
        int n = obs.length;
        int[][] f = new int[n][3];
        for (int[] row : f) Arrays.fill(row, INF);
        f[0][1] = 0;
        f[0][0] = f[0][2] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (obs[i] == j + 1) {
                    f[i][j] = INF;
                } else {
                    for (int k = 0; k < 3; k++) {
                        if (f[i - 1][k] != INF) {
                            f[i][j] = Math.min(f[i][j], obs[i] == k + 1 ? INF : k == j ? f[i - 1][k] : (f[i - 1][k] + 1));
                        }
                    }
                }
            }
        }
        return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
    }

    public int minSideJumps2(int[] obs) {
        int INF = 0x3f3f3f3f;
        int n = obs.length;
        int[][] f = new int[n][3];
        for (int[] row : f) Arrays.fill(row, INF);
        f[0][1] = 0;
        f[0][0] = f[0][2] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (obs[i] == j + 1) {
                    f[i][j] = INF;
                } else {
                    if ((j + 1) % 3 + 1 == obs[i]) {
                        f[i][j] = Math.min(f[i - 1][j], f[i - 1][(j + 2) % 3] + 1);
                    } else if ((j + 2) % 3 + 1 == obs[i]) {
                        f[i][j] = Math.min(f[i - 1][j], f[i - 1][(j + 1) % 3] + 1);
                    } else {
                        f[i][j] = Math.min(f[i - 1][j], Math.min(f[i - 1][(j + 1) % 3] + 1, f[i - 1][(j + 2) % 3] + 1));
                    }
                }
            }
        }
        return Math.min(f[n - 1][0], Math.min(f[n - 1][1], f[n - 1][2]));
    }
}
