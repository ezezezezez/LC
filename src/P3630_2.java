import java.util.Arrays;

// 3630. Partition Array for Maximum XOR and AND
public class P3630_2 {
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
            int mx = getXORMax(tmp, idx);
            ret = Math.max(ret, 0L + sum + xor + 2L * mx);
            idx = 0;
            Arrays.fill(p, 0);
        }

        return ret;
    }

    int getXORMax(int[] nums, int n) {
        int[] p = new int[32];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = 31; j >= 0; j--) {
                if ((num & (1 << j)) == 0) continue;
                if (p[j] == 0) {
                    p[j] = num;
                    break;
                }
                num ^= p[j];
            }
        }

        int mx = 0;
        for (int i = 31; i >= 0; i--) {
            mx = Math.max(mx, mx ^ p[i]);
        }

        return mx;
    }
}
