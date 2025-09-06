// 3627. Maximum Median Sum of Subsequences of Size 3

import java.util.Arrays;

public class P3627 {
    public long maximumMedianSum(int[] nums) {
        long ret = 0;
        int n = nums.length;
        Arrays.sort(nums);
        int i = 0, j = n - 1;
        for (int p = 0; p < n / 3; p++) {
            ret += nums[j - 1];
            i++;
            j -= 2;
        }

        return ret;
    }
}
