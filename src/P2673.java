// 2673. Make Costs of Paths Equal in a Binary Tree
public class P2673 {
    int[] mx;
    int ret;
    int mxValue;
    public int minIncrements(int n, int[] cost) {
        mx = new int[n];
        dfs(1, n, cost);
        mxValue = mx[0];
        dfs2(1, n, cost, 0);
        return ret;
    }

    int dfs(int cur, int n, int[] cost) {
        if (cur > n) return 0;
        int l = dfs(cur * 2, n, cost);
        int r = dfs(cur * 2 + 1, n, cost);
        mx[cur - 1] = Math.max(l, r) + cost[cur - 1];
        return mx[cur - 1];
    }

    void dfs2(int cur, int n, int[] cost, int pre) {
        if (cur > n) return;
        int add = mxValue - mx[cur - 1] - pre;
        ret += add;
        pre += add;
        dfs2(cur * 2, n, cost, pre + cost[cur - 1]);
        dfs2(cur * 2 + 1, n, cost, pre + cost[cur - 1]);
        return;
    }
}
