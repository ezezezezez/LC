import java.util.HashMap;
import java.util.Map;

// 2982. Find Longest Special Substring That Occurs Thrice II
public class P2982 {
    public int maximumLength(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int ret = -1;
        for (int i = 0; i < 26; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int cur = 0;
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (cs[j] - 'a' == i) {
                    cur++;
                    max = Math.max(max, cur);
                } else {
                    if (cur > 0) map.merge(cur, 1, Integer::sum);
                    cur = 0;
                }
            }
            if (cur > 0) map.merge(cur, 1, Integer::sum);
            // System.out.println(map);
            if (map.isEmpty()) continue;
            int a = map.get(max);
            if (a >= 3) ret = Math.max(ret, max);
            else if (a == 2 && max - 1 > 0) ret = Math.max(ret, max - 1);
            else if (map.containsKey(max - 1)) ret = Math.max(ret, max - 1);
            else if (max >= 3) ret = Math.max(ret, max - 2);
        }
        return ret;
    }
}
