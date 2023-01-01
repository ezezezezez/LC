import java.io.*;
import java.lang.*;
import java.util.*;

// 2420. Find All Good Indices

public class P2420 {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        List<Integer> ret = new ArrayList<>();
        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int cnt = 1;
        for (int i = 1; i < n - 1; i++) {
            if (cnt >= k && right[i + 1] >= k) {
                ret.add(i);
            }
            cnt = nums[i] <= nums[i - 1] ? cnt + 1 : 1;
        }
        return ret;
    }
}
