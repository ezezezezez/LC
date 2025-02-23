import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2808. Minimum Seconds to Equalize a Circular Array
public class P2808 {
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
        }
        int lo = 0, hi = n - 1, t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean valid = false;
            for (int key : map.keySet()) {
                List<Integer> index = map.get(key);
                int first = index.get(0), last = index.get(index.size() - 1);
                boolean flag;
                if (first - mid <= 0 && last + mid >= n - 1) {
                    flag = true;
                } else if (first - mid < 0) {
                    flag = last + mid + 1 >= (first - mid + n) % n;
                } else if (last + mid > n - 1) {
                    flag = first - mid - 1 <= (last + mid) % n;
                } else {
                    flag = false;
                }
                if (!flag) continue;
                int pre = first + mid + 1;
                for (int i = 1; i < index.size(); i++) {
                    int idx = index.get(i);
                    if (idx - mid <= pre) {
                        pre = mid + idx + 1;
                    } else {
                        flag = false;
                    }
                }
                if (flag) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
