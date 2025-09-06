import java.util.Arrays;

// 3630. Partition Array for Maximum XOR and AND
public class P3630 {
    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length;
        long ret = 0;
        int mask = (1 << n) - 1;
        int[] tmp = new int[n];
        int idx = 0;
        int[] p = new int[32];
        for (int i = 0; i < (1 << n); i++) {
            int sum = i == 0 ? 0 : -1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum &= nums[j];
                }
            }
            int r = mask ^ i;
            int xor = 0;
            for (int j = 0; j < n; j++) {
                if ((r & (1 << j)) > 0) xor ^= nums[j];
            }
            int rxor = ~xor;
            for (int j = 0; j < n; j++) {
                if ((r & (1 << j)) > 0) tmp[idx++] = nums[j] & rxor;
            }
            for (int j = 0; j < idx; j++) {
                int x = tmp[j];
                for (int k = 31; k >= 0; k--) {
                    if ((x & (1 << k)) == 0) continue;
                    if (p[k] == 0) {
                        p[k] = x;
                        break;
                    }
                    x ^= p[k];
                }
            }
            int mx = 0;
            for (int k = 31; k >= 0; k--) {
                mx = Math.max(mx, mx ^ p[k]);
            }
            ret = Math.max(ret, 0L + sum + xor + 2L * mx);
            idx = 0;
            Arrays.fill(p, 0);
        }

        return ret;
    }
}
