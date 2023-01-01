import java.io.*;
import java.lang.*;
import java.util.*;

// 2283. Check if Number Has Equal Digit Count and Digit Value

public class P2283 {
    public boolean digitCount(String num) {
        int n = num.length();
        int[] cnt = new int[10];
        int[] cnt2 = new int[10];
        for (int i = 0; i < n; i++) {
            cnt[i] = num.charAt(i) - '0';
            cnt2[num.charAt(i) - '0']++;
        }
        for (int i = 0; i < n; i++) {
            if (cnt2[i] != cnt[i]) return false;
        }
        return true;
    }
}
