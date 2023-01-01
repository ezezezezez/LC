import java.util.*;
import java.io.*;
import java.lang.*;

// 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

public class P1347 {
    public int minSteps(String s, String t) {
        int[] sCnt = new int[26], tCnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sCnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            tCnt[t.charAt(i) - 'a']++;
        }
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            diff += Math.abs(sCnt[i] - tCnt[i]);
        }

        return diff / 2;
    }
}
