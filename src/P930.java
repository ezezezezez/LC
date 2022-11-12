import java.util.*;
import java.io.*;
import java.lang.*;

// 930. Binary Subarrays With Sum

public class P930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ret = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum - goal >= 0) {
                ret += map.getOrDefault(sum - goal, 0);
            }

            map.merge(sum, 1, Integer::sum);
        }

        return ret;
    }
}
