import java.io.*;
import java.lang.*;
import java.util.*;

// 1552. Magnetic Force Between Two Balls

public class P1552 {
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int lo = 1, hi = (int)1e9;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (valid(position, m, mid)) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }

    boolean valid(int[] position, int m, int dist) {
        int n = position.length;
        int pre = (int)(-1e9 - 1);
        for (int i = 0; i < n; i++) {
            if (position[i] - pre < dist) continue;
            m--;
            if (m == 0) return true;
            pre = position[i];
        }
        return false;
    }
}
