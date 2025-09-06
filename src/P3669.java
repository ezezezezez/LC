import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3669. Balanced K-Factor Decomposition
public class P3669 {
    public int[] minDifference(int n, int k) {
        this.k = k;
        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                divisors.add(d);
                if (d != n / d) divisors.add(n / d);
            }
        }
        Collections.sort(divisors);
        dfs(divisors, 0, 0, n, new ArrayList<>());
        return ret;
    }

    int k;
    int minDiff = Integer.MAX_VALUE;
    int[] ret;

    void dfs(List<Integer> divisors, int idx, int cnt, int rem, List<Integer> res) {
        if (cnt == k) {
            if (rem == 1) {
                int mn = res.get(0);
                int mx = res.get(res.size() - 1);
                if (mx - mn < minDiff) {
                    minDiff = mx - mn;
                    ret = new int[k];
                    for (int i = 0; i < k; i++) ret[i] = res.get(i);
                }
            }
            return;
        }
        for (int i = idx; i < divisors.size(); i++) {
            int d = divisors.get(i);
            if (rem % d != 0) continue;
            res.add(d);
            dfs(divisors, i, cnt + 1, rem / d, res);
            res.remove(res.size() - 1);
        }
    }
}
