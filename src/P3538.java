import java.util.Arrays;

// 3538. Merge Operations for Minimum Travel Time
public class P3538 {
    public int minTravelTime(int l, int n, int k, int[] pos, int[] time) {
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            prefix[i + 1] = prefix[i] + time[i];
        }
        int[][][] f = new int[n][k + 1][k + 1];
        for (int[][] a : f) {
            for (int[] b : a) {
                Arrays.fill(b, Integer.MAX_VALUE / 2);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= Math.min(i - 1, k); c++) {
                for (int j = 0; j <= c; j++) {
                    f[i][c][0] = Math.min(f[i][c][0], f[i - 1][c][j] + (pos[i] - pos[i - 1]) * (prefix[i] - prefix[i - 1 - j]));
                }
                for (int m = 1; m <= c && i - m > 0; m++) {
                    int x = i - m;
                    int r = c - m;
                    for (int y = 0; y <= r; y++) {
                        f[i][c][m] = Math.min(f[i][c][m], f[x - 1][r][y] + (pos[i] - pos[x - 1]) * (prefix[x] - prefix[x - 1 - y]));
                    }
                }
            }
        }
        // printGrid3D(f);
        int ret = Integer.MAX_VALUE;
        for (int c = 0; c <= k; c++) {
            ret = Math.min(ret, f[n - 1][k][c]);
        }
        return ret;
    }

    void printGrid3D(int[][][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                for (int k = 0; k < grid[i][j].length; k++) {
                    System.out.print(grid[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }
}
