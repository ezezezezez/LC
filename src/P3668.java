import java.util.HashSet;
import java.util.Set;

// 3668. Restore Finishing Order
public class P3668 {
    public int[] recoverOrder(int[] order, int[] friends) {
        int n = order.length, m = friends.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) set.add(friends[i]);
        int[] ret = new int[m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(order[i])) {
                ret[idx++] = order[i];
            }
        }
        return ret;
    }
}
