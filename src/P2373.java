import java.io.*;
import java.lang.*;
import java.util.*;

// 2373. Largest Local Values in a Matrix

public class P2373 {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ret = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int mx = 0;
                for (int u = 0; u <= 2; u++) {
                    for (int v = 0; v <= 2; v++) {
                        mx = Math.max(mx, grid[i + u][j + v]);
                    }
                }
                ret[i][j] = mx;
            }
        }
        return ret;
    }
}
