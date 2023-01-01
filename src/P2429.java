import java.io.*;
import java.lang.*;
import java.util.*;

// 2429. Minimize XOR

public class P2429 {
    public int minimizeXor(int num1, int num2) {
        int cnt = 0;
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & num2) > 0) cnt++;
        }
        int ret = 0;
        boolean[] used = new boolean[32];
        for (int i = 30; i >= 0; i--) {
            if (((1 << i) & num1) > 0 && cnt > 0) {
                // System.out.println("ret " + ret);
                ret ^= 1 << i;
                cnt--;
                used[i] = true;
            }
        }
        // System.out.println(cnt + " " + ret);
        if (cnt == 0) return ret;
        for (int i = 0; i < 30; i++) {
            if (!used[i] && cnt > 0) {
                ret ^= 1 << i;
                cnt--;
            }
        }
        return ret;
    }
}
