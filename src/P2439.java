import java.io.*;
import java.lang.*;
import java.util.*;

// 2439. Minimize Maximum of Array

public class P2439 {
    public int minimizeArrayValue(int[] nums) {
        int n = nums.length;
        int lo = 0, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            long sum = 0;
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                if (sum > 1L * mid * (i + 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
