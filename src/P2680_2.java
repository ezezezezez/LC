// 2680. Maximum OR
public class P2680_2 {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long ret = 0;
        int[] suf = new int[n + 1];
        for (int i = n - 1; i > 0; i--) {
            suf[i] = suf[i + 1] | nums[i];
        }
        int pre = 0;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, pre | ((long) nums[i] << k) | suf[i + 1]);
            pre |= nums[i];
        }
        return ret;
    }
}
