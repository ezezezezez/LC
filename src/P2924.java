import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2924. Find Champion II
public class P2924 {
    public int findChampion(int n, int[][] edges) {
        int m = edges.length;
        Set<Integer> set = new HashSet<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            set.add(v);
        }
        int cnt = 0, ret = -1;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                ret = i;
                cnt++;
            }
        }
        return cnt == 1 ? ret : -1;
    }
}
