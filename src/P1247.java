import java.util.*;
import java.io.*;
import java.lang.*;

// 1247. Minimum Swaps to Make Strings Equal

public class P1247 {
    // O(n^2)
    public int minimumSwap(String s1, String s2) {
        int n = s1.length();
        boolean[] swapped = new boolean[n];
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();

        int step = 0;
        for (int i = 0; i < n; i++) {
            if (cs1[i] == cs2[i]) continue;
            boolean found = false;
            int idx = -1;
            for (int j = i + 1; j < n; j++) {
                if (cs1[j] != cs2[i]) idx = j;
                if (cs1[j] != cs2[i] && cs1[j] != cs2[j]) {
                    found = true;
                    char t = cs1[j];
                    cs1[j] = cs2[i];
                    cs2[i] = t;
                    break;
                }
            }

            if (!found) {
                if (idx != -1) {
                    char t = cs1[idx];
                    cs1[idx] = cs2[i];
                    cs2[i] = t;
                } else {
                    if (swapped[i]) return -1;
                    char t = cs1[i];
                    cs1[i] = cs2[i];
                    cs2[i] = t;
                    swapped[i] = true;
                    i--;
                }
            }
            step++;
        }
        return step;
    }

    // O(n)
    public int minimumSwap2(String s1, String s2) {
        int n = s1.length();
        int xyCnt = 0, yxCnt = 0;

        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 == 'x' && c2 == 'y') xyCnt++;
            else if (c1 == 'y' && c2 == 'x') yxCnt++;
        }

        if ((xyCnt + yxCnt) % 2 == 1) return -1;

        if (xyCnt % 2 == 0) {
            return xyCnt / 2 + yxCnt / 2;
        } else {
            return xyCnt / 2 + yxCnt / 2 + 2;
        }
    }
}
