import java.io.*;
import java.lang.*;
import java.util.*;

// 1509. Minimum Difference Between Largest and Smallest Value in Three Moves

public class P1509 {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 3) return 0;
        Arrays.sort(nums);
        int ret = nums[n - 1] - nums[0];
        for (int i = 3; i >= 0; i--) {
            int j = 3 - i;
            ret = Math.min(ret, nums[n - 1 - j] - nums[i]);
        }
        return ret;
    }
}
