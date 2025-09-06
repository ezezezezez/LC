import java.util.List;

// 3376. Minimum Time to Break Locks I

public class P3376 {
    public int findMinimumTime(List<Integer> strength, int k) {
        this.n = strength.size();
        dfs(strength, 0, 1, k, 0);
        return ret;
    }

    int n;
    int ret = Integer.MAX_VALUE;

    void dfs(List<Integer> strength, int mask, int x, int k, int time) {
        if (mask == ((1 << n) - 1)) {
            ret = Math.min(ret, time);
            return;
        }
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                int s = strength.get(i);
                dfs(strength, mask | (1 << i), x + k, k, time + (s + x - 1) / x);
            }
        }
    }
}
