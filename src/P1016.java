import java.util.*;
import java.io.*;
import java.lang.*;

// 1016. Binary String With Substrings Representing 1 To N

public class P1016 {
    public boolean queryString(String s, int n) {
        int len = s.length();
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= Math.min(len, 31); i++) {
            for (int p = 0; p + i <= len; p++) {
                String ns = s.substring(p, p + i);
                set.add(Integer.parseInt(ns, 2));
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) return false;
        }

        return true;
    }
}
