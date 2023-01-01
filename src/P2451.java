import java.io.*;
import java.lang.*;
import java.util.*;

// 2455. Average Value of Even Numbers That Are Divisible by Three

public class P2451 {
    public String oddString(String[] words) {
        int n = words.length;
        int m = words[0].length();
        String[] diff = new String[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j < m; j++) {
                list.add(word.charAt(j) - word.charAt(j - 1));
            }
            diff[i] = list.toString();
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(diff[i], 1, Integer::sum);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(diff[i]) == 1) return words[i];
        }
        return "";
    }
}
