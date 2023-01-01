import java.io.*;
import java.lang.*;
import java.util.*;

// 2278. Percentage of Letter in String

public class P2278 {
    public int percentageLetter(String s, char letter) {
        int n = s.length();
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) cnt++;
        }
        return (int)(1.0 * cnt / n * 100);
    }
}
