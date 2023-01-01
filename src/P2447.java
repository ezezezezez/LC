import java.io.*;
import java.lang.*;
import java.util.*;

// 2443. Sum of Number and Its Reverse

public class P2447 {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int d = 0;
            for (int j = i; j < n; j++) {
                d = gcd(d, nums[j]);
                if (d == k) ret++;
            }
        }
        return ret;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
