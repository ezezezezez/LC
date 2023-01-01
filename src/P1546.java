import java.io.*;
import java.lang.*;
import java.util.*;

// 1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target

public class P1546 {
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] f = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                f[i + 1] = Math.max(f[i], 1 + f[map.get(sum - target) + 1]);
            } else {
                f[i + 1] = f[i];
            }
            map.put(sum, i);
        }
        // System.out.println(map);
        // System.out.println(Arrays.toString(f));
        return f[n];
    }
}
