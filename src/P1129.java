import java.util.*;
import java.io.*;
import java.lang.*;

// 1129. Shortest Path with Alternating Colors

public class P1129 {
    int[] ret;
    Set<Integer>[] reds;
    Set<Integer>[] blues;
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        ret = new int[n];
        Arrays.fill(ret, Integer.MAX_VALUE);
        reds = new Set[n];
        blues = new Set[n];
        Arrays.setAll(reds, k -> new HashSet<>());
        Arrays.setAll(blues, k -> new HashSet<>());
        for (int[] redEdge : redEdges) {
            int a = redEdge[0], b = redEdge[1];
            reds[a].add(b);
        }
        for (int[] blueEdge : blueEdges) {
            int a = blueEdge[0], b = blueEdge[1];
            blues[a].add(b);
        }

        ret[0] = 0;
        boolean[][] vis = new boolean[n][2];
        vis[0][0] = vis[0][1] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0}); q.offer(new int[]{0, 0, 1});

        while (!q.isEmpty()) {
            int[] x = q.poll();
            int node = x[0], dist = x[1], color = x[2], nxtColor = color ^ 1;
            for (int nxt : color == 0 ? blues[node] : reds[node]) {
                if (vis[nxt][nxtColor]) continue;
                vis[nxt][nxtColor] = true;
                ret[nxt] = Math.min(ret[nxt], dist + 1);
                q.offer(new int[]{nxt, dist + 1, nxtColor});
            }
        }
        for (int i = 0; i < n; i++) {
            if (ret[i] == Integer.MAX_VALUE) ret[i] = -1;
        }

        return ret;
    }
}
