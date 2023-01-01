import java.io.*;
import java.lang.*;
import java.util.*;

// 2300. Successful Pairs of Spells and Potions

public class P2300 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] ret = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            int lo = 0, hi = m - 1, t = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                if (potions[mid] * spell >= success) {
                    t = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if (t == -1) continue;
            ret[i] = m - t;
        }
        return ret;
    }
}
