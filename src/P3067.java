import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 3067. Count Pairs of Connectable Servers in a Weighted Tree Network
public class P3067 {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        int[] ret = new int[n];
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int[] edge : edges) {
            g.get(edge[0]).add(new int[] {edge[1], edge[2]});
            g.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int[] arr : g.get(i)) {
                int nxt = arr[0], w = arr[1];
                int t = dfs(g, nxt, w, i, signalSpeed);
                ret[i] += cur * t;
                cur += t;
            }
        }
        return ret;
    }

    int dfs(List<List<int[]>> g, int cur, int sum, int last, int signalSpeed) {
        int cnt = 0;
        if (sum % signalSpeed == 0) cnt++;
        for (int[] arr : g.get(cur)) {
            int nxt = arr[0], w = arr[1];
            if (nxt == last) continue;
            cnt += dfs(g, nxt, sum + w, cur, signalSpeed);
        }
        return cnt;
    }
}
