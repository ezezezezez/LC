import java.io.*;
import java.lang.*;
import java.util.*;

// 2220. Minimum Bit Flips to Convert Number

public class P2220 {
    public int minBitFlips(int start, int goal) {
        int diff = start ^ goal;
        int ret = 0;
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & diff) > 0) ret++;
        }
        return ret;
    }

    public int minBitFlips2(int start, int goal) {
        int diff = start ^ goal;
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & diff) > 0) ret++;
        }
        return ret;
    }
}
