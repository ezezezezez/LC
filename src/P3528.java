import java.util.*;

// 3528. Unit Conversion I
public class P3528 {
    int mod = (int) Math.round(1e9 + 7);
    int[] ret;
    public int[] baseUnitConversions(int[][] conversions) {
        int m = conversions.length, n = m + 1;
        ret = new int[n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] conv : conversions) {
            int s = conv[0], t = conv[1], f = conv[2];
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(new int[] {t, f});
        }
        dfs(0, map, -1, 1L);
        return ret;
    }

    void dfs(int cur, Map<Integer, List<int[]>> map, int prev, long v) {
        ret[cur] = (int) v;
        if (!map.containsKey(cur)) return;
        for (int[] pair : map.get(cur)) {
            int t = pair[0], f = pair[1];
            if (t == prev) continue;
            dfs(t, map, cur, v * f % mod);
        }
    }
}
