import java.io.*;
import java.lang.*;
import java.util.*;

// 1386. Cinema Seat Allocation

public class P1387 {
    public int getKth(int lo, int hi, int k) {
        int n = hi - lo + 1;
        Integer[] nums = new Integer[n];
        Arrays.setAll(nums, i -> i + lo);

        Arrays.sort(nums, (a, b) -> {
            int sa = step(a), sb = step(b);
            if (sa != sb) return Integer.compare(sa, sb);
            return Integer.compare(a, b);
        });
        return nums[k - 1];
    }

    int step(int x) {
        if (x == 1) return 0;
        return x % 2 == 0 ? 1 + step(x / 2) : (1 + step(3 * x + 1));
    }

    public int getKth2(int lo, int hi, int k) {
        int n = hi - lo + 1;
        Integer[] nums = new Integer[n];
        Arrays.setAll(nums, i -> i + lo);
        int[] steps = new int[n];
        for (int i = 0; i < n; i++) steps[i] = step(i + lo);

        Arrays.sort(nums, (a, b) -> {
            int sa = steps[a - lo], sb = steps[b - lo];
            if (sa != sb) return Integer.compare(sa, sb);
            return Integer.compare(a, b);
        });
        return nums[k - 1];
    }
}
