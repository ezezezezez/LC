import java.util.ArrayList;
import java.util.List;

// 3592. Inverse Coin Change

public class P3592_2 {
    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int ways = numWays[i - 1];
            if (ways == dp[i]) continue;
            if (ways != dp[i] + 1) return List.of();

            ret.add(i);
            for (int j = i; j <= n; j++) {
                dp[j] += dp[j - i];
            }
        }

        return ret;
    }
}
