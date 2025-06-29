import java.util.*;

// 3572. Maximize Y‑Sum by Picking a Triplet of Distinct X‑Values
public class P3572 {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(x[i], y[i], Math::max);
        }

        if (map.size() < 3) return -1;
        List<Integer> vals = new ArrayList<>(map.values());
        Collections.sort(vals);
        int m = vals.size();
        return vals.get(m - 1) + vals.get(m - 2) + vals.get(m - 3);
    }
}
