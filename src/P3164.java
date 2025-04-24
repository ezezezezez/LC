import java.util.HashMap;
import java.util.Map;

// 3164. Find the Number of Good Pairs II
public class P3164 {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        long ret = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums2) {
            cnt.merge(num, 1, Integer::sum);
        }
        for (int num : nums1) {
            if (num % k != 0) continue;
            num /= k;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    ret += cnt.getOrDefault(i, 0);
                    if (i != num / i) ret += cnt.getOrDefault(num / i, 0);
                }
            }
        }
        return ret;
    }
}
