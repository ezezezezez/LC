import java.io.*;
import java.lang.*;
import java.util.*;

// 2477. Minimum Fuel Cost to Report to the Capital

public class P2477 {
    int seats;
    Set<Integer>[] g;
    long ret;
    public long minimumFuelCost(int[][] roads, int seats) {
        int m = roads.length, n = m + 1;
        this.seats = seats;
        g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ret;
    }

    int[] dfs(int node, int pre) {
        int[] arr = new int[2];
        for (int nxt : g[node]) {
            if (nxt == pre) continue;
            int[] t = dfs(nxt, node);
            arr[0] += t[0];
            arr[1] += t[1];
        }
        if (1L * arr[0] * seats == arr[1]) {
            arr[0]++;
            arr[1]++;
        } else {
            arr[1]++;
            arr[0] = (arr[1] + seats - 1) / seats;
        }

        if (node != 0) ret += arr[0];
        // System.out.println(node + " " + arr[0] + " " + arr[1]);
        return arr;
    }
}
