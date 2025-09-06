import java.util.*;

// 3590. Kth Smallest Path XOR Sum

public class P3590 {
    int K = 17;
    int BIT_SIZE = 1 << K;
    static class BIT1 {
        long[] tree;
        int n;
        int MX_IDX;

        BIT1(int n) {
            this.n = n;
            tree = new long[n + 1];
            while ((1 << MX_IDX) <= n) MX_IDX++;
        }

        // x: 1-based
        void update(int index, long delta) {
            if (index <= 0 || index > n)
                throw new IllegalArgumentException(String.format("index(%s) should be in [1, n(%s)]", index, n));
            for (int i = index; i <= n; i += lowbit(i)) {
                tree[i] += delta;
            }
        }

        // sum of val[1...r]
        private long query(int r) {
            if (r < 0 || r > n) throw new IllegalArgumentException(String.format("r(%s) should be in [0, n(%s)]", r, n));
            long ret = 0;
            for (int i = r; i > 0; i -= lowbit(i)) {
                ret += tree[i];
            }
            return ret;
        }

        // l, r: 1-based
        long query(int l, int r) {
            if (r > n || l <= 0 || l > r) {
                throw new IllegalArgumentException(String.format("l(%s), r(%s) should be in [1, n(%s)] and l should <= r", l, r, n));
            }
            return query(r) - query(l - 1);
        }

        // return: 0: BIT is empty, others: kth value
        int findKth(int k) {
            int pos = 0;
            long cnt = 0;
            for (int i = MX_IDX; i >= 0; i--) {
                int bit = 1 << i;
                int nxtPos = pos + bit;
                if (nxtPos <= n && cnt + tree[nxtPos] < k) {
                    pos = nxtPos;
                    cnt += tree[nxtPos];
                }
            }
            return pos == n ? 0 : (pos + 1);
        }

        int lowbit(int x) {
            return x & -x;
        }
    }

    int n;
    List<Integer>[] adj;
    int[] sz, heavy;
    int[] tin, tout, euler;
    int[] pathXor, vals, cnt;
    int timer;
    BIT1 bit;
    int[] ans;
    Map<Integer, List<int[]>> map = new HashMap<>();

    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        n = par.length;
        this.vals = vals;
        adj = new List[n];
        Arrays.setAll(adj, kk -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            int u = i, v = par[i];
            adj[u].add(v);
            adj[v].add(u);
        }
        sz = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        tin = new int[n];
        tout = new int[n];
        euler = new int[n + 1];
        pathXor = new int[n];
        pathXor[0] = vals[0];
        cnt = new int[BIT_SIZE];
        bit = new BIT1(BIT_SIZE);

        int m = queries.length;
        ans = new int[m];

        for (int i = 0; i < m; i++) {
            int u = queries[i][0], k = queries[i][1];
            map.computeIfAbsent(u, kk -> new ArrayList<>()).add(new int[] {i, k});
        }

        dfs1(0, -1);
        // System.out.println(Arrays.toString(heavy));
        // System.out.println(Arrays.toString(pathXor));
        dfs2(0, -1, false);

        return ans;
    }

    void dfs1(int u, int pre) {
        tin[u] = ++timer;
        euler[timer] = u;
        sz[u] = 1;
        int maxSz = 0;
        for (int v : adj[u]) {
            if (v == pre) continue;
            pathXor[v] = pathXor[u] ^ vals[v];
            dfs1(v, u);
            sz[u] += sz[v];
            if (heavy[u] == -1 || sz[v] > maxSz) {
                maxSz = sz[v];
                heavy[u] = v;
            }
        }
        tout[u] = timer;
    }

    void dfs2(int u, int pre, boolean keep) {
        for (int v : adj[u]) {
            if (v == pre || heavy[u] == v) continue;
            dfs2(v, u, false);
        }
        if (heavy[u] != -1) {
            dfs2(heavy[u], u, true);
        }
        for (int v : adj[u]) {
            if (v == pre || heavy[u] == v) continue;
            for (int p = tin[v]; p <= tout[v]; p++) {
                add(euler[p]);
            }
        }
        add(u);
        if (map.containsKey(u)) {
            // System.out.println("check: " + bit.query(1, BIT_SIZE));
            for (int[] pair : map.get(u)) {
                int queryIdx = pair[0], k = pair[1];
                int kth = bit.findKth(k);
                ans[queryIdx] = kth == 0 ? -1 : kth - 1;
            }
        }
        if (!keep) {
            // System.out.println("not keep: " + u + " " + tin[u] + " " + tout[u]);
            for (int p = tin[u]; p <= tout[u]; p++) {
                del(euler[p]);
            }
        }
    }

    void add(int node) {
        // System.out.println("adding node: " + node);
        if (cnt[pathXor[node]] == 0) bit.update(pathXor[node] + 1, 1);
        cnt[pathXor[node]]++;
    }

    void del(int node) {
        // System.out.println("deleting node: " + node);
        if (cnt[pathXor[node]] == 1) bit.update(pathXor[node] + 1, -1);
        cnt[pathXor[node]]--;
    }
}
