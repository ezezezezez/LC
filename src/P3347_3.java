import java.util.Arrays;

// 3347. Maximum Frequency of an Element After Performing Operations II

public class P3347_3 {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = 0;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j + 1 < n && nums[j + 1] == nums[i]) {
                j++;
            }
            int cnt = j - i + 1;
            while (nums[left] < nums[i] - k) left++;
            while (right < n && nums[right] <= nums[i] + k) right++;
            ret = Math.max(ret, cnt + Math.min(right - left - cnt, numOperations));
        }
        if (ret >= numOperations) return ret;
        left = 0;
        for (int i = 0; i < n; i++) {
            while (nums[left] < nums[i] - 2 * k) left++;
            ret = Math.max(ret, Math.min(i - left + 1, numOperations));
        }
        return ret;
    }
}
