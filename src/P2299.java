import java.io.*;
import java.lang.*;
import java.util.*;

// 2299. Strong Password Checker II

public class P2299 {
    public boolean strongPasswordCheckerII(String password) {
        int n = password.length();
        if (n < 8) return false;
        boolean flag1 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (c >= 'a' && c <= 'z') {
                flag1 = true;
                break;
            }
        }
        if (!flag1) return false;
        boolean flag2 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                flag2 = true;
                break;
            }
        }
        if (!flag2) return false;
        boolean flag3 = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (c >= '0' && c <= '9') {
                flag3 = true;
                break;
            }
        }
        if (!flag3) return false;
        boolean flag4 = false;
        String spe = "!@#$%^&*()-+";
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (spe.indexOf(c) != -1) {
                flag4 = true;
                break;
            }
        }
        if (!flag4) return false;
        for (int i = 1; i < n; i++) {
            if (password.charAt(i - 1) == password.charAt(i)) return false;
        }
        return true;
    }
}
