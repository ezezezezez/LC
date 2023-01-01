import java.io.*;
import java.lang.*;
import java.util.*;

// 2391. Minimum Amount of Time to Collect Garbage

public class P2396 {
    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            int[] ds = new int[20];
            int idx = 0;
            int num = i;
            while (num > 0) {
                ds[idx++] = num % i;
                num /= i;
            }
            for (int j = 0; j < idx / 2; j++) {
                if (ds[j] != ds[idx - 1 - j]) return false;
            }
        }
        return true;
    }
}
