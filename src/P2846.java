import java.util.*;

// 2846. Minimum Edge Weight Equilibrium Queries in a Tree
public class P2846 {
    int S = 0;
    int N;
    List<int[]>[] adj;
    int[] parent, dep, size, heavy, head, dfn, rev, value;
    int timer;

    int[][] base;
    int[][] tree;
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        N = n;
        adj = new List[N + 1];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            int[] edge = edges[i];
            int u = edge[0], v = edge[1], w = edge[2];
            adj[u].add(new int[] {v, w, i});
            adj[v].add(new int[] {u, w, i});
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

        base = new int[N][27];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= 26; j++) {
                base[dfn[i]][j] = value[i] == j ? 1 : 0;
            }
        }

        tree = new int[4 * N][27];
        buildTree(0, 0, N - 1);

        int m = queries.length;
        int[] ret = new int[m];
        Arrays.fill(ret, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int u = query[0], v = query[1];
            int[] res = queryPath(u, v);
            int tot = 0;
            for (int cnt : res) tot += cnt;
            for (int cnt : res) ret[i] = Math.min(ret[i], tot - cnt);
        }
        return ret;
    }

    int[] queryPath(int u, int v) {
        int[] ret = new int[27];
        int hu = head[u], hv = head[v];
        while (hu != hv) {
            if (dep[hu] > dep[hv]) {
                int[] res = queryTree(dfn[hu], dfn[u]);
                for (int i = 1 ; i <= 26; i++) ret[i] += res[i];
                u = parent[hu];
                hu = head[u];
            } else {
                int[] res = queryTree(dfn[hv], dfn[v]);
                for (int i = 1 ; i <= 26; i++) ret[i] += res[i];
                v = parent[hv];
                hv = head[v];
            }
        }
        if (dep[u] > dep[v]) {
            int[] res = queryTree(dfn[v] + 1, dfn[u]);
            for (int i = 1 ; i <= 26; i++) ret[i] += res[i];
        } else {
            int[] res = queryTree(dfn[u] + 1, dfn[v]);
            for (int i = 1 ; i <= 26; i++) ret[i] += res[i];
        }
        return ret;
    }

    int[] queryTree(int u, int v) {
        return queryHelper(0, 0, N - 1, u, v);
    }

    int[] queryHelper(int node, int l, int r, int u, int v) {
        if (v < l || u > r) return new int[27];
        if (u <= l && v >= r) {
            return tree[node];
        }
        int mid = l + (r - l) / 2;
        int[] left = queryHelper(2 * node + 1, l, mid, u, v);
        int[] right = queryHelper(2 * node + 2, mid + 1, r, u, v);
        int[] res = new int[27];
        for (int i = 1; i <= 26; i++) res[i] = left[i] + right[i];
        return res;
    }

    void buildTree(int node, int l, int r) {
        if (l == r) {
            tree[node] = base[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildTree(2 * node + 1, l, mid);
        buildTree(2 * node + 2, mid + 1, r);
        for (int i = 1; i <= 26; i++) {
            tree[node][i] = tree[2 * node + 1][i] + tree[2 * node + 2][i];
        }
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
                int w = vv[1], eid = vv[2];
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
