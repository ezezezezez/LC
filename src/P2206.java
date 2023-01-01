import java.io.*;
import java.lang.*;
import java.util.*;

// 2210. Count Hills and Valleys in an Array

public class P2206 {
    public boolean divideArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i < n; i += 2) {
            if (nums[i] != nums[i - 1]) return false;
        }
        return true;
    }
}
