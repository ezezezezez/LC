// 3654. Minimum Sum After Divisible Sum Deletions

import java.util.Arrays;

public class P3654 {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        long[] best = new long[k];
        Arrays.fill(best, (long) -9e18);
        best[0] = 0;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            int m = (int) (prefix[i + 1] % k);
            dp[i + 1] = Math.max(dp[i], best[m] + prefix[i + 1]);
            best[m] = Math.max(best[m], dp[i + 1] - prefix[i + 1]);
        }
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(best));
        return prefix[n] - dp[n];
    }
}
