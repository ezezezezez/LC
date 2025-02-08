import java.util.HashMap;
import java.util.Map;

// 2750. Ways to Split Array Into Good Subarrays
public class P2750 {
    public int numberOfGoodSubarraySplits(int[] nums) {
        int n = nums.length;
        long ret = 0, mod = (long) (1e9 + 7);
        long[] dp = new long[n];
        dp[0] = nums[0] == 1 ? 1 : 0;
        Map<Integer, Integer> map = new HashMap<>();
        int pre = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                map.put(i, pre);
                pre = i;
            }
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                dp[i] = dp[i - 1];
            } else {
                int t = map.get(i);
                if (t == -1) dp[i] = 1;
                else {
                    dp[i] = dp[i - 1] * (i - t + 0L) % mod;
                }
            }
        }
        return (int) dp[n - 1];
    }
}
