// 3332. Maximum Points Tourist Can Earn
public class P3332 {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int ret = 0;
        int[][] dp = new int[k][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[0][i] = Math.max(dp[0][i], Math.max(stayScore[0][i], travelScore[j][i]));
            }
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < n; p++) {
                    dp[i][j] = Math.max(dp[i][j], p == j ? dp[i - 1][p] + stayScore[i][j] : dp[i - 1][p] + travelScore[p][j]);
                }
            }
        }

        int mx = 0;
        for (int i = 0; i < n; i++) mx = Math.max(mx, dp[k - 1][i]);

        return mx;
    }
}
