import java.io.*;
import java.lang.*;
import java.util.*;

// 2359. Find Closest Node to Given Two Nodes

public class P2359 {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (edges[i] == -1) continue;
            adj.get(i).add(new Edge(edges[i], 1));
        }
        int[] a = dijkstra(node1, adj);
        int[] b = dijkstra(node2, adj);
        int ret = Integer.MAX_VALUE, idx = -1;
        for (int i = 0; i < n; i++) {
            if (Math.max(a[i], b[i]) < ret) {
                ret = Math.max(a[i], b[i]);
                idx = i;
            }
        }
        return idx;
    }

    int INF = Integer.MAX_VALUE;
    int[] p;

    int[] dijkstra(int s, List<List<Edge>> adj) {
        int n = adj.size();
        int[] d = new int[n];
        Arrays.fill(d, INF);
        p = new int[n];
        Arrays.fill(p, -1);
        boolean[] vis = new boolean[n];
        // [0]: vertex, [1]: dist
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        d[s] = 0;
        pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int v = pair[0];
            if (vis[v]) {
                continue;
            }
            if (d[v] == INF) {
                break;
            }
            vis[v] = true;
            for (Edge edge : adj.get(v)) {
                int to = edge.to;
                int len = edge.len;
                if (d[v] + len < d[to]) {
                    d[to] = d[v] + len;
                    pq.offer(new int[]{to, d[to]});
                    p[to] = v;
                }
            }
        }
        return d;
    }

    static class Edge {
        int to, len;
        Edge(int to, int len) {
            this.to = to;
            this.len = len;
        }
    }
}
