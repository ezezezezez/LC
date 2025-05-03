import java.util.Arrays;

// 3240. Minimum Number of Flips to Make Binary Grid Palindromic II
public class P3240 {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m / 2 && n > 1; i++) {
            int[][] f = new int[n / 2][2];
            int zeros = 0;
            if (grid[i][0] == 0) zeros++;
            if (grid[i][n - 1] == 0) zeros++;
            if (grid[m - 1 - i][0] == 0) zeros++;
            if (grid[m - 1 - i][n - 1] == 0) zeros++;
            f[0][0] = 4 - zeros;
            f[0][1] = zeros;
            for (int j = 1; j < f.length; j++) {
                zeros = 0;
                if (grid[i][j] == 0) zeros++;
                if (grid[i][n - 1 - j] == 0) zeros++;
                if (grid[m - 1 - i][j] == 0) zeros++;
                if (grid[m - 1 - i][n - 1 - j] == 0) zeros++;
                f[j][0] = Math.min(f[j - 1][0], f[j - 1][1]) + 4 - zeros;
                f[j][1] = Math.min(f[j - 1][0], f[j - 1][1]) + zeros;
            }
            cnt += Math.min(f[n / 2 - 1][0], f[n / 2 - 1][1]);
        }
        int t = 0;
        if (m % 2 == 1 && n % 2 == 1) {
            t += grid[m / 2][n / 2] == 1 ? 1 : 0;
        }
        int diffPair = 0;
        int ones = 0;
        if (m % 2 == 1) {
            for (int i = 0; i < n / 2; i++) {
                if (grid[m / 2][i] != grid[m / 2][n - 1 - i]) diffPair++;
                else if (grid[m / 2][i] == 1) ones += 2;
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][n / 2] != grid[m - 1 - i][n / 2]) diffPair++;
                else if (grid[i][n / 2] == 1) ones += 2;
            }
        }
        int ret = diffPair + cnt + t;
        if (ones % 4 == 0) return ret;
        for (int i = 1; i <= diffPair; i++) {
            if ((ones + 2 * i) % 4 == 0) return ret;
        }
        return ret + 2;
    }
}
