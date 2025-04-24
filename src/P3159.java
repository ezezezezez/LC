import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3159. Find Occurrences of an Element in an Array
public class P3159 {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int n = nums.length, m = queries.length;
        int[] ret = new int[m];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < m; i++) {
            int q = queries[i];
            if (!map.containsKey(x)) {
                ret[i] = -1;
                continue;
            }
            List<Integer> list = map.get(x);
            ret[i] = q <= list.size() ? list.get(q - 1) : -1;
        }
        return ret;
    }
}
