import java.io.*;
import java.lang.*;
import java.util.*;

// 2300. Successful Pairs of Spells and Potions

public class P2304 {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        for (int j = 0; j < n; j++) {
            f[0][j] = grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    f[i][k] = Math.min(f[i][k], f[i - 1][j] + moveCost[grid[i - 1][j]][k] + grid[i][k]);
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, f[m - 1][i]);
        }
        return ret;
    }
}
