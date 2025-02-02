import java.util.HashMap;
import java.util.Map;

public class P2560 {
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int lo = 0, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                    i++;
                }
            }
            if (cnt >= k) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return t;
    }
}
