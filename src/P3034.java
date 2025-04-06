import java.util.Arrays;

// 3034. Number of Subarrays That Match a Pattern I
public class P3034 {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length;
        int ret = 0;
        for (int i = m; i < n; i++) {
            int pre = nums[i - m];
            boolean valid = true;
            for (int j = 0; j < m; j++) {
                int cur = nums[i - m + j + 1];
                if (pattern[j] == 1 && cur <= pre) {
                    valid = false;
                    break;
                }
                if (pattern[j] == 0 && cur != pre) {
                    valid = false;
                    break;
                }
                if (pattern[j] == -1 && cur >= pre) {
                    valid = false;
                    break;
                }
                pre = cur;
            }
            if (valid) ret++;
        }
        return ret;
    }
}
