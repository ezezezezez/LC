// 3334. Find the Maximum Factor Score of Array

// GCD(0, x) == x
// LCM(1, x) == x
public class P3334_2 {
    public long maxScore(int[] nums) {
        int n = nums.length;
        long g = 0, l = 1;
        for (int i = 0; i < n; i++) {
            g = gcd(g, nums[i]);
            l = lcm(l, nums[i]);
        }
        long ret = g * l;
        for (int i = 0; i < n; i++) {
            long tg = 0, tl = 1;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                tg = gcd(tg, nums[j]);
                tl = lcm(tl, nums[j]);
            }
            ret = Math.max(ret, tg * tl);
        }

        return ret;
    }

    long gcd(long a, long b) {
        return b > 0 ? gcd(b, a % b) : a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
