import java.io.*;
import java.lang.*;
import java.util.*;

// 2492. Minimum Score of a Path Between Two Cities

public class P2497 {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int ret = Integer.MIN_VALUE;
        int n = vals.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, p -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (vals[b] > 0) g[a].add(b);
            if (vals[a] > 0) g[b].add(a);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(g[i], (a, b) -> Integer.compare(vals[b], vals[a]));
            // System.out.println(g[i]);
        }
        for (int i = 0; i < n; i++) {
            int sum = vals[i];
            for (int j = 0; j < g[i].size() && j < k && vals[g[i].get(j)] > 0; j++) {
                sum += vals[g[i].get(j)];
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }
}
