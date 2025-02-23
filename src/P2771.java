import java.util.Arrays;

// 2771. Longest Non-decreasing Subarray From Two Arrays
public class P2771 {
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[2][n];
        dp[0][0] = dp[1][0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] >= nums1[i - 1]) {
                dp[0][i] = Math.max(dp[0][i], 1 + dp[0][i - 1]);
            }
            if (nums1[i] >= nums2[i - 1]) {
                dp[0][i] = Math.max(dp[0][i], 1 + dp[1][i - 1]);
            }
            if (nums1[i] < Math.min(nums1[i - 1], nums2[i - 1])) {
                dp[0][i] = 1;
            }

            if (nums2[i] >= nums1[i - 1]) {
                dp[1][i] = Math.max(dp[1][i], 1 + dp[0][i - 1]);
            }
            if (nums2[i] >= nums2[i - 1]) {
                dp[1][i] = Math.max(dp[1][i], 1 + dp[1][i - 1]);
            }
            if (nums2[i] < Math.min(nums1[i - 1], nums2[i - 1])) {
                dp[1][i] = 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, Math.max(dp[0][i], dp[1][i]));
        }
        return ret;
    }
}
