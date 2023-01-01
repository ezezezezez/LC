import java.io.*;
import java.lang.*;
import java.util.*;

// 1759. Count Number of Homogenous Substrings

public class P1759 {
    public int countHomogenous(String s) {
        int n = s.length();
        int ret = 0;
        int mod = (int)(1e9 + 7);
        for (int i = 0, j = 0; i < n; i++) {
            while (i + 1 < n && s.charAt(i + 1) == s.charAt(i)) i++;
            long m = i - j + 1;
            ret = (int)((ret + m * (m + 1) / 2) % mod);
            j = i + 1;
        }
        return ret;
    }
}
