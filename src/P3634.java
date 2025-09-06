// 3634. Minimum Removals to Balance Array

import java.util.Arrays;

public class P3634 {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < n; i++) {
            while (1L * nums[j] * k < (long) nums[i]) j++;
            ret = Math.min(ret, n - (i - j + 1));
        }
        return ret;
    }
}
