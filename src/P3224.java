import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

// 3224. Minimum Array Changes to Make Differences Equal
public class P3224 {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        List<int[]> diffs = new ArrayList<>();
        List<Integer> maxDiffs = new ArrayList<>();
        for (int i = 0; i < n / 2; i++) {
            int j = n - 1 - i;
            int maxDiff = 0;
            if (nums[i] > nums[j]) {
                maxDiff = Math.max(k - nums[j], nums[i]);
            } else if (nums[j] > nums[i]) {
                maxDiff = Math.max(k - nums[i], nums[j]);
            } else {
                maxDiff = Math.max(k - nums[i], nums[i]);
            }
            diffs.add(new int[] {nums[i], nums[j]});
            maxDiffs.add(maxDiff);
        }
        Collections.sort(diffs, (a, b) -> Integer.compare(Math.abs(a[0] - a[1]), Math.abs(b[0] - b[1])));
        Collections.sort(maxDiffs);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] pair : diffs) {
            tm.merge(Math.abs(pair[0] - pair[1]), 1, Integer::sum);
        }
        int ret = Integer.MAX_VALUE;
        int pre = 0;
        for (int gap : tm.keySet()) {
            int cnt = tm.get(gap);
            int rem = n / 2 - pre - cnt; // one
            int idx = lowerBound(maxDiffs, 0, maxDiffs.size(), gap) - 1;
            int two = idx + 1;
            int one = pre - two;
            ret = Math.min(ret, 2 * two + one + rem);
            pre += cnt;
        }
        return ret;
    }

    int lowerBound(List<Integer> nums, int lo, int hi, int val) {
        lo--;
        while (hi - lo > 1) {
            int mid = (lo + hi) >> 1;
            if (nums.get(mid) >= val) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
