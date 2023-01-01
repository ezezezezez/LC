import java.io.*;
import java.lang.*;
import java.util.*;

// 2428. Maximum Sum of an Hourglass

public class P2428 {
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ret = 0;
        for (int i = 2; i < m; i++) {
            for (int j = 2; j < n; j++) {
                int sum = 0;
                sum += grid[i][j] + grid[i][j - 1] + grid[i][j - 2];
                sum += grid[i - 1][j - 1];
                sum += grid[i - 2][j] + grid[i - 2][j - 1] + grid[i - 2][j - 2];
                ret = Math.max(ret, sum);
            }
        }
        return ret;
    }
}
