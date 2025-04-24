import java.util.Arrays;

// 3176. Find the Maximum Length of a Good Subsequence I
public class P3176_2 {
    int[] nums;
    int n;
    int[][] memo;
    public int maximumLength(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;
        memo = new int[n][k + 1];
        for (int[] a : memo) {
            Arrays.fill(a, -1);
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, dfs(i, k));
        }
        // System.out.println(memo);
        return ret;
    }

    int dfs(int idx, int k) {
        if (idx == -1) {
            return 0;
        }
        if (memo[idx][k] != -1) return memo[idx][k];
        int ret = 1;
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[i] == nums[idx]) {
                ret = Math.max(ret, 1 + dfs(i, k));
            } else if (k > 0) {
                ret = Math.max(ret, 1 + dfs(i, k - 1));
            }
        }
        memo[idx][k] = ret;
        return ret;
    }
}
