import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 3542. Minimum Operations to Convert All Elements to Zero
public class P3542 {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ret = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int num : nums) {
            int last = -1;
            while (!dq.isEmpty() && dq.peekLast() > num) {
                int x = dq.pollLast();
                if (last == -1 || last != x) {
                    ret++;
                }
                last = x;
            }
            dq.offerLast(num);
        }
        int last = -1;
        while (!dq.isEmpty() && dq.peekLast() > 0) {
            int x = dq.pollLast();
            if (last == -1 || last != x) {
                ret++;
            }
            last = x;
        }
        return ret;
    }
}
