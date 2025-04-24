import java.util.*;

// 3115. Maximum Prime Difference
public class P3115 {
    public int maximumPrimeDifference(int[] nums) {
        int n = nums.length;
        int first = -1, last = -1;
        for (int i = 0; i < n; i++) {
            boolean prime = nums[i] > 1;
            for (int j = 2; j * j <= nums[i]; j++) {
                if (nums[i] % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (first == -1 && prime) first = i;
            if (prime) last = i;
        }
        return last - first;
    }
}
