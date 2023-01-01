import java.io.*;
import java.lang.*;
import java.util.*;

// 1593. Split a String Into the Max Number of Unique Substrings

public class P1593 {
    int ret;
    int n;
    public int maxUniqueSplit(String s) {
        n = s.length();
        dfs(s, 0, new HashSet<>());
        return ret;
    }

    void dfs(String s, int idx, Set<String> set) {
        if (idx == n) {
            ret = Math.max(ret, set.size());
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < n; i++) {
            sb.append(s.charAt(i));
            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                dfs(s, i + 1, set);
                set.remove(sb.toString());
            }
        }
    }
}
