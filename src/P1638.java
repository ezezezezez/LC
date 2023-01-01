import java.io.*;
import java.lang.*;
import java.util.*;

// 1638. Count Substrings That Differ by One Character

public class P1638 {
    public int countSubstrings(String s, String t) {
        int n = s.length(), m = t.length();
        Map<Integer, Map<String, Integer>> mapS = new HashMap<>();
        Map<Integer, Map<String, Integer>> mapT = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substr = s.substring(i, j + 1);
                mapS.computeIfAbsent(substr.length(), k -> new HashMap<>()).merge(substr, 1, Integer::sum);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                String substr = t.substring(i, j + 1);
                mapT.computeIfAbsent(substr.length(), k -> new HashMap<>()).merge(substr, 1, Integer::sum);
            }
        }
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            Map<String, Integer> substrMapS = mapS.get(i);
            Map<String, Integer> substrMapT = mapT.get(i);
            for (String subS : substrMapS.keySet()) {
                for (String subT : substrMapT.keySet()) {
                    int diff = 0;
                    for (int j = 0; j < i; j++) {
                        if (subS.charAt(j) != subT.charAt(j)) {
                            diff++;
                            if (diff > 1) break;
                        }
                    }
                    if (diff == 1) {
                        ret += substrMapS.get(subS) * substrMapT.get(subT);
                    }
                }
            }
        }
        return ret;
    }

    public int countSubstrings2(String s, String t) {
        int n = s.length(), m = t.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int diff = 0;
                for (int k = 0; i + k < n && j + k < m; k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) {
                        diff++;
                    }
                    if (diff > 1) break;
                    if (diff == 1) ret++;
                }
            }
        }
        return ret;
    }

    public int countSubstrings3(String s, String t) {
        int n = s.length(), m = t.length();
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret += c(s, t, i, 0);
        }
        for (int i = 1; i < m; i++) {
            ret += c(s, t, 0, i);
        }
        return ret;
    }

    int c(String s, String t, int i, int j) {
        int same = 0, diff = 0, ret = 0;
        int n = s.length(), m = t.length();
        while (i < n && j < m) {
            if (s.charAt(i++) == t.charAt(j++)) {
                same++;
            } else {
                diff = same + 1;
                same = 0;
            }
            ret += diff;
        }
        return ret;
    }
}
