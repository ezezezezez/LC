import java.util.*;
import java.io.*;
import java.lang.*;

// 1053. Previous Permutation With One Swap

public class P1053 {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr;

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i];
            Integer key = tm.lowerKey(cur);
            tm.put(cur, i);
            if (key == null) continue;
            int idx = tm.get(key);
            arr[i] = arr[idx];
            arr[idx] = cur;
            return arr;
        }

        return arr;
    }
}
