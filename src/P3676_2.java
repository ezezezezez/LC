// 3676. Count Bowl Subarrays

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P3676_2 {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        long ret = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(0);
        for (int i = 1; i < n; i++) {
            int mx = -1, cnt = 0;
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                int j = dq.pollLast();
                mx = Math.max(mx, nums[j]);
                if (mx == nums[j] && i - j > 1) {
                    cnt++;
                }
            }
            // System.out.println( cnt + " " + ret);
            ret += cnt;
            if (!dq.isEmpty() && i - dq.peekLast() > 1) {
                ret += 1;
            }
            dq.offerLast(i);
        }
        return ret;
    }
}
