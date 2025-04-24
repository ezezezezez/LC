import java.util.ArrayDeque;
import java.util.Deque;

// 3523. Make Array Non-decreasing
public class P3523 {
    public int maximumPossibleSize(int[] nums) {
        int n = nums.length;
        int ret = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(nums[0]);
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            while (i < n) {
                if (num >= dq.peekLast()) {
                    dq.offerLast(num);
                    break;
                } else {
                    i++;
                    if (i < n) num = Math.max(num, nums[i]);
                }
            }
        }
        return dq.size();
    }
}
