import java.util.ArrayList;
import java.util.List;

// 3644. Maximum K to Sort a Permutation
public class P3644_2 {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];
        int k = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == i) continue;
            if (vis[i]) continue;
            int cur = i;
            List<Integer> cycle = new ArrayList<>();
            while (!vis[cur]) {
                vis[cur] = true;
                cycle.add(cur);
                cur = nums[cur];
            }
            int innerK = -1;
            for (int v : cycle) {
                innerK &= v;
            }
            k &= innerK;
        }
        return k == -1 ? 0 : k;
    }
}
