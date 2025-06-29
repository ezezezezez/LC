import java.util.*;

// 3598. Longest Common Prefix Between Adjacent Strings After Removals

public class P3598 {
    public int[] longestCommonPrefix(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
        int n = words.length;
        if (n == 1) return new int[1];
        for (int i = 0; i < n - 1; i++) {
            int l = words[i].length(), r = words[i + 1].length();
            int p = 0;
            while (p < l && p < r && words[i].charAt(p) == words[i + 1].charAt(p)) {
                p++;
            }
            map.put(i, p);
            tm.computeIfAbsent(p, kk -> new ArrayList<>()).add(i);
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                int len = map.get(i);
                List<Integer> list = tm.get(len);
                if (list.size() == 1) tm.remove(len);
                ret[i] = tm.isEmpty() ? 0 : tm.lastKey();
                if (list.size() == 1) tm.put(len, list);
            } else if (i == n - 1) {
                int len = map.get(i - 1);
                List<Integer> list = tm.get(len);
                if (list.size() == 1) tm.remove(len);
                ret[i] = tm.isEmpty() ? 0 : tm.lastKey();
                if (list.size() == 1) tm.put(len, list);
            } else {
                int len1 = map.get(i - 1), len2 = map.get(i);
                List<Integer> list1 = tm.get(len1), list2 = tm.get(len2);
                if (list1.size() == 1) tm.remove(len1);
                if (list2.size() == 1) tm.remove(len2);
                if (i - 1 >= 0 && i + 1 < n) {
                    int l = words[i - 1].length(), r = words[i + 1].length();
                    int p = 0;
                    while (p < l && p < r && words[i - 1].charAt(p) == words[i + 1].charAt(p)) {
                        p++;
                    }
                    ret[i] = Math.max(tm.isEmpty() ? 0 : tm.lastKey(), p);
                } else {
                    ret[i] = tm.isEmpty() ? 0 : tm.lastKey();
                }
                if (list1.size() == 1) tm.put(len1, list1);
                if (list2.size() == 1) tm.put(len2, list2);
            }
        }

        return ret;
    }
}
