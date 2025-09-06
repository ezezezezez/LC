// 3670. Maximum Product of Two Integers With No Common Bits
public class P3670 {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        long ret = 0;
        int mx = 0;
        for (int i = 0; i < n; i++) mx = Math.max(mx, nums[i]);
        int B = 0;
        while ((1 << B) <= mx) B++;
        int SZ = (1 << B);
        int[] f = new int[SZ];
        for (int num : nums) f[num] = num;

        for (int i = 0; i < B; i++) {
            for (int m = 0; m < SZ; m++) {
                if ((m & (1 << i)) > 0) {
                    f[m] = Math.max(f[m], f[m ^ (1 << i)]);
                }
            }
        }

        for (int num : nums) {
            int rm = ~num & (SZ - 1);
            int v = f[rm];
            ret = Math.max(ret, 1L * num * v);
        }

        return ret;
    }
}
