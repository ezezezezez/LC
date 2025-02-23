// 2789. Largest Element in an Array after Merge Operations
public class P2789 {
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long ret = nums[n - 1];
        long cur = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= cur) {
                cur += nums[i];
            } else {
                cur = nums[i];
            }
            ret = Math.max(ret, cur);
        }
        return ret;
    }
}
