// 2909. Minimum Sum of Mountain Triplets II
public class P2909 {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 2; i--) {
            suf[i] = Math.min(suf[i + 1], nums[i]);
        }
        int pre = nums[0];
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            pre = Math.min(pre, nums[i - 1]);
            if (pre < nums[i] && suf[i + 1] < nums[i]) {
                ret = Math.min(ret, pre + nums[i] + suf[i + 1]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
