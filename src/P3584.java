// 3584. Maximum Product of First and Last Elements of a Subsequence
public class P3584 {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        int[] minE = new int[n], maxE = new int[n];
        minE[0] = maxE[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minE[i] = Math.min(minE[i - 1], nums[i]);
            maxE[i] = Math.max(maxE[i - 1], nums[i]);
        }
        long ret = (long) -1e18;

        for (int i = 0; i < n; i++) {
            int j = i - m + 1;
            if (j < 0) continue;
            long v1 = 1L * nums[i] * minE[j], v2 = 1L * nums[i] * maxE[j];
            ret = Math.max(ret, Math.max(v1, v2));
        }

        return ret;
    }
}
