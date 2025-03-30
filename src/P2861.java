import java.util.Collections;
import java.util.List;

// 2861. Maximum Number of Alloys
public class P2861 {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        long lo = 0, hi = Integer.MAX_VALUE, ret = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            boolean valid = false;
            for (List<Integer> comp : composition) {
                long b = budget;
                for (int j = 0; j < comp.size(); j++) {
                    int metal = comp.get(j);
                    long need = mid * metal - stock.get(j);
                    if (need > 0) b -= need * cost.get(j);
                    if (b < 0) break;
                }
                if (b >= 0) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                ret = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
            // System.out.println(lo + " " + hi);
        }
        return (int) (ret == -1 ? 0 : ret);
    }
}
