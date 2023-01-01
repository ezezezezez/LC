import java.io.*;
import java.lang.*;
import java.util.*;

// 1901. Find a Peak Element II

public class P1901 {
    int m, n;
    public int[] findPeakGrid(int[][] mat) {
        m = mat.length; n = mat[0].length;
        int[] dir = new int[]{-1, 0, 1, 0, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dir[k], ny = j + dir[k + 1];
                    if (valid(nx, ny)) {
                        if (mat[nx][ny] > mat[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) return new int[]{i, j};
            }
        }
        return new int[0];
    }

    boolean valid(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
