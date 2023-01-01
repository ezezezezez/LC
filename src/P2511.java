import java.io.*;
import java.lang.*;
import java.util.*;

// 2511. Maximum Enemy Forts That Can Be Captured

public class P2511 {
    public int captureForts(int[] forts) {
        int n = forts.length;
        int ret = 0;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + (forts[i] == 0 ? 1 : 0);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (forts[i] == 1 && forts[j] == -1 && prefix[j] - prefix[i + 1] == j - i - 1) {
                    ret = Math.max(ret, j - i - 1);
                }
                if (forts[i] == -1 && forts[j] == 1 && prefix[j] - prefix[i + 1] == j - i - 1) {
                    ret = Math.max(ret, j - i - 1);
                }
            }
        }
        return ret;
    }
}
