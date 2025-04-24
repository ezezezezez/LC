import java.util.Arrays;

// 3180. Maximum Total Reward Using Operations I
public class P3180_2 {
    public int maxTotalReward(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int mx = nums[n - 1];
        boolean[] f = new boolean[mx];
        f[0] = true;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = 0; j < num; j++) {
                if (f[j]) {
                    ret = Math.max(ret, j + num);
                    if (j + num < mx) {
                        f[j + num] = true;
                    }
                }
            }
        }
        return ret;
    }
}
