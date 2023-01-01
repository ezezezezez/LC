import java.io.*;
import java.lang.*;
import java.util.*;

// 2369. Check if There is a Valid Partition For The Array

public class P2369 {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] && f[i - 1]) {
                f[i + 1] = true;
                continue;
            }
            if (i > 1 && nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2] && f[i - 2]) {
                f[i + 1] = true;
                continue;
            }
            if (i > 1 && nums[i] - nums[i - 1] == 1 && nums[i - 1] - nums[i - 2] == 1 && f[i - 2]) {
                f[i + 1] = true;
            }
        }
        return f[n];
    }
}
