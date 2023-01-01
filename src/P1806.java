import java.io.*;
import java.lang.*;
import java.util.*;

// 1806. Minimum Number of Operations to Reinitialize a Permutation

public class P1806 {
    public int reinitializePermutation(int n) {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int cur = i;
            int cnt = 0;
            do {
                cur = cur < n / 2 ? 2 * cur : (2 * cur + 1 - n);
                cnt++;
            } while (cur != i);
            ret = Math.max(ret, cnt);
        }
        return ret;
    }
}
