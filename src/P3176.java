import java.util.ArrayDeque;
import java.util.Arrays;

// 3176. Find the Maximum Length of a Good Subsequence I
public class P3176 {
    int[] nums;
    int n;
    int[][][] memo;
    public int maximumLength(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;
        memo = new int[n][n][k + 1];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        int ret = dfs(0, -1, k);
        // System.out.println(memo);
        return ret;
    }

    int dfs(int idx, int last, int k) {
        if (idx == n) {
            return 0;
        }
        if (last != -1 && memo[idx][last][k] != -1) return memo[idx][last][k];
        int ret = dfs(idx + 1, last, k);
        if (last != -1 && nums[last] == nums[idx]) {
            ret = Math.max(ret, 1 + dfs(idx + 1, last, k));
        } else if (last == -1 || k > 0) {
            ret = Math.max(ret, 1 + dfs(idx + 1, idx, last == -1 ? k : k - 1));
        }
        if (last != -1) memo[idx][last][k] = ret;
        return ret;
    }
}
