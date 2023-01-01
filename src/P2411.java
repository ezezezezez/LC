import java.io.*;
import java.lang.*;
import java.util.*;

// 2411. Smallest Subarrays With Maximum Bitwise OR

public class P2411 {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        int[] cnt = new int[32];
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & nums[n - 1]) > 0) {
                cnt[i]++;
            }
        }
        ret[n - 1] = 1;
        int mx = nums[n - 1];
        for (int i = n - 2, v = n - 1; i >= 0; i--) {
            for (int j = 0; j < 31; j++) {
                if (((1 << j) & nums[i]) > 0) {
                    cnt[j]++;
                }
            }
            while (v > i) {
                boolean flag = true;
                for (int j = 0; j < 31; j++) {
                    if (((1 << j) & nums[v]) > 0 && cnt[j] - 1 == 0) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
                for (int j = 0; j < 31; j++) {
                    if (((1 << j) & nums[v]) > 0) {
                        cnt[j]--;
                    }
                }
                v--;
            }
            ret[i] = v - i + 1;
        }
        return ret;
    }
}
