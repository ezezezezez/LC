// 3637. Trionic Array I

public class P3637 {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        for (int p = 1; p < n - 2; p++) {
            for (int q = p + 1; q < n - 1; q++) {
                boolean flag = true;
                for (int i = 1; i <= p; i++) {
                    if (nums[i] <= nums[i - 1]) {
                        flag = false;
                        break;
                    }
                }
                for (int i = p + 1; i <= q; i++) {
                    if (nums[i] >= nums[i - 1]) {
                        flag = false;
                        break;
                    }
                }
                for (int i = q + 1; i <= n - 1; i++) {
                    if (nums[i] <= nums[i - 1]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return true;
            }
        }
        return false;
    }
}
