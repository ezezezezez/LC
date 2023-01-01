import java.io.*;
import java.lang.*;
import java.util.*;

// 1497. Check If Array Pairs Are Divisible by k

public class P1497 {
    public boolean canArrange(int[] arr, int k) {
        int n = arr.length;
        int[] mods = new int[k];
        for (int num : arr) {
            mods[Math.floorMod(num, k)]++;
        }
        if (mods[0] % 2 != 0) return false;
        for (int i = 1; i < k; i++) {
            if (i == k - i) {
                if (mods[i] % 2 != 0) return false;
            } else {
                if (mods[i] != mods[k - i]) return false;
            }
        }
        return true;
    }
}
