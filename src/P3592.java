import java.util.ArrayList;
import java.util.List;

// 3592. Inverse Coin Change

public class P3592 {
    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) dp[0][i] = 1;
        List<Integer> list = new ArrayList<>();
        if (numWays[0] == 1) {
            for (int i = 1; i <= n; i++) dp[1][i] = 1;
            list.add(1);
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                if (list.contains(j)) {
                    for (int v = j; v <= i; v += j) {
                        dp[i][j] += dp[i - v][j - 1];
                    }
                }
            }
            int way = numWays[i - 1];
            if (dp[i][i] > way) return new ArrayList<>();
            if (dp[i][i] < way) {
                dp[i][i]++;
                if (dp[i][i] < way) {
                    // System.out.println(i + " " + dp[i][i] + " " + way);
                    return new ArrayList<>();
                }
                for (int j = i + 1; j <= n; j++) dp[i][j] = dp[i][j - 1];
                list.add(i);
            }
        }

        return list;
    }
}
