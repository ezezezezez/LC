import java.io.*;
import java.lang.*;
import java.util.*;

// 2279. Maximum Bags With Full Capacity of Rocks

public class P2279 {
    public int maximumBags(int[] capacity, int[] rocks, int ar) {
        int n = capacity.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, k -> k);
        Arrays.sort(ids, (a, b) -> Integer.compare(capacity[a] - rocks[a], capacity[b] - rocks[b]));
        int ret = 0;
        for (int i = 0; i < n && ar > 0; i++) {
            if (ar >= capacity[ids[i]] - rocks[ids[i]]) {
                ret++;
                ar -= capacity[ids[i]] - rocks[ids[i]];
            } else break;
        }
        return ret;
    }
}
