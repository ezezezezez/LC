import java.io.*;
import java.lang.*;
import java.util.*;

// 1887. Reduction Operations to Make the Array Elements Equal

public class P1887 {
    public int reductionOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int last = nums[n - 1];
        int ret = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] != last) {
                ret += n - i - 1;
            }
            last = nums[i];
        }
        return ret;
    }
}
