import java.io.*;
import java.lang.*;
import java.util.*;

// 2399. Check Distances Between Same Letters

public class P2399 {
    public boolean checkDistances(String s, int[] distance) {
        int n = s.length();
        List<Integer>[] list = new List[26];
        Arrays.setAll(list, k -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            list[c - 'a'].add(i);
        }
        for (int i = 0; i < 26; i++) {
            if (list[i].isEmpty()) continue;
            if (list[i].get(1) - list[i].get(0) - 1 != distance[i]) return false;
        }
        return true;
    }
}
