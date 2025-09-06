import java.util.Arrays;
import java.util.PriorityQueue;

// 3377. Digit Operations to Make Two Integers Equal
public class P3377 {
    public int minOperations(int n, int m) {
        if (isp[n]) return -1;
        int[] d = new int[10000];
        Arrays.fill(d, Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(d[a], d[b]));
        d[n] = n;
        pq.offer(n);
        boolean[] vis = new boolean[10000];
        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (u == m) return d[u];
            if (vis[u]) continue;
            vis[u] = true;

            int t = u;
            int[] ud = new int[4];
            int idx = 0;
            while (t > 0) {
                ud[idx] = t % 10;
                t /= 10;
                idx++;
            }
            for (int i = 0; i < idx; i++) {
                if (ud[i] < 9) {
                    ud[i]++;
                    int v = 0;
                    for (int j = idx - 1; j >= 0; j--) {
                        v = 10 * v + ud[j];
                    }
                    if (!isp[v] && d[u] + v < d[v]) {
                        d[v] = d[u] + v;
                        pq.offer(v);
                    }
                    ud[i]--;
                }
                if (ud[i] > 0) {
                    ud[i]--;
                    int v = 0;
                    for (int j = idx - 1; j >= 0; j--) {
                        v = 10 * v + ud[j];
                    }
                    if (!isp[v] && d[u] + v < d[v]) {
                        d[v] = d[u] + v;
                        pq.offer(v);
                    }
                    ud[i]++;
                }
            }
        }
        return -1;
    }

    static int MAXV = 10000;
    static boolean[] isp = new boolean[MAXV + 5];

    static {
        Arrays.fill(isp, true);
        isp[0] = false;
        isp[1] = false;
        for (int i = 2; i * i <= MAXV; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= MAXV; j += i) {
                    isp[j] = false;
                }
            }
        }
    }
}
