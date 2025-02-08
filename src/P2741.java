import java.util.Arrays;

// 2741. Special Permutations
public class P2741 {
    public int specialPerm(int[] nums) {
        long mod = (long) (1e9 + 7);
        int n = nums.length;
        long[][] dp = new long[1 << n][n];
        for (int i = 0; i < n; i++) dp[1 << i][i] = 1;
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    int t = i ^ (1 << j);
                    for (int k = 0; k < n; k++) {
                        if (((1 << k) & t) > 0 && (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0)) {
                            dp[i][j] = (dp[i][j] + dp[t][k]) % mod;
                        }
                    }
                }
            }
        }
        long ret = 0;
        for (int i = 0; i < n; i++) ret = (ret + dp[(1 << n) - 1][i]) % mod;
        return (int) ret;
    }
}
