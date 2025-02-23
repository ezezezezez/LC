import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

// 2817. Minimum Absolute Difference Between Elements With Constraint
public class P2817 {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        int n = nums.size();
        TreeSet<Integer> set = new TreeSet<>();
        int ret = Integer.MAX_VALUE;
        for (int i = x; i < n; i++) {
            set.add(nums.get(i - x));
            int num = nums.get(i);
            if (set.ceiling(num) != null) {
                ret = Math.min(ret, Math.abs(num - set.ceiling(num)));
            }
            if (set.floor(num) != null) {
                ret = Math.min(ret, Math.abs(num - set.floor(num)));
            }
        }
        return ret;
    }
}
