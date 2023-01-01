import java.io.*;
import java.lang.*;
import java.util.*;

// 2419. Longest Subarray With Maximum Bitwise AND

public class P2419 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int mx = nums[0];
        for (int i = 1; i < n; i++) mx = Math.max(mx, nums[i]);
        int ret = 1;
        for (int i = 0; i < n; i++) {
            int t = i;
            while (i < n && nums[i] == mx) {
                i++;
            }
            if (nums[t] == mx) {
                ret = Math.max(ret, i - t);
            }
        }
        return ret;
    }
}
