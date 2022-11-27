import java.util.*;
import java.io.*;
import java.lang.*;

// 1208. Get Equal Substrings Within Budget

public class P1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int lo = 0, hi = n;
        int temp = -1;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (valid(s, t, mid, maxCost)) {
                temp = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return temp == -1 ? 0 : temp;
    }

    boolean valid(String s, String t, int len, int cost) {
        int n = s.length();
        int c = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(s.charAt(i) - t.charAt(i));
            c += diff;
            if (i >= len) {
                int diff2 = Math.abs(s.charAt(i - len) - t.charAt(i - len));
                c -= diff2;
            }
            if (i >= len - 1 && c <= cost) return true;
        }
        return false;
    }
}
