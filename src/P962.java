import java.util.*;
import java.io.*;
import java.lang.*;

// 962. Maximum Width Ramp

public class P962 {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int ret = 0;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> {
            if (nums[a] != nums[b]) return Integer.compare(nums[a], nums[b]);
            return Integer.compare(a, b);
        });

        int idx = ids[0];
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, ids[i] - idx);
            idx = Math.min(idx, ids[i]);
        }

        return ret;
    }
}
