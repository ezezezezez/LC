import java.io.*;
import java.lang.*;
import java.util.*;

// 1616. Split Two Strings to Make Palindrome

public class P1616 {
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();
        boolean flag = true;
        for (int i = 0; i < n / 2; i++) {
            int j = n - 1 - i;
            if (a.charAt(i) != a.charAt(j)) {
                flag = false;
                break;
            }
        }
        if (flag) return true;
        flag = true;
        for (int i = 0; i < n / 2; i++) {
            int j = n - 1 - i;
            if (b.charAt(i) != b.charAt(j)) {
                flag = false;
                break;
            }
        }
        if (flag) return true;

        int i = 0, j = n - 1;
        flag = true;
        while (i < j) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j--;
            } else {
                if (isPalin(a, i, j) || isPalin(b, i, j)) return true;
                else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) return true;
        flag = true;
        i = 0; j = n - 1;
        while (i < j) {
            if (b.charAt(i) == a.charAt(j)) {
                i++;
                j--;
            } else {
                if (isPalin(a, i, j) || isPalin(b, i, j)) return true;
                else {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    boolean isPalin(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
