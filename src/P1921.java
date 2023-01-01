import java.io.*;
import java.lang.*;
import java.util.*;

// 1921. Eliminate Maximum Number of Monsters

public class P1921 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int cur = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            double u = 1.0 * a[0] / a[1], v = 1.0 * b[0] / b[1];
            return Double.compare(u, v);
        });

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{dist[i], speed[i]});
        }
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int d = node[0], s = node[1];
            int t = (d + s - 1) / s;
            if (t <= cur) return n - pq.size() - 1;
            cur++;
        }
        return n;
    }
}
