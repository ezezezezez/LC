import java.io.*;
import java.lang.*;
import java.util.*;

// 2352. Equal Row and Column Pairs

public class P2352 {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rows = new HashMap<>();
        Map<String, Integer> cols = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rows.merge(Arrays.toString(grid[i]), 1, Integer::sum);
            int[] col = new int[n];
            for (int j = 0; j < n; j++) {
                col[j] = grid[j][i];
            }
            cols.merge(Arrays.toString(col), 1, Integer::sum);
        }
        // System.out.println(rows);
        // System.out.println(cols);
        int ret = 0;
        for (String key1 : rows.keySet()) {
            for (String key2 : cols.keySet()) {
                if (key1.equals(key2)) {
                    int cnt1 = rows.get(key1), cnt2 = cols.get(key2);
                    ret += cnt1 * cnt2;
                }
            }
        }
        return ret;
    }
}
