import java.util.*;
import java.io.*;
import java.lang.*;

// 1248. Count Number of Nice Subarrays

public class P1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> ids = new ArrayList<>();
        ids.add(-1);
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) {
                ids.add(i);
            }
        }
        ids.add(n);
        int ret = 0;

        for (int i = 1, j = 1; i < ids.size() - 1; i++) {
            if (i - j + 1 > k) j++;
            int cnt = i - j + 1;
            if (cnt >= k) {
                int left = ids.get(j - 1), right = ids.get(i + 1);
                ret += (ids.get(j) - left) * (right - ids.get(i));
            }
        }

        return ret;
    }
}
