import java.util.*;

// 3254. Find the Power of K-Size Subarrays I
public class P3254 {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        for (int i = 0; i < ret.length; i++) {
            boolean flag = true;
            for (int j = 1; j < k; j++) {
                if (nums[i + j] != nums[i + j - 1] + 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ret[i] = nums[i + k - 1];
            } else {
                ret[i] = -1;
            }
        }
        return ret;
    }
}
