import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

// 3097. Shortest Subarray With OR at Least K II
public class P3097_2 {
    public int minimumSubarrayLength(int[] nums, int k) {
        return logTrick2(nums, (a, b) -> a | b, k);
    }

    public int logTrick2(int[] nums, IntBinaryOperator op, int k) {
        int ret = Integer.MAX_VALUE;
        List<int[]> dp = new ArrayList<>();
        int n = nums.length;

        for (int pos = 0; pos < n; pos++) {
            int cur = nums[pos];
            for (int[] seg : dp) {
                seg[2] = op.applyAsInt(seg[2], cur);
            }
            dp.add(new int[] { pos, pos + 1, cur });

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
                if (key >= k) ret = Math.min(ret, pos - seg[1] + 2);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}
