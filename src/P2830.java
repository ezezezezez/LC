import java.util.Collections;
import java.util.List;

// 2830. Maximize the Profit as the Salesman
public class P2830 {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        int m = offers.size();
        Collections.sort(offers, (a, b) -> Integer.compare(a.get(1), b.get(1)));
        int ret = 0;
        int[] dp = new int[m + 1];
        for (int i = 0; i < m; i++) {
            dp[i + 1] = dp[i];
            List<Integer> offer = offers.get(i);
            int start = offer.get(0), end = offer.get(1), gold = offer.get(2);
            int lo = 0, hi = i - 1, t = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                List<Integer> pre = offers.get(mid);
                if (pre.get(1) < start) {
                    t = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            dp[i + 1] = Math.max(dp[i + 1], gold + dp[t + 1]);
            ret = Math.max(ret, dp[i + 1]);
        }
        return ret;
    }
}
