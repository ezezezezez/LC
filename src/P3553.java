import java.util.*;

// 3553. Minimum Weighted Subgraph With the Required Paths II
public class P3553 {
    int S = 0;
    int N;
    List<int[]>[] adj;
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        N = edges.length + 1;
        int m = queries.length;
        adj = new List[N];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[] {v, w});
            adj[v].add(new int[] {u, w});
        }

        parent = new int[N];
        dep = new int[N];
        size = new int[N];
        heavy = new int[N];
        head = new int[N];
        dfn = new int[N];
        rev = new int[N];
        value = new int[N];
        Arrays.fill(heavy, -1);

        prepareHLD(S);

        base = new long[N];
        for (int i = 0; i < N; i++) {
            base[dfn[i]] = value[i];
        }

        tree = new long[4 * N];
        buildTree(0, 0, N - 1);

        int[] ret = new int[m];
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1], z = queries[i][2];
            ret[i] = (int) (queryPath(x, y) + queryPath(x, z) + queryPath(y, z)) / 2;
            // int lca = lca(x, y);
            // lca = lca(lca, z);
            // int minDep = Math.min(dep[head[x]], Math.min(dep[head[y]], dep[head[z]]));

        }
        return ret;
    }

    int[] parent, dep, size, heavy, head, dfn, rev, value;
    int timer;

    long[] base;
    long[] tree;

    long queryPath(int u, int v) {
        long ret = 0;
        int hu = head[u], hv = head[v];
        while (hu != hv) {
            if (dep[hu] > dep[hv]) {
                ret += queryTree(dfn[hu], dfn[u]);
                u = parent[hu];
                hu = head[u];
            } else {
                ret += queryTree(dfn[hv], dfn[v]);
                v = parent[hv];
                hv = head[v];
            }
        }
        if (dep[u] > dep[v]) {
            ret += queryTree(dfn[v] + 1, dfn[u]);
        } else {
            ret += queryTree(dfn[u] + 1, dfn[v]);
        }
        return ret;
    }

    long queryTree(int u, int v) {
        return queryHelper(0, 0, N - 1, u, v);
    }

    long queryHelper(int node, int l, int r, int u, int v) {
        if (v < l || u > r) return 0;
        if (u <= l && v >= r) {
            return tree[node];
        }
        int mid = l + (r - l) / 2;
        long left = queryHelper(2 * node + 1, l, mid, u, v);
        long right = queryHelper(2 * node + 2, mid + 1, r, u, v);
        return left + right;
    }

    int lca(int u, int v) {
        int hu = head[u], hv = head[v];
        while (hu != hv) {
            if (dep[hu] > dep[hv]) {
                u = parent[hu];
                hu = head[u];
            } else {
                v = parent[hv];
                hv = head[v];
            }
        }
        return dep[u] > dep[v] ? v : u;
    }

    void buildTree(int node, int l, int r) {
        if (l == r) {
            tree[node] = base[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildTree(2 * node + 1, l, mid);
        buildTree(2 * node + 2, mid + 1, r);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    void prepareHLD(int root) {
        parent[root] = 0;
        dep[root] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(root);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int[] vv : adj[u]) {
                int v = vv[0];
                if (v == parent[u]) continue;
                int w = vv[1];
                parent[v] = u;
                dep[v] = dep[u] + 1;
                value[v] = w;
                q.offer(v);
            }
        }
        for (int i = order.size() - 1; i >= 0; i--) {
            int u = order.get(i);
            size[u] = 1;
            int maxSize = 0;
            for (int[] vv : adj[u]) {
                int v = vv[0];
                if (v == parent[u]) continue;
                size[u] += size[v];
                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        timer = 0;
        Deque<Integer> st = new ArrayDeque<>();
        head[root] = root;
        st.push(root);
        while (!st.isEmpty()) {
            int u = st.pop();
            int h = head[u];
            for (int cur = u; cur != -1; cur = heavy[cur]) {
                head[cur] = h;
                dfn[cur] = timer;
                rev[timer++] = cur;
                for (int[] vv : adj[cur]) {
                    int v = vv[0];
                    if (v != parent[cur] && v != heavy[cur]) {
                        head[v] = v;
                        st.push(v);
                    }
                }
            }
        }
    }
}
