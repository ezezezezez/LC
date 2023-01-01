import java.io.*;
import java.lang.*;
import java.util.*;

// 2239. Find Closest Number to Zero

public class P2239 {
    public int findClosestNumber(int[] nums) {
        int n = nums.length;
        int ret = Integer.MIN_VALUE + 1, diff = -ret;
        for (int num : nums) {
            int d = Math.abs(num);
            if (d < diff) {
                ret = num;
                diff = d;
            } else if (d == diff) {
                ret = Math.max(ret, num);
            }
        }
        return ret;
    }
}
