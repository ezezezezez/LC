import java.io.*;
import java.lang.*;
import java.util.*;

// 2337. Move Pieces to Obtain a String

public class P2337 {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (i < n && start.charAt(i) != 'L' && start.charAt(i) != 'R') {
                i++;
            }
            if (i == n) {
                while (j < n && target.charAt(j) != 'L' && target.charAt(j) != 'R') {
                    j++;
                }
                if (j != n) return false;
            } else if (start.charAt(i) == 'L') {
                while (j < n && target.charAt(j) != 'L' && target.charAt(j) != 'R') {
                    j++;
                }
                if (j == n) return false;
                if (target.charAt(j) == 'R') return false;
                if (j > i) return false;
                j++;
            } else {
                while (j < n && target.charAt(j) != 'L' && target.charAt(j) != 'R') {
                    j++;
                }
                if (j == n) return false;
                if (target.charAt(j) == 'L') return false;
                if (j < i) return false;
                j++;
            }
        }
        while (j < n && target.charAt(j) == '_') j++;
        return j == n;
    }

    public boolean canChange2(String start, String target) {
        if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) return false;
        // System.out.println(start + " " + target);
        for (int i = 0, j = 0; i < start.length(); ++i) {
            if (start.charAt(i) == '_') continue;
            while (target.charAt(j) == '_') ++j;
            if (i != j && (start.charAt(i) == 'L') == (i < j)) return false;
            ++j;
        }
        return true;
    }
}
