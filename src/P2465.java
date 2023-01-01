import java.io.*;
import java.lang.*;
import java.util.*;

// 2469. Convert the Temperature

public class P2465 {
    public int distinctAverages(int[] nums) {
        int n = nums.length;
        Set<Double> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int j = n - 1 - i;
            set.add((nums[i] + nums[j]) / 2.0);
        }
        return set.size();
    }
}
