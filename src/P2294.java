import java.io.*;
import java.lang.*;
import java.util.*;

// 2294. Partition Array Such That Maximum Difference Is K

public class P2294 {
    public int partitionArray(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int pre = nums[0];
        int ret = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= pre + k) continue;
            ret++;
            pre = nums[i];
        }
        ret++;
        return ret;
    }
}
