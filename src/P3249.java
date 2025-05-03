import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 3249. Count the Number of Good Nodes
public class P3249 {
    int ret;
    Map<Integer, List<Integer>> g;
    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        g = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        dfs(0, -1);
        return ret;
    }

    int dfs(int cur, int pre) {
        int t = -1, cnt = 0;
        boolean flag = true;
        for (int nxt : g.get(cur)) {
            if (nxt == pre) continue;
            int v = dfs(nxt, cur);
            cnt += v;
            if (t == -1) t = v;
            else if (t != v) flag = false;
        }
        if (flag) ret++;
        return cnt + 1;
    }
}
