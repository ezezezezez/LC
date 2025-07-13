// 3604. Minimum Time to Reach Destination in Directed Graph

public class P3171 {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            ret = Math.min(ret, Math.abs(nums[i] - k));
            for (int j = i - 1; j >= 0; j--) {
                int or = nums[j] | nums[i];
                if (or <= nums[j]) break;
                ret = Math.min(ret, Math.abs(or - k));
                nums[j] = or;
            }
        }

        return ret;
    }
}
