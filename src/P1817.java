import java.io.*;
import java.lang.*;
import java.util.*;

// 1817. Finding the Users Active Minutes

public class P1817 {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int[] ret = new int[k];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            int user = log[0], time = log[1];
            map.computeIfAbsent(user, idx -> new HashSet<>()).add(time);
        }
        for (int key : map.keySet()) {
            Set<Integer> set = map.get(key);
            ret[set.size() - 1]++;
        }
        return ret;
    }
}
