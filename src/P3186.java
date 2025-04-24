import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3186. Maximum Total Damage With Spell Casting
public class P3186 {
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int p : power) map.merge(p, 1, Integer::sum);
        power = Arrays.stream(power).distinct().sorted().toArray();
        int n = power.length;
        long[] f = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int p = power[i];
            int idx = lowerBound(power, 0, i, p - 2) - 1;
            f[i + 1] = Math.max(f[i], (long) p * map.get(p) + f[idx + 1]);
        }
        // System.out.println(Arrays.toString(f));
        return f[n];
    }

    int lowerBound(int[] nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums[mid] >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
