import java.util.List;

// 2826. Sorting Three Groups
public class P2826 {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[][] dp = new int[3][n + 1];
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            if (num == 1) {
                dp[0][i + 1] = dp[0][i];
                dp[1][i + 1] = dp[1][i] + 1;
                dp[2][i + 1] = dp[2][i] + 1;
            } else if (num == 2) {
                dp[0][i + 1] = dp[0][i] + 1;
                dp[1][i + 1] = Math.min(dp[0][i], dp[1][i]);
                dp[2][i + 1] = dp[2][i] + 1;
            } else {
                dp[0][i + 1] = dp[0][i] + 1;
                dp[1][i + 1] = dp[1][i] + 1;
                dp[2][i + 1] = Math.min(Math.min(dp[0][i], dp[1][i]), dp[2][i]);
            }
            ret = Math.min(ret, dp[0][i + 1] + n - i - 1);
            ret = Math.min(ret, dp[1][i + 1] + n - i - 1);
            ret = Math.min(ret, dp[2][i + 1] + n - i - 1);
        }
        return ret;
    }
}
