import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// 2866. Beautiful Towers II
public class P2866 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long ret = 0;
        int[] pre = new int[n], suf = new int[n];
        long[] left = new long[n + 1], right = new long[n + 1];
        Arrays.fill(pre, -1);
        Arrays.fill(suf, n);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            long num = maxHeights.get(i);
            while (!dq.isEmpty() && maxHeights.get(dq.peekLast()) >= num) dq.pollLast();
            if (!dq.isEmpty()) pre[i] = dq.peekLast();
            dq.offerLast(i);
            left[i + 1] = (i - pre[i]) * num + left[pre[i] + 1];
        }
        Deque<Integer> dq2 = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            long num = maxHeights.get(i);
            while (!dq2.isEmpty() && maxHeights.get(dq2.peekLast()) >= num) dq2.pollLast();
            if (!dq2.isEmpty()) suf[i] = dq2.peekLast();
            dq2.offerLast(i);
            right[i] = (suf[i] - i) * num + right[suf[i]];
        }
        // System.out.println(Arrays.toString(pre));
        // System.out.println(Arrays.toString(suf));
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, left[i + 1] + right[i] - maxHeights.get(i));
        }
        return ret;
    }
}
