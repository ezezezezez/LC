import java.io.*;
import java.lang.*;
import java.util.*;

// 2351. First Letter to Appear Twice

public class P2351 {
    public char repeatedCharacter(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            if (cnt[c - 'a'] == 2) return c;
        }
        return 0;
    }
}
