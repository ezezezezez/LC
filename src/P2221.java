import java.io.*;
import java.lang.*;
import java.util.*;

// 2221. Find Triangular Sum of an Array

public class P2221 {
    public int triangularSum(int[] nums) {
        while (nums.length > 1) {
            int n = nums.length;
            int[] t = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                t[i] = (nums[i] + nums[i + 1]) % 10;
            }
            nums = t;
        }
        return nums[0];
    }
}
