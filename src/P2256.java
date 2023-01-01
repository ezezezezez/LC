import java.io.*;
import java.lang.*;
import java.util.*;

// 2256. Minimum Average Difference

public class P2256 {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];
        int diff = Integer.MAX_VALUE;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int a = (int)(prefix[i + 1] / (i + 1)), b = i == n - 1 ? 0 : (int)((prefix[n] - prefix[i + 1]) / (n - i - 1));
            if (Math.abs(a - b) < diff) {
                diff = Math.abs(a - b);
                ret = i;
            }
        }
        return ret;
    }
}
