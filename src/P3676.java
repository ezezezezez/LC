// 3676. Count Bowl Subarrays

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P3676 {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        long ret = 0;
        int[] prevGreater = new int[n], nextGreater = new int[n];
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextGreater, -1);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && nums[i] > nums[dq.peek()]) {
                nextGreater[dq.pop()] = i;
            }
            prevGreater[i] = dq.isEmpty() ? -1 : dq.peek();
            dq.push(i);
        }
        for (int i = 0; i < n; i++) {
            if (prevGreater[i] != -1 && i - prevGreater[i] >= 2) ret++;
            if (nextGreater[i] != -1 && nextGreater[i] - i >= 2) ret++;
        }
        return ret;
    }
}
