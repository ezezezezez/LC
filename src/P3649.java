// 3648. Minimum Sensors to Cover Grid

import java.util.Arrays;

public class P3649 {
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);
        int j = 0;
        long ret = 0;
        for (int i = 1; i < n; i++) {
            while (j < i && nums[i] - nums[j] > nums[j]) j++;
            ret += i - j;
        }
        return ret;
    }
}
