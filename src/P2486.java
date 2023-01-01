import java.io.*;
import java.lang.*;
import java.util.*;

// 2486. Append Characters to String to Make Subsequence

public class P2486 {
    public int appendCharacters(String s, String t) {
        int n = s.length(), m = t.length();
        int j = 0;
        for (int i = 0; i < n && j < m; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(j);
            if (c1 == c2) {
                j++;
            }
        }
        return m - j;
    }
}
