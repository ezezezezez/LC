import java.io.*;
import java.lang.*;
import java.util.*;

// 2391. Minimum Amount of Time to Collect Garbage

public class P2391 {
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length, m = travel.length;
        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String g = garbage[i];
            int len = g.length();
            ret += len;
            for (int j = 0; j < len; j++) {
                char c = g.charAt(j);
                map.put(c, i);
            }
        }
        int[] prefix = new int[m + 1];
        for (int i = 0; i < m; i++) {
            prefix[i + 1] = prefix[i] + travel[i];
        }
        for (int idx : map.values()) {
            ret += prefix[idx];
        }
        return ret;
    }
}
