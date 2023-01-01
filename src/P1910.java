import java.io.*;
import java.lang.*;
import java.util.*;

// 1910. Remove All Occurrences of a Substring

public class P1910 {
    public String removeOccurrences(String s, String part) {
        int m = part.length();
        while (true) {
            int n = s.length();
            boolean hasNext = false;
            for (int i = 0; i <= n - m; i++) {
                boolean found = true;
                for (int j = 0; j < m; j++) {
                    if (s.charAt(i + j) != part.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    s = s.substring(0, i) + s.substring(i + m);
                    hasNext = true;
                    break;
                }
            }
            if (!hasNext) break;
        }
        return s;
    }

    public String removeOccurrences2(String s, String part) {
        int idx = s.indexOf(part);
        while (idx != -1) {
            s = s.substring(0, idx) + s.substring(idx + part.length());
            idx = s.indexOf(part);
        }
        return s;
    }
}
