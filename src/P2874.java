// 2874. Maximum Value of an Ordered Triplet II
public class P2874 {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long ret = 0;
        int[] mxGap = new int[n];
        int mx = nums[0];
        for (int i = 1; i < n; i++) {
            mxGap[i] = Math.max(mxGap[i - 1], mx - nums[i]);
            mx = Math.max(mx, nums[i]);
        }
        for (int i = 2; i < n; i++) {
            ret = Math.max(ret, 1L * nums[i] * mxGap[i - 1]);
        }
        return ret;
    }
}
