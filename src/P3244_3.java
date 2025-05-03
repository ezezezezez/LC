import java.util.HashMap;
import java.util.Map;

// 3244. Shortest Distance After Road Addition Queries II
public class P3244_3 {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ret = new int[m];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            map.put(i, i + 1);
        }
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int l = query[0], r = query[1];
            if (map.containsKey(l) && map.get(l) < r) {
                int p = l;
                while (p != r) {
                    p = map.remove(p);
                }
                map.put(l, r);
            }
            ret[i] = map.size();
        }
        return ret;
    }
}
