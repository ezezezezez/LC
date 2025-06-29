// 3573. Best Time to Buy and Sell Stock V
public class P3573 {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][] dp = new long[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            long preMax = -prices[0], preMax2 = prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j + 1] = Math.max(dp[i][j], Math.max(preMax + prices[j], preMax2 - prices[j]));
                preMax = Math.max(preMax, dp[i - 1][j] - prices[j]);
                preMax2 = Math.max(preMax2, dp[i - 1][j] + prices[j]);
            }

            // System.out.println(Arrays.toString(dp[i]));
        }

        return dp[k][n];
    }
}
