import java.util.*;
import java.io.*;
import java.lang.*;

// 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

public class P1334 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Set<Edge>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            g[a].add(new Edge(b, w));
            g[b].add(new Edge(a, w));
        }

        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            pq.offer(new int[]{i, 0});
            boolean[] vis = new boolean[n];
            int[] distArr = new int[n];
            Arrays.fill(distArr, Integer.MAX_VALUE);
            distArr[i] = 0;
            while (!pq.isEmpty()) {
                int[] node = pq.poll();
                int cur = node[0];
                if (vis[cur]) continue;
                vis[cur] = true;
                cnt[i]++;
                for (Edge nxtNode : g[cur]) {
                    int nxt = nxtNode.to, nxtW = nxtNode.weight;
                    if (distArr[cur] + nxtW <= distanceThreshold && distArr[cur] + nxtW < distArr[nxt]) {
                        distArr[nxt] = distArr[cur] + nxtW;
                        pq.offer(new int[]{nxt, distArr[nxt]});
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(cnt));
        int id = 0, min = cnt[0];
        for (int i = 1; i < n; i++) {
            if (cnt[i] <= min) {
                min = cnt[i];
                id = i;
            }
        }
        return id;
    }

    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
