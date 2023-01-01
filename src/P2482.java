import java.io.*;
import java.lang.*;
import java.util.*;

// 2482. Difference Between Ones and Zeros in Row and Column

public class P2482 {
    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] onesRow = new int[m], onesCol = new int[n];
        int[] zerosRow = new int[m], zerosCol = new int[n];
        for (int i = 0; i < m; i++) {
            int oneCnt = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) oneCnt++;
            }
            onesRow[i] = oneCnt;
            zerosRow[i] = n - oneCnt;
        }
        for (int j = 0; j < n; j++) {
            int oneCnt = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) oneCnt++;
            }
            onesCol[j] = oneCnt;
            zerosCol[j] = m - oneCnt;
        }
        int[][] ret = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j];
            }
        }
        return ret;
    }
}
