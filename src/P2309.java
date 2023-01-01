import java.io.*;
import java.lang.*;
import java.util.*;

// 2309. Greatest English Letter in Upper and Lower Case

public class P2309 {
    public String greatestLetter(String s) {
        int n = s.length();
        String ret = "";
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') cnt1[c - 'a']++;
            if (c >= 'A' && c <= 'Z') cnt2[c - 'A']++;
        }
        for (int i = 25; i >= 0; i--) {
            if (cnt1[i] > 0 && cnt2[i] > 0) return "" + (char)('A' + i);
        }
        return ret;
    }
}
