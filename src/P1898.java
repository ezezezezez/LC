import java.io.*;
import java.lang.*;
import java.util.*;

// 1898. Maximum Number of Removable Characters

public class P1898 {
    public int maximumRemovals(String s, String p, int[] removable) {
        int n = s.length(), m = p.length(), r = removable.length;
        int lo = 0, hi = r;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < mid; i++) {
                set.add(removable[i]);
            }
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (set.contains(i)) {
                    i++;
                    continue;
                }
                char c = s.charAt(i);
                if (c == p.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == m) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
