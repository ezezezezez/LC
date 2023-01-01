import java.io.*;
import java.lang.*;
import java.util.*;

// 2517. Maximum Tastiness of Candy Basket

public class P2515 {
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word =  words[i];
            map.computeIfAbsent(word, k -> new ArrayList<>()).add(i);
        }
        if (!map.containsKey(target)) return -1;
        int diff = Integer.MAX_VALUE;
        for (int idx : map.get(target)) {
            int m = Math.abs(idx - startIndex);
            diff = Math.min(diff, Math.min(m, n - m));
        }
        return diff;
    }
}
