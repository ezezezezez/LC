import java.io.*;
import java.lang.*;
import java.util.*;

// 2315. Count Asterisks

public class P2315 {
    public int countAsterisks(String s) {
        int n = s.length();
        int ret = 0, status = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') status = (status + 1) % 2;
            else if (status == 0 && c == '*') {
                ret++;
            }
        }
        return ret;
    }
}
