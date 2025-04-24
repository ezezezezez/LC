// 3192. Minimum Operations to Make Binary Array Elements Equal to One II
public class P3192 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0, mask = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if ((num ^ mask) == 0) {
                ret++;
                mask ^= 1;
            }
        }
        return ret;
    }
}
