import java.io.*;
import java.lang.*;
import java.util.*;

// 1781. Sum of Beauty of All Substrings

public class P1781 {
    public int beautySum(String s) {
        int n = s.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                int mx = 0, mn = Integer.MAX_VALUE;
                for (int c : cnt) {
                    if (c > 0) {
                        mx = Math.max(mx, c);
                        mn = Math.min(mn, c);
                    }

                }
                ret += mx - mn;
            }
        }
        return ret;
    }
}
