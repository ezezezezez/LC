import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2685. Count the Number of Complete Components
public class P2685 {
    public int countCompleteComponents(int n, int[][] edges) {
        boolean[][] connected = new boolean[n][n];
        for (int i = 0; i < n; i++) connected[i][i] = true;
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            uf.union(a, b);
            connected[a][b] = connected[b][a] = true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(uf.find(i), k -> new ArrayList<>()).add(i);
        }
        int ret = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            boolean flag = true;
            outer:
            for (int v : list) {
                for (int v2 : list) {
                    if (!connected[v][v2]) {
                        flag = false;
                        break outer;
                    }
                }
            }
            if (flag) ret++;
        }
        return ret;
    }

    public class UF {
        int[] parent;
        int[] size;

        int[] rank;
        int n;
        int setCount;

        public UF(int _n) {
            n = _n;
            setCount = _n;
            parent = new int[_n];
            size = new int[_n];
            rank = new int[_n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            //        if (rank[x] < rank[y])
            if (size[x] < size[y]) {
                int t = x;
                x = y;
                y = t;
            }
            parent[y] = x;
            //        if (rank[x] == rank[y])
            //            rank[x]++;
            size[x] += size[y];
            --setCount;
            return true;
        }

        boolean connected(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }
    }
}
