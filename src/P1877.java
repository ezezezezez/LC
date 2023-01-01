import java.io.*;
import java.lang.*;
import java.util.*;

// 1877. Minimize Maximum Pair Sum in Array

public class P1877 {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 0;
        for (int i = 0; i < n / 2; i++) {
            ret = Math.max(ret, nums[i] + nums[n - 1 - i]);
        }
        return ret;
    }
}
