import java.io.*;
import java.lang.*;
import java.util.*;

public class P2536 {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] t = new int[n][n];
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            t[r1][c1]++;
            if (c2 + 1 < n) t[r1][c2 + 1]--;
            if (r2 + 1 < n) t[r2 + 1][c1]--;
            if (r2 + 1 < n && c2 + 1 < n) t[r2 + 1][c2 + 1]++;
        }
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (t[i][j] != 0) {
                    cur += t[i][j];

                }
                t[i][j] = cur + (i - 1 >= 0 ? t[i - 1][j] : 0);
            }
        }
        return t;
    }
}
