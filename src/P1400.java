import java.io.*;
import java.lang.*;
import java.util.*;

// 1400. Construct K Palindrome Strings

public class P1400 {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        int see = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) see++;
        }

        return see <= k;
    }
}
