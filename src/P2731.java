import java.util.Arrays;

// 2731. Movement of Robots
public class P2731 {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] pos = new long[n];
        for (int i = 0; i < n; i++) {
            pos[i] = nums[i] + ((s.charAt(i) == 'L' ? -1L : 1L) * d);
        }
        Arrays.sort(pos);
        long ret = 0, mod = (int) (1e9 + 7);
        long right = 0;
        for (int i = 1; i < n; i++) {
            right += pos[i] - pos[0];
        }
        right %= mod;
        for (int i = 0; i < n; i++) {
            ret = (ret + right) % mod;
            if (i + 1 < n) {
                right = (right - (n - (i + 1)) * (pos[i + 1] - pos[i])) % mod;
                right = (right + mod) % mod;
            }
        }
        return (int) ret;
    }
}
