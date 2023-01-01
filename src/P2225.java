import java.io.*;
import java.lang.*;
import java.util.*;

// 2225. Find Players With Zero or One Losses

public class P2225 {
    public List<List<Integer>> findWinners(int[][] matches) {
        int n = matches.length;
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> all = new HashSet<>();
        Set<Integer> loses = new HashSet<>();
        for (int[] match : matches) {
            all.add(match[0]);
            all.add(match[1]);
            loses.add(match[1]);
        }
        List<Integer> a = new ArrayList<>();
        for (int player : all) {
            if (!loses.contains(player)) {
                a.add(player);
            }
        }
        ret.add(a);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] match : matches) {
            cnt.merge(match[1], 1, Integer::sum);
        }
        List<Integer> b = new ArrayList<>();
        for (int key : cnt.keySet()) {
            if (cnt.get(key) == 1) {
                b.add(key);
            }
        }
        ret.add(b);
        Collections.sort(a);
        Collections.sort(b);
        return ret;
    }
}
