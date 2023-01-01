import java.io.*;
import java.lang.*;
import java.util.*;

// 2467. Most Profitable Path in a Tree

public class P2470 {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int d = 0;
            int lcm = nums[i];
            for (int j = i; j < n; j++) {
                d = gcd(lcm, nums[j]);
                lcm = lcm * nums[j] / d;
                if (lcm == k) ret++;
                else if (lcm > k) break;
            }
        }
        return ret;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public int subarrayLCM2(int[] nums, int k) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int lcm = nums[i];
            for (int j = i; j < n; j++) {
                lcm = lcm * nums[j] / gcd(lcm, nums[j]);
                if (lcm == k) ret++;
                else if (lcm > k) break;
            }
        }
        return ret;
    }
}
