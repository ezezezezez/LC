import java.io.*;
import java.lang.*;
import java.util.*;

// 1540. Can Convert String in K Moves

public class P1540 {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) return false;
        int n = s.length();
        int[] diff = new int[26];
        for (int i = 0; i < n; i++) {
            diff[Math.floorMod(t.charAt(i) - s.charAt(i), 26)]++;
        }
        int[] have = new int[26];
        Arrays.setAll(have, idx -> k / 26);
        int rem = k % 26;
        for (int i = 1; i <= rem; i++) have[i]++;
        // System.out.println(Arrays.toString(diff));
        // System.out.println(Arrays.toString(have));
        for (int i = 1; i < 26; i++) {
            if (have[i] < diff[i]) return false;
        }
        return true;
    }
}
