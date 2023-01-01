import java.io.*;
import java.lang.*;
import java.util.*;

// 1814. Count Nice Pairs in an Array

public class P1814 {
    public int countNicePairs(int[] nums) {
        int n = nums.length;
        int[] revNums = new int[n];
        for (int i = 0; i < n; i++) {
            revNums[i] = calcRev(nums[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 0, mod = (int)(1e9 + 7);
        for (int i = n - 1; i >= 0; i--) {
            int f = nums[i] - revNums[i];
            ret = (ret + map.getOrDefault(f, 0)) % mod;
            map.merge(f, 1, Integer::sum);
        }
        return ret;
    }

    int calcRev(int num) {
        int[] d = new int[15];
        int idx = 0;
        while (num > 0) {
            d[idx++] = num % 10;
            num /= 10;
        }
        int ret = 0;
        for (int i = 0; i < idx; i++) {
            ret = 10 * ret + d[i];
        }
        return ret;
    }
}
