import java.util.HashMap;
import java.util.Map;

public class P2597 {
    int ret = 0;
    int k;
    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        this.k = k;
        dfs(nums, 0, new HashMap<>());
        return ret - 1;
    }

    void dfs(int[] nums, int cur, Map<Integer, Integer> map) {
        int n = nums.length;
        if (cur == n) {
            ret++;
            return;
        }
        int a = nums[cur] - k, b = nums[cur] + k;
        if (!map.containsKey(a) && !map.containsKey(b)) {
            map.merge(nums[cur], 1, Integer::sum);
            dfs(nums, cur + 1, map);
            map.merge(nums[cur], -1, Integer::sum);
            if (map.get(nums[cur]) == 0) map.remove(nums[cur]);
        }
        dfs(nums, cur + 1, map);
    }
}
