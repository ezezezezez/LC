// 2787. Ways to Express an Integer as Sum of Powers
public class P2787 {
    public int numberOfWays(int n, int x) {
        long mod = (long) (1e9 + 7);
        long ret = 0;
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = 1; j <= n; j++) {
                long p = 1;
                for (int k = 0; k < x; k++) p *= i;
                long q = j - p;
                if (q >= 0) {
                    dp[i][j] = dp[i][j] + dp[i - 1][(int) q];
                    if (dp[i][j] >= mod) dp[i][j] -= mod;
                }
            }
        }
        // for (long[] row : dp) System.out.println(Arrays.toString(row));
        return (int) dp[n][n];
    }
}
