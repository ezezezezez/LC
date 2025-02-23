import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2860. Happy Students
public class P2860 {
    public int countWays(List<Integer> nums) {
        int n = nums.size();
        Collections.sort(nums);
        int ret = 0;
        if (nums.get(0) > 0) ret++;
        for (int i = 0; i < n; i++) {
            if (i + 1 <= nums.get(i)) continue;
            if (i < n - 1 && nums.get(i + 1) <= i + 1) continue;
            ret++;
        }
        return ret;
    }
}
