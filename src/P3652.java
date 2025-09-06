// 3652. Best Time to Buy and Sell Stock using Strategy

public class P3652 {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] prefix = new long[n + 1], suffix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + prices[i] * strategy[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + prices[i] * strategy[i];
        }
        long ret = prefix[n];
        long[] prefix2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix2[i + 1] = prefix2[i] + prices[i];
        }
        for (int i = k - 1; i < n; i++) {
            ret = Math.max(ret, prefix[i - k + 1] + prefix2[i + 1] - prefix2[i + 1 - k / 2] + suffix[i + 1]);
        }
        return ret;
    }
}
