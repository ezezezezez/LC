import java.util.Arrays;
import java.util.List;

// 2915. Length of the Longest Subsequence That Sums to Target
public class P2915 {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] dp = new int[n + 1][target + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            for (int j = 1; j <= target; j++) {
                dp[i + 1][j] = dp[i][j];
                if (j - num >= 0 && dp[i][j - num] != -1) {
                    dp[i + 1][j] = Math.max(dp[i + 1][j], 1 + dp[i][j - num]);
                }
            }
        }
        return dp[n][target];
    }
}
