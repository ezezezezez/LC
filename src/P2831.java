import java.util.*;

// 2831. Find the Longest Equal Subarray
public class P2831 {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            map.computeIfAbsent(num, key -> new ArrayList<>()).add(i);
        }
        int lo = 1, hi = n, t = 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean valid = false;
            outer:
            for (List<Integer> list : map.values()) {
                int sz = list.size();
                int cost = 0;
                for (int i = 1, j = 0; i < sz; i++) {
                    cost += list.get(i) - list.get(i - 1) - 1;
                    if (i - j + 1 > mid) {
                        j++;
                        cost -= list.get(j) - list.get(j - 1) - 1;
                    }
                    if (i - j + 1 == mid && cost <= k) {
                        valid = true;
                        break outer;
                    }
                }
            }
            if (valid) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
