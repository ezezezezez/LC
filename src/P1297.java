import java.util.*;
import java.io.*;
import java.lang.*;

// 1297. Maximum Number of Occurrences of a Substring

public class P1297 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();

        int ret = 0;
        for (int i = minSize; i <= maxSize; i++) {
            Map<String, Integer> cnt = new HashMap<>();
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < i - 1; j++) map.merge(s.charAt(j), 1, Integer::sum);
            for (int j = i - 1; j < n; j++) {
                map.merge(s.charAt(j), 1, Integer::sum);
                int l = j - i + 1;
                if (j >= i) {
                    map.merge(s.charAt(l - 1), -1, Integer::sum);
                    if (map.get(s.charAt(l - 1)) == 0) map.remove(s.charAt(l - 1));
                }
                if (map.size() <= maxLetters) {
                    String sub = s.substring(l, l + i);
                    cnt.merge(sub, 1, Integer::sum);
                }
            }
            for (String key : cnt.keySet()) {
                ret = Math.max(ret, cnt.get(key));
            }
        }

        return ret;
    }

    public int maxFreq2(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();

        int ret = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n - minSize + 1; i++) {
            int j = i + minSize - 1;
            Set<Character> set = new HashSet<>();
            for (int k = i; k <= j; k++) {
                set.add(s.charAt(k));
            }
            if (set.size() <= maxLetters) {
                map.merge(s.substring(i, j + 1), 1, Integer::sum);
            }
        }

        for (String key : map.keySet()) {
            ret = Math.max(ret, map.get(key));
        }

        return ret;
    }
}
