import java.io.*;
import java.lang.*;
import java.util.*;

// 1482. Minimum Number of Days to Make m Bouquets

public class P1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (m * k > n) return -1;

        int lo = 0, hi = (int)1e9;
        int t = -1;

        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int cnt = 0, cur = 0;
            for (int i = 0; i < n; i++) {
                if (mid >= bloomDay[i]) {
                    cur++;
                    if (cur == k) {
                        cnt++;
                        cur = 0;
                    }
                } else {
                    cur = 0;
                }
            }

            if (cnt >= m) {
                t = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return t;
    }
}
