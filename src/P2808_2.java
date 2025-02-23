import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2808. Minimum Seconds to Equalize a Circular Array
public class P2808_2 {
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }

        int ans = n;
        for (List<Integer> a : pos.values()) {
            int mx = n - a.get(a.size() - 1) + a.get(0);
            for (int i = 1; i < a.size(); i++) {
                mx = Math.max(mx, a.get(i) - a.get(i - 1));
            }
            ans = Math.min(ans, mx);
        }
        return ans / 2;
    }
}
