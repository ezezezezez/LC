import java.util.Arrays;
import java.util.PriorityQueue;

public class P2593 {
    public long findScore(int[] nums) {
        int n = nums.length;
        long ret = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {nums[i], i});
        }
        boolean[] mark = new boolean[n];
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int num = pair[0], idx = pair[1];
            if (mark[idx]) continue;
            ret += num;
            mark[idx] = true;
            if (idx - 1 >= 0) mark[idx - 1] = true;
            if (idx + 1 < n) mark[idx + 1] = true;
        }
        return ret;
    }
}
