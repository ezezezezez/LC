import java.io.*;
import java.lang.*;
import java.util.*;

// 1493. Longest Subarray of 1's After Deleting One Element

public class P1493 {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        int ret = 0;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            // System.out.println(lo + " " + hi);
            if (validate(nums, mid)) {
                ret = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ret;
    }

    boolean validate(int[] nums, int len) {
        int n = nums.length;
        int cur = 0;
        int preZero = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cur++;
                if (cur >= len) {
                    return true;
                }
            } else {
                cur = i - preZero - 1;
                preZero = i;
            }
        }
        return false;
    }

    public int longestSubarray2(int[] nums) {
        int n = nums.length;
        int zeroCnt = 0, ret = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] == 0) zeroCnt++;
            while (zeroCnt > 1) {
                if (nums[j++] == 0) zeroCnt--;
            }
            ret = Math.max(ret, i - j);
        }
        return ret;
    }
}
