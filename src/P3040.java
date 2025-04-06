// 3040. Maximum Number of Operations With the Same Score II
public class P3040 {
    int n;
    int[][] memo;
    public int maxOperations(int[] nums) {
        n = nums.length;
        memo = new int[n][n];
        return solve(nums, 0, n - 1, 0, 0);
    }

    int solve(int[] nums, int l, int r, int sum, int round) {
        if (r - l < 1) {
            return round;
        }
        if (memo[l][r] != 0) return memo[l][r];
        int mx = round;
        if (sum == 0 || sum == nums[l] + nums[r]) {
            mx = Math.max(mx, solve(nums, l + 1, r - 1, nums[l] + nums[r], round + 1));
        }
        if (sum == 0 || sum == nums[l] + nums[l + 1]) {
            mx = Math.max(mx, solve(nums, l + 2, r, nums[l] + nums[l + 1], round + 1));
        }
        if (sum == 0 || sum == nums[r - 1] + nums[r]) {
            mx = Math.max(mx, solve(nums, l, r - 2, nums[r - 1] + nums[r], round + 1));
        }
        memo[l][r] = mx;
        return mx;
    }
}
