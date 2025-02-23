import java.util.Arrays;

// 2779. Maximum Beauty of an Array After Applying Operation
public class P2779 {
    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ret = 1;
        int j = 0;
        int v = nums[0] + k;
        for (int i = 1; i < n; i++) {
            while (nums[i] - k > v) {
                j++;
                v = nums[j] + k;
            }
            ret = Math.max(ret, i - j + 1);
        }
        return ret;
    }
}
