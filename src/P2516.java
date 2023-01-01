import java.io.*;
import java.lang.*;
import java.util.*;

// 2516. Take K of Each Character From Left and Right

public class P2516 {
    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;
        int n = s.length();
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) return -1;
        int lo = 0, hi = n - 1;
        int t = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int rem = n - mid;
            int[] arr = new int[3];
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                arr[c - 'a']++;
                if (i >= mid) {
                    arr[s.charAt(i - mid) - 'a']--;
                }
                if (i >= mid - 1) {
                    if (cnt[0] - arr[0] >= k && cnt[1] - arr[1] >= k && cnt[2] - arr[2] >= k) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                lo = mid + 1;
                t = rem;
            } else {
                hi = mid - 1;
            }
        }
        return t;
    }
}
