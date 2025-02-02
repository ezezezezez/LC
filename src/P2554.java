import java.util.HashSet;
import java.util.Set;

public class P2554 {
    public int maxCount(int[] banned, int n, int maxSum) {
        int ret = 0, sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int ban : banned) set.add(ban);
        for (int i = 1; i <= n; i++) {
            if (sum + i <= maxSum && !set.contains(i)) {
                ret++;
                sum += i;
            }
        }
        return ret;
    }
}
