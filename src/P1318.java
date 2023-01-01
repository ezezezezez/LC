import java.util.*;
import java.io.*;
import java.lang.*;

// 1318. Minimum Flips to Make a OR b Equal to c

public class P1318 {
    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        for (int i = 0; i < 31; i++) {
            int abit = a & (1 << i), bbit = b & (1 << i), cbit = c & (1 << i);
            int or = a | b;
            if (or != cbit) {
                if (cbit == 0) {
                    cnt += (abit > 0 ? 1 : 0) + (bbit > 0 ? 1 : 0);
                } else if (abit == 0 && bbit == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
