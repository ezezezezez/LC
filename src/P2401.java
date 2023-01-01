import java.io.*;
import java.lang.*;
import java.util.*;

// 2401. Longest Nice Subarray

public class P2401 {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int ret = 1;
        int pre = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (i > 0 && (pre & nums[i]) > 0) {
                pre ^= nums[j++];
            }
            ret = Math.max(ret, i - j + 1);
            pre |= nums[i];
        }
        return ret;
    }
}
