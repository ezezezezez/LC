import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 3350. Adjacent Increasing Subarrays Detection II

public class P3350 {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums.get(j) > nums.get(j - 1)) j++;
            list.add(j - 1);
            i = j - 1;
        }
        int ret = (list.get(1) + 1) / 2;
        for (int i = 2; i < list.size(); i++) {
            int len1 = list.get(i - 1) - list.get(i - 2), len2 = list.get(i) - list.get(i - 1);
            ret = Math.max(ret, Math.max(Math.min(len1, len2), len2 / 2));
        }

        return ret;
    }
}
