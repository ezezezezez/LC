// 3685. Subsequence Sum After Capping Elements

public class P3685 {
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        int n = nums.length;
        boolean[] ret = new boolean[n];
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        int[] cnt = new int[n + 1];
        for (int num : nums) cnt[num]++;
        int[] cnt2 = new int[n + 2];
        for (int i = n; i >= 1; i--) {
            cnt2[i] = cnt2[i + 1] + cnt[i];
        }

        for (int i = 1; i <= n; i++) {
            int mx = Math.min(cnt2[i], k / i);
            boolean can = false;
            for (int j = 0; j <= mx; j++) {
                if (dp[k - j * i]) {
                    can = true;
                    break;
                }
            }
            // System.out.println(Arrays.toString(dp) + " " + can + " " + mx + " " + cnt2[i]);
            ret[i - 1] = can;
            for (int j = 1; j <= cnt[i]; j++) {
                for (int p = k; p >= i; p--) {
                    dp[p] = dp[p] || dp[p - i];
                }
            }
        }
        return ret;
    }
}
