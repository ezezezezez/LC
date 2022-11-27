import java.util.*;
import java.io.*;
import java.lang.*;

// 1237. Find Positive Integer Solution for a Given Equation

public class P1237 {
    public List<List<Integer>> findSolution(CustomFunction iface, int z) {
        List<List<Integer>> ret = new ArrayList<>();

        for (int x = 1; x <= 1000; x++) {
            int lo = 1, hi = 1000;
            int t = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >> 1;
                int v = iface.f(x, mid);
                if (v == z) {
                    ret.add(List.of(x, mid));
                    break;
                } else if (v > z) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return ret;
    }

    interface CustomFunction {
        int f(int x, int y);
    }
}
