import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 3020. Find the Maximum Number of Elements in Subset
public class P3020 {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        Map<Long, Integer> map = new HashMap<>();
        long max = 0;
        for (int num : nums) {
            map.merge((long) num, 1, Integer::sum);
            max = Math.max(max, num);
        }
        int ret = 1;
        if (map.containsKey(1L)) {
            ret = map.get(1L) % 2 == 1 ? map.get(1L) : (map.get(1L) - 1);
        }
        for (long i = 2; i <= 100000 && i * i <= max; i++) {
            boolean valid = true;
            long j = i;
            int cnt = 1;
            while (map.getOrDefault(j, 0) >= 2) {
                j *= j;
                if (j > max) break;
                if (map.containsKey(j)) {
                    ret = Math.max(ret, 2 * cnt + 1);
                }
                cnt++;
            }
        }
        return ret;
    }
}
