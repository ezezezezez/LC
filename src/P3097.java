// 3097. Shortest Subarray With OR at Least K II
public class P3097 {
    public int minimumSubarrayLength(int[] nums, int k) {
        if (k == 0) return 1;
        int n = nums.length;
        int[] cnt = new int[32];
        int ret = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < n; i++) {
            for (int p = 0; p < 32; p++) {
                cnt[p] += (nums[i] & (1 << p)) > 0 ? 1 : 0;
            }
            int v = 0;
            for (int p = 0; p < 32; p++) {
                if (cnt[p] > 0) v += 1 << p;
            }
            while (v >= k) {
                ret = Math.min(ret, i - j + 1);
                for (int p = 0; p < 32; p++) {
                    cnt[p] -= (nums[j] & (1 << p)) > 0 ? 1 : 0;
                }
                v = 0;
                for (int p = 0; p < 32; p++) {
                    if (cnt[p] > 0) v += 1 << p;
                }
                j++;
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
