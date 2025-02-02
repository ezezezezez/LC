import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P2564 {
    public int[][] substringXorQueries(String s, int[][] queries) {
        int n = s.length(), m = queries.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                map.put(0, i);
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') continue;
            for (int j = 1; j <= 31 && i + j <= n; j++) {
                String sub = s.substring(i, i + j);
                int v = Integer.parseInt(sub, 2);
                map.putIfAbsent(v, i);
            }
        }
        int[][] ret = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int first = query[0], second = query[1];
            int t = first ^ second;
            if (!map.containsKey(t)) ret[i][0] = ret[i][1] = -1;
            else {
                int len = 0, step = 0;
                while (step < 31) {
                    if (((t >> step) & 1) > 0) len = step;
                    step++;
                }
                ret[i][0] = map.get(t);
                ret[i][1] = map.get(t) + len;
            }
        }
        return ret;
    }
}
