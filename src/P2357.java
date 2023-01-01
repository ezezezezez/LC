import java.io.*;
import java.lang.*;
import java.util.*;

// 2357. Make Array Zero by Subtracting Equal Amounts

public class P2357 {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        Arrays.sort(nums);
        int pre = 0;
        for (int num : nums) {
            if (pre != num) ret++;
            pre = num;
        }
        return ret;
    }
}
