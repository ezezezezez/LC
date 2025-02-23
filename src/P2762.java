import java.util.*;

// 2762. Continuous Subarrays
public class P2762 {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long ret = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.merge(nums[0], 1, Integer::sum);
        int j = 0;
        for (int i = 1; i < n; i++) {
            map.merge(nums[i], 1, Integer::sum);
            while (Math.abs(map.lastKey() - map.firstKey()) > 2) {
                map.merge(nums[j], -1, Integer::sum);
                if (map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }
            ret += i - j + 1;
        }
        return ret;
    }
}
