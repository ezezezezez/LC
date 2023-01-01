import java.io.*;
import java.lang.*;
import java.util.*;

// 2433. Find The Original Array of Prefix Xor

public class P2433 {
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] ret = new int[n];
        ret[0] = pref[0];
        for (int i = 1; i < n; i++) {
            ret[i] = pref[i] ^ pref[i - 1];
        }
        return ret;
    }
}
