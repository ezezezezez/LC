import java.io.*;
import java.lang.*;
import java.util.*;

// 2500. Delete Greatest Value in Each Row

public class P2500 {
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int[] row : grid) Arrays.sort(row);
        int ret = 0;
        for (int j = n - 1; j >= 0; j--) {
            int mx = 0;
            for (int i = 0; i < m; i++) {
                mx = Math.max(mx, grid[i][j]);
            }
            ret += mx;
        }
        return ret;
    }
}
