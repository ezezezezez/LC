import java.io.*;
import java.lang.*;
import java.util.*;

// 2381. Shifting Letters II

public class P2381 {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] f = new int[n];
        for (int[] shift : shifts) {
            int a = shift[0], b = shift[1], dir = shift[2];
            if (dir == 1) {
                f[a]++;
                if (b + 1 < n) f[b + 1]--;
            } else {
                f[a]--;
                if (b + 1 < n) f[b + 1]++;
            }
        }
        // System.out.println(Arrays.toString(f));
        StringBuilder sb = new StringBuilder(s);
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur = Math.floorMod(cur + f[i], 26);
            int c = sb.charAt(i) - 'a';
            char nc = (char)(((c + cur) % 26) + 'a');
            sb.setCharAt(i, nc);
        }
        return sb.toString();
    }
}
