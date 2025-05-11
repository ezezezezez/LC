import java.util.*;

// 3548. Equal Sum Grid Partition II
public class P3548 {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] sum = new long[m + 1][n + 1];
        Map<Long, List<int[]>> rows = new HashMap<>();
        Map<Long, List<int[]>> cols = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
                rows.computeIfAbsent((long) grid[i][j], kk -> new ArrayList<>()).add(new int[] {i, j});
                cols.computeIfAbsent((long) grid[i][j], kk -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        for (List<int[]> list : rows.values()) Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        for (List<int[]> list : cols.values()) Collections.sort(list, (a, b) -> Integer.compare(a[1], b[1]));

        // n == 1
        if (n == 1) {
            for (int i = 0; i < m - 1; i++) {
                long upper = sum[i + 1][n], lower = sum[m][n] - upper;
                if (upper == lower) return true;
                long diff = Math.abs(upper - lower);
                if (upper > lower) {
                    if (i > 0 && (grid[0][0] == diff || grid[i][0] == diff)) return true;
                } else {
                    if (i < m - 2 && (grid[m - 1][0] == diff || grid[i + 1][0] == diff)) return true;
                }
            }
            return false;
        }

        // m == 1;
        if (m == 1) {
            for (int j = 0; j < n - 1; j++) {
                long left = sum[m][j + 1], right = sum[m][n] - left;
                if (left == right) return true;
                long diff = Math.abs(left - right);
                if (left > right) {
                    if (j > 0 && (grid[0][0] == diff || grid[0][j] == diff)) return true;
                } else {
                    if (j < n - 2 && (grid[0][n - 1] == diff || grid[0][j + 1] == diff)) return true;
                }
            }
            return false;
        }

        // m > 1 && n > 1
        for (int i = 0; i < m - 1; i++) {
            if (sum[m][n] - sum[i + 1][n] == sum[i + 1][n]) return true;
            long upper = sum[i + 1][n], lower = sum[m][n] - upper;
            long diff = Math.abs(upper - lower);
            if (upper > lower) {
                if (rows.containsKey(diff) && rows.get(diff).get(0)[0] <= i) {
                    if (i > 0) return true;
                    for (int[] cell : rows.get(diff)) {
                        if (cell[0] > 0) break;
                        if (cell[1] == 0 || cell[1] == n - 1) return true;
                    }
                }
            } else {
                if (rows.containsKey(diff) && rows.get(diff).get(rows.get(diff).size() - 1)[0] > i) {
                    if (i < m - 2) return true;
                    for (int[] cell : rows.get(diff)) {
                        if (cell[0] < m - 1) continue;
                        if (cell[1] == 0 || cell[1] == n - 1) return true;
                    }
                }
            }
        }
        for (int j = 0; j < n - 1; j++) {
            if (sum[m][n] - sum[m][j + 1] == sum[m][j + 1]) return true;
            long left = sum[m][j + 1], right = sum[m][n] - left;
            long diff = Math.abs(left - right);
            if (left > right) {
                if (cols.containsKey(diff) && cols.get(diff).get(0)[1] <= j) {
                    if (j > 0) return true;
                    for (int[] cell : cols.get(diff)) {
                        if (cell[1] > 0) break;
                        if (cell[0] == 0 || cell[0] == m - 1) return true;
                    }
                }
            } else {
                if (cols.containsKey(diff) && cols.get(diff).get(cols.get(diff).size() - 1)[1] > j) {
                    if (j < n - 2) return true;
                    for (int[] cell : cols.get(diff)) {
                        if (cell[1] < n - 1) continue;
                        if (cell[0] == 0 || cell[0] == m - 1) return true;
                    }
                }
            }
        }
        return false;
    }
}
