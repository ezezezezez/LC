import java.io.*;
import java.lang.*;
import java.util.*;

// 2358. Maximum Number of Groups Entering a Competition

public class P2358 {
    public int maximumGroups(int[] grades) {
        int n = grades.length;
        Arrays.sort(grades);
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && j - i + 1 <= ret) {
                j++;
            }
            if (j < n) ret = j - i + 1;
            else return ret;
            i = j;
        }
        return ret;
    }

    public int maximumGroups2(int[] grades) {
        int n = grades.length;
        int lo = 1, hi = n;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            long sum = (1L + mid) * mid / 2;
            if (sum <= n) {
                t = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
