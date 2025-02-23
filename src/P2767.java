import java.util.*;

// 2767. Partition String Into Minimum Beautiful Substrings
public class P2767 {
    int n;
    int ret = Integer.MAX_VALUE;
    public int minimumBeautifulSubstrings(String s) {
        n = s.length();
        dfs(s, 0, new ArrayList<>());
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    void dfs(String s, int cur, List<String> list) {
        if (cur == n) {
            for (String str : list) {
                int v = Integer.parseInt(str, 2);
                if (!isPowerOf(v, 5)) return;
            }
            ret = Math.min(ret, list.size());
            return;
        }
        if (s.charAt(cur) == '0') return;
        for (int i = cur; i < n; i++) {
            String str = s.substring(cur, i + 1);
            list.add(str);
            dfs(s, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    boolean isPowerOf(int v, int p) {
        while (v > 1) {
            if (v % p != 0) return false;
            v /= p;
        }
        return v == 1;
    }
}
