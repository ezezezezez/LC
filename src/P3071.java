// 3071. Minimum Operations to Write the Letter Y on a Grid
public class P3071 {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int lo = 0, hi = Integer.MAX_VALUE, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean valid = false;
            outer:
            for (int u = 0; u <= 2; u++) {
                for (int v = 0; v <= 2; v++) {
                    if (u == v) continue;
                    int cnt = 0;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if ((i == j && i <= n / 2) || (i + j == n - 1 && i <= n / 2) || (i > n / 2 && j == n / 2)) {
                                if (grid[i][j] != u) cnt++;
                            } else {
                                if (grid[i][j] != v) cnt++;
                            }
                        }
                    }
                    if (cnt <= mid) {
                        valid = true;
                        break outer;
                    }
                }
            }
            if (valid) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
