// 2732. Find a Good Subset of the Matrix

import java.util.List;

public class P2732 {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[] arr = new boolean[1 << n];
        int[] row = new int[1 << n];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    mask |= (1 << j);
                }
            }
            if (mask == 0) return List.of(i);
            arr[mask] = true;
            row[mask] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ((i & j) == 0 && arr[i] && arr[j]) {
                    return row[i] < row[j] ? List.of(row[i], row[j]) : List.of(row[j], row[i]);
                }
            }
        }
        return List.of();
    }
}
