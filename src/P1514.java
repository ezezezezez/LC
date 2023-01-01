import java.io.*;
import java.lang.*;
import java.util.*;

// 1513. Number of Substrings With Only 1s

public class P1514 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Set<Edge>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int i = 0; i < succProb.length; i++) {
            int[] edge = edges[i];
            int a = edge[0], b = edge[1];
            g[a].add(new Edge(b, succProb[i]));
            g[b].add(new Edge(a, succProb[i]));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        pq.offer(new Node(start, 1));
        boolean[] vis = new boolean[n];
        double[] dist = new double[n];
        dist[start] = 1;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (vis[cur.node]) continue;
            vis[cur.node] = true;
            for (Edge nxt : g[cur.node]) {
                double nxtProb = cur.prob * nxt.prob;
                if (nxtProb > dist[nxt.to]) {
                    dist[nxt.to] = nxtProb;
                    pq.offer(new Node(nxt.to, nxtProb));
                }
            }
        }
        // System.out.println(Arrays.toString(dist));

        return dist[end];
    }

    static class Node {
        int node;
        double prob;
        Node(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }

    static class Edge {
        int to;
        double prob;
        Edge(int to, double prob) {
            this.to = to;
            this.prob = prob;
        }
    }
}
