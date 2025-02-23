import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2770. Maximum Number of Jumps to Reach the Last Index
public class P2770 {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (Math.abs(nums[j] - nums[i]) > target) continue;
                if (dp[i] == -1) continue;
                dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }
}
