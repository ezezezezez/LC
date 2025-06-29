// 3576. Transform Array to All Equal Elements
public class P3576_2 {
    public boolean canMakeEqual(int[] nums, int k) {
        return check(nums, k, 1) || check(nums, k, -1);
    }

    boolean check(int[] nums, int k, int target) {
        int n = nums.length;
        boolean flip = false;
        for (int i = 0; i < n; i++) {
            int v = flip ? -nums[i] : nums[i];
            if (v != target) {
                if (i == n - 1 || k <= 0) return false;
                flip = true;
                k--;
            } else {
                flip = false;
            }
        }
        return true;
    }
}
