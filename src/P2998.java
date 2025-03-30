import java.util.ArrayDeque;
import java.util.Deque;

// 2998. Minimum Number of Operations to Make X and Y Equal
public class P2998 {
    public int minimumOperationsToMakeEqual(int x, int y) {
        boolean[] vis = new boolean[2 * Math.max(x, y) + 1];
        vis[x] = true;
        int step = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(x);
        while (!dq.isEmpty()) {
            int sz = dq.size();
            for (int i = 0; i < sz; i++) {
                int t = dq.poll();
                if (t == y) return step;
                if (t % 11 == 0 && !vis[t / 11]) {
                    vis[t / 11] = true;
                    dq.offer(t / 11);
                }
                if (t % 5 == 0 && !vis[t / 5]) {
                    vis[t / 5] = true;
                    dq.offer(t / 5);
                }
                if (t + 1 < vis.length && !vis[t + 1]) {
                    vis[t + 1] = true;
                    dq.offer(t + 1);
                }
                if (t - 1 >= 0 && !vis[t - 1]) {
                    vis[t - 1] = true;
                    dq.offer(t - 1);
                }
            }
            step++;
        }
        return -1;
    }
}
