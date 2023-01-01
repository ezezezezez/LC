import java.io.*;
import java.lang.*;
import java.util.*;

// 2395. Find Subarrays With Equal Sum

public class P2395 {
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < n; i++) {
            int sum = nums[i - 1] + nums[i];
            if (set.contains(sum)) return true;
            set.add(sum);
        }
        return false;
    }
}
