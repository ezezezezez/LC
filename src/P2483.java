import java.io.*;
import java.lang.*;
import java.util.*;

// 2483. Minimum Penalty for a Shop

public class P2483 {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        char[] cs = customers.toCharArray();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + (cs[i] == 'Y' ? 1 : 0);
        int pe = n - prefix[n];
        int idx = n;
        for (int i = 0; i < n; i++) {
            int t = i - prefix[i] + prefix[n] - prefix[i];
            // System.out.println(i + " " + t);
            if (t < pe) {
                idx = i;
                pe = t;
            } else if (t == pe) {
                idx = Math.min(idx, i);
            }
        }
        return idx;
    }
}
