// 2653. Sliding Subarray Beauty
public class P2653 {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ret = new int[n - k + 1];
        int[] cnt = new int[101];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            cnt[num + 50]++;
            if (i < k - 1) continue;
            if (i >= k) cnt[nums[i - k] + 50]--;
            int cur = 0;
            for (int j = 0; j <= 49; j++) {
                cur += cnt[j];
                if (cur >= x) {
                    ret[i - k + 1] = j - 50;
                    break;
                }
            }
        }
        return ret;
    }
}
