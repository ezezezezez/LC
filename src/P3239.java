import java.util.ArrayList;
import java.util.List;

// 3239. Minimum Number of Flips to Make Binary Grid Palindromic I
public class P3239 {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int k = n - 1 - j;
                if (grid[i][j] != grid[i][k]) cnt1++;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m / 2; i++) {
                int k = m - 1 - i;
                if (grid[i][j] != grid[k][j]) cnt2++;
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
