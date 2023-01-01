import java.io.*;
import java.lang.*;
import java.util.*;

// 1583. Count Unhappy Friends

public class P1583 {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] last = new int[n];
        Map<Integer, Integer>[] prefs = new Map[n];
        Arrays.setAll(prefs, k -> new HashMap<>());
        int[] dislikes = new int[n];
        for (int[] pair : pairs) {
            int a = pair[0], b = pair[1];
            dislikes[a] = b;
            dislikes[b] = a;
        }
        for (int i = 0; i < n; i++) {
            int[] pref = preferences[i];
            for (int j = 0; j < pref.length; j++) {
                prefs[i].put(pref[j], j);
            }
        }
        int ret = 0;
        for (int[] pair : pairs) {
            int a = pair[0], b = pair[1];
            boolean found = false;
            for (int i = 0; i < preferences[a].length && preferences[a][i] != b; i++) {
                int c = preferences[a][i];
                int cDislike = dislikes[c];
                if (prefs[c].get(a) < prefs[c].get(cDislike)) {
                    found = true;
                    break;
                }
            }
            if (found) ret++;
            found = false;
            for (int i = 0; i < preferences[b].length && preferences[b][i] != a; i++) {
                int c = preferences[b][i];
                int cDislike = dislikes[c];
                if (prefs[c].get(b) < prefs[c].get(cDislike)) {
                    found = true;
                    break;
                }
            }
            if (found) ret++;
        }
        return ret;
    }
}
