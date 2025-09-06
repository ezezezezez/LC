import java.util.HashMap;
import java.util.Map;

// 3659. Partition Array Into K-Distinct Groups
public class P3659 {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) return false;
        int cnt = n / k;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.merge(num, 1, Integer::sum);
        for (int c : map.values()) {
            if (c > cnt) return false;
        }
        return true;
    }
}
