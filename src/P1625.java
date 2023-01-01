import java.io.*;
import java.lang.*;
import java.util.*;

// 1625. Lexicographically Smallest String After Applying Operations

public class P1625 {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String ret = s;
        Deque<String> dq = new ArrayDeque<>();
        dq.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        while (!dq.isEmpty()) {
            String cur = dq.poll();
            String nxt1 = cur.substring(n - b) + cur.substring(0, n - b);
            StringBuilder nxt2SB = new StringBuilder(cur);
            for (int j = 1; j < n; j += 2) {
                nxt2SB.setCharAt(j, (char)((cur.charAt(j) - '0' + a) % 10 + '0'));
            }
            String nxt2 = nxt2SB.toString();
            if (!vis.contains(nxt1)) {
                vis.add(nxt1);
                if (nxt1.compareTo(ret) < 0) ret = nxt1;
                dq.offer(nxt1);
            }
            if (!vis.contains(nxt2)) {
                vis.add(nxt2);
                if (nxt2.compareTo(ret) < 0) ret = nxt2;
                dq.offer(nxt2);
            }
        }
        return ret;
    }
}
