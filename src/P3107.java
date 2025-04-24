import java.util.Arrays;

// 3107. Minimum Operations to Make Median of Array Equal to K
public class P3107 {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        long ret = Math.abs(k - nums[n / 2]);
        for (int i = n / 2 + 1; i < n; i++) {
            if (nums[i] < k) {
                ret += k - nums[i];
            }
        }
        for (int i = n / 2 - 1; i >= 0; i--) {
            if (nums[i] > k) {
                ret += nums[i] - k;
            }
        }
        return ret;
    }
}
