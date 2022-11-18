import java.util.*;
import java.io.*;
import java.lang.*;

// 945. Minimum Increment to Make Array Unique

public class P945 {
    public int minIncrementForUnique(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                cnt += 1;
                nums[i] = nums[i - 1] + 1;
            } else if (nums[i] < nums[i - 1]) {
                cnt += nums[i - 1] + 1 - nums[i];
                nums[i] = nums[i - 1] + 1;
            }
        }

        return cnt;
    }
}
