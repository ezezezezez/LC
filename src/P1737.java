import java.io.*;
import java.lang.*;
import java.util.*;

// 1737. Change Minimum Characters to Satisfy One of Three Conditions

public class P1737 {
    public int minCharacters(String a, String b) {
        int n = a.length(), m = b.length();
        int[] cnt1 = new int[26], cnt2 = new int[26];
        int lowA = Integer.MAX_VALUE, lowB = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            cnt1[a.charAt(i) - 'a']++;
            lowA = Math.min(lowA, a.charAt(i));
        }
        for (int i = 0; i < m; i++) {
            cnt2[b.charAt(i) - 'a']++;
            lowB = Math.min(lowB, b.charAt(i));
        }
        int most1 = 0, most2 = 0;
        for (int cnt : cnt1) most1 = Math.max(most1, cnt);
        for (int cnt : cnt2) most2 = Math.max(most2, cnt);
        int ret = n - most1 + m - most2;
        char[] ca = a.toCharArray(), cb = b.toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);
        // System.out.println(Arrays.toString(ca));
        // System.out.println(Arrays.toString(cb));
        // System.out.println(ret);
        // System.out.println(calc(ca, cb));
        // System.out.println();
        // System.out.println(calc(cb, ca));
        ret = Math.min(ret, Math.min(calc(ca, cb), calc(cb, ca)));
        return ret;
    }

    int calc(char[] ca, char[] cb) {
        int n = ca.length, m = cb.length;
        int i = m - 1;
        int ret = Integer.MAX_VALUE;
        int j = 0;
        while (j < n && ca[j] == 'a') j++;
        while (j < n && i >= 0 && cb[i] >= ca[j]) i--;
        i++;
        while (i < m && j < n && ca[j] <= cb[i]) {
            ret = Math.min(ret, m - i + j);
            j++;
            while (i < m && j < n && cb[i] < ca[j]) i++;
            // System.out.println(j + " " + i);
        }
        if (j == n) {
            if (i == m) i--;
            while (i < m && cb[i] < 'z') i++;
        }
        ret = Math.min(ret, m - i + j);
        return ret;
    }

    public int minCharacters2(String a, String b) {
        int n = a.length(), m = b.length();
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (int i = 0; i < n; i++) cnt1[a.charAt(i) - 'a']++;
        for (int i = 0; i < m; i++) cnt2[b.charAt(i) - 'a']++;
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            ret = Math.min(ret, n + m - cnt1[i] - cnt2[i]);
        }
        for (int i = 0, ca = 0, cb = 0; i < 25; i++) {
            ca += cnt1[i];
            cb += cnt2[i];
            ret = Math.min(ret, ca + m - cb);
            ret = Math.min(ret, cb + n - ca);
        }
        return ret;
    }
}
