import java.util.Arrays;

// 3618. Split Array by Prime Indices

public class P3618 {
    static int MAXV = 100000;
    static boolean[] isp = new boolean[MAXV + 1];
    static {
        Arrays.fill(isp, true);
        isp[0] = false;
        isp[1] = false;
        for (int i = 2; i * i <= MAXV; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= MAXV; j += i) {
                    isp[j] = false;
                }
            }
        }
    }

    public long splitArray(int[] nums) {
        int n = nums.length;
        long a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (isp[i]) a += nums[i];
            else b += nums[i];
        }
        return Math.abs(a - b);
    }
}
