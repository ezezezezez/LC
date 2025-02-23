import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2841. Maximum Sum of Almost Unique Subarray
public class P2841 {
    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();
        long ret = 0, cur = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            cur += num;
            map.merge(num, 1, Integer::sum);
            if (i >= k - 1) {
                if (i > k - 1) {
                    int pre = nums.get(i - k);
                    cur -= pre;
                    map.merge(pre, -1, Integer::sum);
                    if (map.get(pre) == 0) map.remove(pre);
                }
                if (map.size() >= m) ret = Math.max(ret, cur);
            }
        }
        return ret;
    }
}
