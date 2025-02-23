import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 2786. Visit Array Positions to Maximize Score
public class P2786 {
    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long a = nums[0] % 2 == 0 ? nums[0] : Integer.MIN_VALUE, b = nums[0] % 2 == 0 ? Integer.MIN_VALUE : nums[0];
        long ret = Math.max(a, b);
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == 0) {
                a = Math.max(a + nums[i], b + nums[i] - x);
            } else {
                b = Math.max(b + nums[i], a + nums[i] - x);
            }
            ret = Math.max(ret, Math.max(a, b));
        }
        return ret;
    }
}
