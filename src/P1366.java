import java.io.*;
import java.lang.*;
import java.util.*;

// 1366. Rank Teams by Votes

public class P1366 {
    public String rankTeams(String[] votes) {
        int n = votes.length;
        int len = votes[0].length();
        List<Integer>[] list = new List[26];
        Arrays.setAll(list, k -> new ArrayList<>());
        for (String vote : votes) {
            for (int i = 0; i < len; i++) {
                char c = vote.charAt(i);
                list[c - 'A'].add(i);
            }
        }
        int sz = list[votes[0].charAt(0) - 'A'].size();
        for (List<Integer> l : list) {
            Collections.sort(l);
            // System.out.println(l);
        }
        Integer[] arr = new Integer[26];
        for (int i = 0; i < 26; i++) arr[i] = i;
        Arrays.sort(arr, (a, b) -> {
            if (list[a].isEmpty()) return 1;
            else if (list[b].isEmpty()) return -1;

            for (int i = 0; i < sz; i++) {
                if (list[a].get(i) != list[b].get(i)) {
                    return Integer.compare(list[a].get(i), list[b].get(i));
                }
            }
            return Integer.compare(a, b);
        });
        // System.out.println(Arrays.toString(arr));
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) ret.append((char)(arr[i] + 'A'));
        return ret.toString();
    }
}
