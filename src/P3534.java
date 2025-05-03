import java.math.BigInteger;
import java.util.*;

// 3534. Path Existence Queries in a Graph II
public class P3534 {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int m = queries.length;
        int[] ret = new int[m];
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {nums[i], i};
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        int MAXP = 18;
        int[][] f = new int[n][MAXP];
        int j = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (arr[j][0] - arr[i][0] > maxDiff) j--;
            int x = arr[i][1], y = arr[j][1];
            f[x][0] = y;
            for (int p = 1; p < MAXP; p++) {
                f[x][p] = f[f[x][p - 1]][p - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int x = query[0], y = query[1];
            if (nums[x] > nums[y]) {
                int t = x;
                x = y;
                y = t;
            }
            if (x == y) {
                ret[i] = 0;
                continue;
            }
            if (nums[x] == nums[y]) {
                ret[i] = 1;
                continue;
            }
            int d = 0;
            for (int p = MAXP - 1; p >= 0; p--) {
                if (nums[f[x][p]] < nums[y]) {
                    d += 1 << p;
                    x = f[x][p];
                }
            }
            // if (nums[x] + maxDiff < nums[y]) {
            if (nums[f[x][0]] < nums[y]) {
                ret[i] = -1;
            } else {
                ret[i] = d + 1;
            }
        }
        return ret;
    }
}
