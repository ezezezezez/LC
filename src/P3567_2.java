// 3567. Minimum Absolute Difference in Sliding Submatrix

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P3567_2 {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ret = new int[m - k + 1][n - k + 1];

        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                List<Integer> list = new ArrayList<>();
                for (int p = 0; p < k; p++) {
                    for (int q = 0; q < k; q++) {
                        list.add(grid[i + p][j + q]);
                    }
                }
                Collections.sort(list);
                // System.out.println(list);
                int diff = Integer.MAX_VALUE;
                for (int x = 0; x < list.size() - 1; x++) {
                    if (list.get(x).equals(list.get(x + 1))) continue;
                    diff = Math.min(diff, Math.abs(list.get(x) - list.get(x + 1)));
                }
                ret[i][j] = diff == Integer.MAX_VALUE ? 0 : diff;
            }
        }

        return ret;
    }
}
