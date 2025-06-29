// 3587. Minimum Adjacent Swaps to Alternate Parity

public class P3587 {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int odd = 0, even = 0;
        for (int num : nums) {
            if (num % 2 == 0) even++;
            else odd++;
        }
        if (Math.abs(odd - even) >= 2) return -1;
        if (odd == even) {
            int j = 0;
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] % 2 == 0) {
                    cnt1 += Math.abs(j - i);
                    j += 2;
                }
            }
            j = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] % 2 == 1) {
                    cnt2 += Math.abs(j - i);
                    j += 2;
                }
            }
            return Math.min(cnt1, cnt2);
        } else if (odd > even) {
            int j = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] % 2 == 1) {
                    cnt += Math.abs(j - i);
                    j += 2;
                }
            }
            return cnt;
        } else {
            int j = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] % 2 == 0) {
                    cnt += Math.abs(j - i);
                    j += 2;
                }
            }
            return cnt;
        }
    }
}
