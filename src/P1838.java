import java.io.*;
import java.lang.*;
import java.util.*;

// 1838. Frequency of the Most Frequent Element

public class P1838 {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 1;
        long sum = 0;
        for (int i = 1, j = 0; i < n; i++) {
            sum += (nums[i] - nums[i - 1] + 0L) * (i - j);
            while (sum > k) {
                sum -= nums[i] - nums[j++];
            }
            ret = Math.max(ret, i - j + 1);
        }
        return ret;
    }
}
