import java.io.*;
import java.lang.*;
import java.util.*;

// 1911. Maximum Alternating Subsequence Sum

public class P1911 {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long mx = 0;
        long neg = 0;
        for (int num : nums) {
            long t = mx;
            mx = Math.max(mx, neg + num);
            neg = Math.max(neg, t - num);
            // System.out.println(mx + " " + neg);
        }
        return mx;
    }
}
