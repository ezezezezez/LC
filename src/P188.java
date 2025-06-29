// 188. Best Time to Buy and Sell Stock IV
public class P188 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        if (k >= prices.length / 2) {
            int sum = 0;
            int pre = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < prices[i - 1]) {
                    sum += prices[i - 1] - pre;
                    pre = prices[i];
                }
            }
            sum += prices[prices.length - 1] - pre;
            return sum;
        }
        if (k == 0) return 0;
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int maxTemp = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], maxTemp + prices[j]);
                maxTemp = Math.max(maxTemp, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][prices.length - 1];
    }
}
