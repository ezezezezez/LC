import java.util.Arrays;

// 2919. Minimum Increment Operations to Make Array Beautiful
public class P2919 {
    public long minIncrementOperations(int[] nums, int k) {
        int n = nums.length;
        long[] f = new long[n + 1];
        Arrays.fill(f, Long.MAX_VALUE / 2);
        f[0] = f[1] = f[2] = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                f[i + 1] = Math.min(f[i + 1], Math.max(0, k - nums[i - j]) + f[i - j]);
            }
        }
        return f[n];
    }
}
