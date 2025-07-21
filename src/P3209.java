import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

// 3209. Number of Subarrays With AND Value of K

public class P3209 {
    public long countSubarrays(int[] nums, int k) {
        return logTrick(nums, (a, b) -> a & b).getOrDefault(k, 0L);
    }

    public Map<Integer, Long> logTrick(int[] nums, IntBinaryOperator op) {
        Map<Integer, Long> res = new HashMap<>();
        List<int[]> dp = new ArrayList<>();
        int n = nums.length;

        for (int pos = 0; pos < n; pos++) {
            int cur = nums[pos];
            for (int[] seg : dp) {
                seg[2] = op.applyAsInt(seg[2], cur);
            }
            dp.add(new int[]{pos, pos + 1, cur});

            int ptr = 0;
            for (int i = 1; i < dp.size(); i++) {
                int[] v = dp.get(i);
                if (dp.get(ptr)[2] != v[2]) {
                    ptr++;
                    dp.set(ptr, v);
                } else {
                    dp.get(ptr)[1] = v[1];
                }
            }
            dp = new ArrayList<>(dp.subList(0, ptr + 1));

            for (int[] seg : dp) {
                int key = seg[2];
                long count = seg[1] - seg[0];
                res.merge(key, count, Long::sum);
            }
        }

        return res;
    }
}
